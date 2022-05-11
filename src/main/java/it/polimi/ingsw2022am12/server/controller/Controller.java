package it.polimi.ingsw2022am12.server.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.server.Server;
import it.polimi.ingsw2022am12.server.virtualview.VirtualView;
import it.polimi.ingsw2022am12.server.adapter.GameAdapter;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Controller class represents the component which receives inputs from the client and generates a response updating views
 */
public class Controller {
    private Game myGame;
    private boolean gameWasSet;
    private boolean difficulty;
    private int numOfPlayers;
    private InputHandler inputHandler;
    private boolean creatingGame;
    private boolean acceptingUsers;
    private final Map<VirtualView, String> userMap;
    private final ArrayList<VirtualView> virtualViews;
    private final Lock lock;
    private final Server server;


    /**
     * Constructor method of Controller class
     */
    public Controller(Server server){
        this.userMap = new HashMap<>();
        this.server = server;
        creatingGame = false;
        acceptingUsers = true;
        gameWasSet = false;
        lock = new ReentrantLock();
        virtualViews = new ArrayList<>();

    }

    /**
     * updateAllViews converts the state of the game in a string, and forwards it to the various VirtualViews
     */
    private void updateAllViews(String result){
        for(VirtualView v: userMap.keySet()){
            System.out.println("updating virtualview "+ userMap.get(v));
            v.forwardMsg(result);
        }
    }

    /**
     * send method has the function to send certain messages to the VirtualViews according to the action selected by a user
     * @param v the VirtualView in the server
     * @param s selected object
     */
    public void send(VirtualView v, Selectable s){
        synchronized (lock){

            if (myGame == null){
                v.forwardMsg(ControlMessages.GAMEHASNTSTARTED.getMessage());
                return;
            }

            //check username
            if(myGame.getCurrentSchoolBoard().getNick().equals(userMap.get(v))){
                ActionStep result = inputHandler.addSelection(s);
                if (result.equals(ActionStep.NOTOK)){
                    v.forwardMsg(ControlMessages.INVALIDSELECTION.getMessage());
                    v.forwardMsg(inputHandler.getNextSelection());
                    return;
                }else if(result.equals(ActionStep.HALFOK)){
                    v.forwardMsg(inputHandler.getNextSelection());
                    return;
                }else if(result.equals(ActionStep.OK)){
                    Gson gson = new GsonBuilder().registerTypeAdapter(Game.class, new GameAdapter()).create();
                    String gameState = gson.toJson(myGame);
                    updateAllViews(gameState);
                    v.forwardMsg(ControlMessages.ACTIONCOMPLETED.getMessage());
                    if(myGame.getCurrentSchoolBoard().getNick().equals(userMap.get(v))){
                        v.forwardMsg(inputHandler.getNextSelection());
                    }else {
                        v.forwardMsg("Your turn has ended"+ "\n");
                        notifyNextPlayerOfSel();
                    }
                    return;
                }
            }
            v.forwardMsg(ControlMessages.INVALIDUSER.getMessage());
        }
    }

    /**
     * setGameMode is the method that sets the instance of my game
     * @param v view of my user
     * @param b game mode, which can be Easy (false) or Expert (true)
     * @param i selected number of players
     */
    public void setGameMode(VirtualView v, int i, boolean b){
        synchronized (lock){
            if(userMap.containsKey(v)) {
                if (gameWasSet) {
                    v.forwardMsg(ControlMessages.GAMEWASSET.getMessage());
                    return;
                }

                if (i > 1 && i <= 4) {
                    difficulty = b;
                    numOfPlayers = i;
                    creatingGame = false;
                    gameWasSet = true;
                    v.forwardMsg(ControlMessages.ACCEPTED.getMessage());
                    updateViewsOfStatus();
                } else {
                    v.forwardMsg(ControlMessages.INVALIDVALUES.getMessage());
                }
                return;
            }
            v.forwardMsg(ControlMessages.INVALIDUSER.getMessage());
        }


    }


    /**
     * selectUsername allows the users to select their usernames, which cannot be repeated
     * @param v the view of the user
     * @param nick nickname of the user
     */
    public void selectUsername(String nick, VirtualView v){
        synchronized (lock) {
            if (userMap.containsKey(v)) {
                v.forwardMsg(ControlMessages.ALREADYIN.getMessage());
                return;
            }else if (creatingGame) {
                v.forwardMsg(ControlMessages.GAMEISBEINGCREATED.getMessage());
                return;
            }else if (acceptingUsers) {
                if (userMap.containsValue(nick)) {
                    v.forwardMsg(ControlMessages.RETRY.getMessage());

                } else {
                    bindView(v, nick);
                }
                return;

            }
            v.forwardMsg(ControlMessages.GAMEISFULL.getMessage());

        }
    }

    /**
     * bindView binds the VirtualView to a certain username
     * @param nick the chosen nickname
     * @param v the view of the user
     */
    private void bindView(VirtualView v, String nick){

        userMap.put(v, nick);
        v.forwardMsg(ControlMessages.ACCEPTED.getMessage());
        if(userMap.size()==1){
            creatingGame=true;
            updateViewsOfStatus();
            return;
        }

        if(userMap.size()==numOfPlayers){
            System.out.println("Reached max players");
            acceptingUsers = false;
            ArrayList<String> nicks = new ArrayList<>(userMap.values());
            myGame = new Game(nicks, difficulty);
            myGame.setUp();
            System.out.println("Game is up");
            inputHandler = new InputHandler(myGame);
            updateViewsOfStatus();
            notifyNextPlayerOfSel();
            return;
        }
        updateViewsOfStatus();

    }


    /**
     *notifyNextPlayerOfSel iterates on the VirtualViews
     */
    public void notifyNextPlayerOfSel(){
        for(VirtualView virtualView : userMap.keySet()){
            if(userMap.get(virtualView).equals(myGame.getCurrentSchoolBoard().getNick())){
                virtualView.forwardMsg(inputHandler.getNextSelection());
            }
        }
    }

    /**
     * endGame terminates the game if a player disconnects
     */
    public void endGame(){
        System.out.println("Closing game");
        updateAllViews("Game is closing because of a player disconnection");

        myGame = null;

        gameWasSet = false;
        creatingGame = false;
        acceptingUsers = true;

        for(VirtualView v: userMap.keySet()){
            virtualViews.remove(v);
            v.close();
        }
        userMap.clear();
        updateViewsOfStatus();
    }

    /**
     * removeView removes the view when a player disconnects
     * @param v my Virtual view
     */
    public void removeView(VirtualView v){

        if(userMap.remove(v)!=null){
            System.out.println("a player disconnected");
            v.close();
            endGame();
        }else{
            System.out.println("Someone disconnected");
            virtualViews.remove(v);
            v.close();
        }
    }

    public ControlMessages getMatchStatusOfView(VirtualView v){
        if(userMap.containsKey(v)){
            if(creatingGame){
                return ControlMessages.INSERTMODE;
            }
            if(acceptingUsers){
                return ControlMessages.WAITINGFORPLAYERS;
            }
            return ControlMessages.MATCHMAKINGCOMPLETE;
        }else {
            if (creatingGame) {
                return ControlMessages.GAMEISBEINGCREATED;
            }
            if (!acceptingUsers) {
                return ControlMessages.GAMEISFULL;
            }

            return ControlMessages.REQUESTINGNICK;
        }
    }

    public void addView(VirtualView v){
        System.out.println("adding a view to server");
        virtualViews.add(v);
    }

    public void updateViewsOfStatus(){
        for(VirtualView v : virtualViews){
            v.forwardMsg(getMatchStatusOfView(v).getMessage());
        }
    }

}
