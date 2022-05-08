package it.polimi.ingsw2022am12.server.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.server.ControlMessages;
import it.polimi.ingsw2022am12.server.VirtualView;
import it.polimi.ingsw2022am12.server.adapter.GameAdapter;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * Constructor method of Controller class
     */
    public Controller(){
        this.userMap = new HashMap<>();
        creatingGame = false;
        acceptingUsers = true;
        gameWasSet = false;

    }

    /**
     * updateAllViews converts the state of the game in a string, and forwards it to the various VirtualViews
     */
    private void updateAllViews(String result){
        for(VirtualView v: userMap.keySet()){
            v.forwardMsg(result);
        }
    }

    /**
     * send method has the function to send certain messages to the VirtualViews according to the action selected by a user
     * @param v the VirtualView in the server
     * @param s selected object
     * @return String the message sent
     */
    public synchronized String send(VirtualView v, Selectable s){
        String msg = "";
        if (myGame == null) return "Game isn't ready yet";

        //check username
        if(myGame.getCurrentSchoolBoard().getNick().equals(userMap.get(v))){
            ActionStep result = inputHandler.addSelection(s);
            if (result.equals(ActionStep.NOTOK)){
                msg = msg.concat("Invalid selection."+"\n") ;
                msg = msg.concat(inputHandler.getNextSelection());
            }else if(result.equals(ActionStep.HALFOK)){
                return inputHandler.getNextSelection();
            }else if(result.equals(ActionStep.OK)){
                Gson gson = new GsonBuilder().registerTypeAdapter(Game.class, new GameAdapter()).create();
                String gameState = gson.toJson(myGame);
                updateAllViews(gameState);
                msg = msg.concat("Action completed successfully." + "\n") ;
                if(myGame.getCurrentSchoolBoard().getNick().equals(userMap.get(v))){
                    msg = msg.concat(inputHandler.getNextSelection());
                }else {
                    msg = msg.concat("Your turn has ended"+ "\n");
                    notifyNextPlayerOfSel();
                }

            }
            return msg;

        }
        return "Not your turn";
    }

    /**
     * setGameMode is the method that sets the instance of my game
     * @param v view of my user
     * @param b game mode, which can be Easy or Expert
     * @param i selected number of players
     * @return ControlMessages
     */
    public synchronized ControlMessages setGameMode(VirtualView v, int i, boolean b){
        if(userMap.containsKey(v)){
            if(gameWasSet){
                return ControlMessages.GAMEWASSET;
            }

            if(i>1&&i<=4){
                difficulty = b;
                numOfPlayers = i;
                creatingGame = false;
                gameWasSet = true;
                notifyAll();
                return ControlMessages.ACCEPTED;
            }else{
                return ControlMessages.INVALIDVALUES;
            }
        }return ControlMessages.INVALIDUSER;

    }


    /**
     * selectUsername allows the users to select their usernames, which cannot be repeated
     * @param v the view of the user
     * @param nick nickname of the user
     * @return ControlMessages
     */
    public synchronized ControlMessages selectUsername(String nick, VirtualView v){
            if(userMap.containsKey(v)){
                return ControlMessages.ALREADYIN;
            }
            while(creatingGame){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(acceptingUsers){
                if(userMap.containsValue(nick)){
                    return ControlMessages.RETRY;
                }else{
                    return bindView(v, nick);
                }

            }
            return ControlMessages.GAMEISFULL;


    }

    /**
     * bindView binds the VirtualView to a certain username
     * @param nick the chosen nickname
     * @param v the view of the user
     * @return ControlMessages
     */
    private ControlMessages bindView(VirtualView v, String nick){

        userMap.put(v, nick);
        if(userMap.size()==1){
            creatingGame=true;
            return  ControlMessages.INSERTMODE;
        }
        String message = "A player was added. "+(numOfPlayers- userMap.size())+" players missing";
        updateAllViews(message);
        if(userMap.size()==numOfPlayers){
            System.out.println("Reached max players");
            acceptingUsers = false;
            ArrayList<String> nicks = new ArrayList<>();
            for(String userName: userMap.values()){
                nicks.add(userName);
            }
            myGame = new Game(nicks, difficulty);
            myGame.setUp();
            System.out.println("Game is up");
            inputHandler = new InputHandler(myGame);
            notifyNextPlayerOfSel();

        }
        return ControlMessages.ACCEPTED;
    }


    public void notifyNextPlayerOfSel(){
        for(VirtualView virtualView : userMap.keySet()){
            if(userMap.get(virtualView).equals(myGame.getCurrentSchoolBoard().getNick())){
                virtualView.forwardMsg(inputHandler.getNextSelection());
            }
        }
    }

    public void endGame(){
        System.out.println("a player disconnected");
    }


}
