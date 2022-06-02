package it.polimi.ingsw2022am12.server.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.ControlMessagesAdapter;
import it.polimi.ingsw2022am12.NickInputAdapter;
import it.polimi.ingsw2022am12.server.gameSaveAdapters.GameSaveAdapter;
import it.polimi.ingsw2022am12.updateFlag.Flag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlagAdapterFactory;
import it.polimi.ingsw2022am12.NickInput;
import it.polimi.ingsw2022am12.server.Server;
import it.polimi.ingsw2022am12.server.virtualview.VirtualView;
import it.polimi.ingsw2022am12.server.adapter.GameAdapter;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private File savedGame;


    /**
     * Constructor method of Controller class
     */
    public Controller(Server server){
        this.userMap = new HashMap<>();
        this.server = server;
        this.directory = directory;
        creatingGame = false;
        acceptingUsers = true;
        gameWasSet = false;
        lock = new ReentrantLock();
        virtualViews = new ArrayList<>();


        savedGame = createNewFIle();

        if(isGameSavedPresent){
            String gameJson = getSavedJson();
            Gson gson = new GsonBuilder().registerTypeAdapter(Game.class, new GameSaveAdapter()).create();
            myGame = gson.fromJson(gameJson, Game.class);
        }

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
            ArrayList<ControlMessages> messages = new ArrayList<>();
            Gson gsonForMessages = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ControlMessagesAdapter()).create();
            if (myGame == null){
                messages.add(ControlMessages.GAMEHASNTSTARTED);
                v.forwardMsg(gsonForMessages.toJson(messages));
                return;
            }

            //check username
            if(myGame.getCurrentSchoolBoard().getNick().equals(userMap.get(v))){
                ActionStep result = inputHandler.addSelection(s);
                if (result.equals(ActionStep.NOTOK)){
                    messages.add(ControlMessages.INVALIDSELECTION);
                    //v.forwardMsg(ControlMessages.INVALIDSELECTION.getMessage());
                    //v.forwardMsg(inputHandler.getNextSelection());
                    messages.addAll(inputHandler.getNextSelection());
                    v.forwardMsg(gsonForMessages.toJson(messages));
                    return;
                }else if(result.equals(ActionStep.HALFOK)){
                    //v.forwardMsg(inputHandler.getNextSelection());
                    messages.addAll(inputHandler.getNextSelection());
                    v.forwardMsg(gsonForMessages.toJson(messages));
                    return;
                }else if(result.equals(ActionStep.OK)){
                    System.out.println("serializing game");
                    Gson gameSaveG = new GsonBuilder().registerTypeAdapter(Game.class, new GameSaveAdapter()).create();
                    System.out.println("made the serializer");
                    String savedGame = gameSaveG.toJson(myGame);
                    System.out.println("serialized");
                    saveGame(savedGame);
                    System.out.println("saved");
                    Gson gson = new GsonBuilder().registerTypeAdapter(Game.class, new GameAdapter()).create();
                    String gameState = gson.toJson(myGame);
                    updateAllViews(gameState);
                    ArrayList<UpdateFlag> up = inputHandler.getUpdates();
                    gson = new GsonBuilder().registerTypeAdapterFactory(new UpdateFlagAdapterFactory()).create();
                    for(UpdateFlag f:up){
                        String res = gson.toJson(f);
                        updateAllViews(res);
                    }

                    //v.forwardMsg(ControlMessages.ACTIONCOMPLETED.getMessage());
                    messages.add(ControlMessages.ACTIONCOMPLETED);
                    v.forwardMsg(gsonForMessages.toJson(messages));
                    messages.remove(ControlMessages.ACTIONCOMPLETED);

                    if(myGame.getCurrentSchoolBoard().getNick().equals(userMap.get(v))){
                        //v.forwardMsg(inputHandler.getNextSelection());
                        messages.addAll(inputHandler.getNextSelection());
                        v.forwardMsg(gsonForMessages.toJson(messages));
                    }else {
                        //v.forwardMsg("Your turn has ended"+ "\n");
                        messages.add(ControlMessages.TURNENDED);
                        v.forwardMsg(gsonForMessages.toJson(messages));
                        notifyNextPlayerOfSel();
                    }
                    return;
                }
            }
            //v.forwardMsg(ControlMessages.INVALIDUSER.getMessage());
            messages.add(ControlMessages.INVALIDUSER);
            v.forwardMsg(gsonForMessages.toJson(messages));
        }
    }

    /**
     * setGameMode is the method that sets the instance of my game
     * @param v view of my user
     * @param mode game mode, which can be Easy (false) or Expert (true)
     * @param playerNum selected number of players
     */
    public void setGameMode(VirtualView v, int playerNum, boolean mode){
        synchronized (lock){
            ArrayList<ControlMessages> messages = new ArrayList<>();
            Gson gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ControlMessagesAdapter()).create();
            if(userMap.containsKey(v)) {
                if (gameWasSet) {
                    //v.forwardMsg(ControlMessages.GAMEWASSET.getMessage());
                    messages.add(ControlMessages.GAMEWASSET);
                    v.forwardMsg(gson.toJson(messages));
                    return;
                }

                if (playerNum > 1 && playerNum <= 4) {
                    difficulty = mode;
                    numOfPlayers = playerNum;
                    if(isGameSavedPresent&&(myGame.isExpertMode()!=mode||myGame.getPlayerNicks().size()!=playerNum)){
                        isGameSavedPresent = false;
                    }
                    creatingGame = false;
                    gameWasSet = true;
                    //v.forwardMsg(ControlMessages.ACCEPTED.getMessage());
                    messages.add(ControlMessages.ACCEPTED);
                    v.forwardMsg(gson.toJson(messages));

                    updateViewsOfStatus();
                } else {
                    //v.forwardMsg(ControlMessages.INVALIDVALUES.getMessage());
                    messages.add(ControlMessages.INVALIDUSER);
                    v.forwardMsg(gson.toJson(messages));
                }

                return;
            }
            //v.forwardMsg(ControlMessages.INVALIDUSER.getMessage());
            messages.add(ControlMessages.INVALIDUSER);
            v.forwardMsg(gson.toJson(messages));
        }


    }


    /**
     * selectUsername allows the users to select their usernames, which cannot be repeated
     * @param v the view of the user
     * @param nick nickname of the user
     */
    public void selectUsername(String nick, VirtualView v){
        synchronized (lock) {
            ArrayList<ControlMessages> messages = new ArrayList<>();
            Gson gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ControlMessagesAdapter()).create();
            if (userMap.containsKey(v)) {
                //v.forwardMsg(ControlMessages.ALREADYIN.getMessage());
                messages.add(ControlMessages.ALREADYIN);
                v.forwardMsg(gson.toJson(messages));
                return;
            }else if (creatingGame) {
                //v.forwardMsg(ControlMessages.GAMEISBEINGCREATED.getMessage());
                messages.add(ControlMessages.GAMEISBEINGCREATED);
                v.forwardMsg(gson.toJson(messages));
                return;
            }else if (acceptingUsers) {
                if (userMap.containsValue(nick)) {
                    //v.forwardMsg(ControlMessages.RETRY.getMessage());
                    messages.add(ControlMessages.RETRY);
                    v.forwardMsg(gson.toJson(messages));
                } else {
                    //v.forwardMsg(ControlMessages.ASSIGNEDNICK.getMessage());
                    messages.add(ControlMessages.ASSIGNEDNICK);
                    v.forwardMsg(gson.toJson(messages));
                    NickInput toBeSet = new NickInput(nick);
                    Gson gsonNick = new GsonBuilder().registerTypeAdapter(NickInput.class, new NickInputAdapter()).create();
                    String setNick = gsonNick.toJson(toBeSet);
                    v.forwardMsg(setNick);
                    bindView(v, nick);
                }
                return;

            }
            //v.forwardMsg(ControlMessages.GAMEISFULL.getMessage());
            messages.add(ControlMessages.GAMEISFULL);
            v.forwardMsg(gson.toJson(messages));
        }
    }

    /**
     * bindView binds the VirtualView to a certain username
     * @param nick the chosen nickname
     * @param v the view of the user
     */
    private void bindView(VirtualView v, String nick){

        userMap.put(v, nick);
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
            Gson gson = new GsonBuilder().registerTypeAdapter(Game.class, new GameAdapter()).create();
            String gameState = gson.toJson(myGame);
            updateAllViews(gameState);

            gson = new GsonBuilder().registerTypeAdapterFactory(new UpdateFlagAdapterFactory()).create();
            UpdateFlag f = new UpdateFlag(Flag.FULLGAME);
            String res = gson.toJson(f);
            updateAllViews(res);

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
            ArrayList<ControlMessages> msgs = new ArrayList<>();
            if(userMap.get(virtualView).equals(myGame.getCurrentSchoolBoard().getNick())){
                Gson gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ControlMessagesAdapter()).create();
                //virtualView.forwardMsg(gson.toJson(inputHandler.getNextSelection()));
                msgs.addAll(inputHandler.getNextSelection());
                virtualView.forwardMsg(gson.toJson(msgs));
            }
        }
    }

    /**
     * endGame terminates the game if a player disconnects
     */
    public void endGame(){
        System.out.println("Closing game");
        Gson g = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ControlMessagesAdapter()).create();
        ArrayList<ControlMessages> msgs = new ArrayList<>();
        msgs.add(ControlMessages.DISCONNECTION);
        updateAllViews(g.toJson(msgs));

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

    /**
     * getMatchStatusOfView returns a particular type of ControlMessages, based on the state of the game(marked with
     * boolean values)
     *
     * @param v the VirtualView
     * @return a controlMessage
     */
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

    /**
     * addViewMethod adds a VirtualView to the Server
     *
     * @param v the VirtualView I want to add
     */
    public void addView(VirtualView v){
        System.out.println("adding a view to server");
        virtualViews.add(v);
    }

    /**
     *
     */
    public void updateViewsOfStatus(){
        Gson g = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ControlMessagesAdapter()).create();
        for(VirtualView v : virtualViews){
            ArrayList<ControlMessages>msgs = new ArrayList<>();
            msgs.add(getMatchStatusOfView(v));
            v.forwardMsg(g.toJson(msgs));
        }
    }

    public File createNewFIle(){
        File myObj = null;
        try {
            myObj = new File(directory + "\\savedGame.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                isGameSavedPresent = false;
            } else {
                if(!new Scanner(myObj).nextLine().equals("empty"))
                    isGameSavedPresent = true;
            }
        } catch (IOException e) {
            isGameSavedPresent = false;
            System.out.println("An error occurred while making a save file.");
            e.printStackTrace();
        }
        return myObj;
    }

    public void saveGame(String game){
        try {
            FileWriter myWriter = new FileWriter(savedGame,false);
            myWriter.write(game);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
