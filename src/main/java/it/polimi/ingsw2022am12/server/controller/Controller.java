package it.polimi.ingsw2022am12.server.controller;

import it.polimi.ingsw2022am12.server.ControlMessages;
import it.polimi.ingsw2022am12.server.VirtualView;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Controller {
    private Game myGame;
    private boolean gameWasSet;
    private boolean difficulty;
    private int numOfPlayers;
    private InputHandler inputHandler;
    private boolean creatingGame;
    private boolean acceptingUsers;
    private final Map<VirtualView, String> userMap;


    public Controller(){
        this.userMap = new HashMap<>();
        creatingGame = false;
        acceptingUsers = true;

    }

    private void updateAllViews(){
        for(VirtualView v: userMap.keySet()){
            //v.forwardMsg(myGame.getStateMsg);
        }
    }

    public synchronized String send(VirtualView v, Selectable s){

        //check username
        if(myGame.getCurrentSchoolBoard().getNick().equals(userMap.get(v))){
            ActionStep result = inputHandler.addSelection(s);
            if (result.equals(ActionStep.NOTOK)){
                return "Invalid selection.";
            }else if(result.equals(ActionStep.HALFOK)){
                return inputHandler.getNextSelection();
            }else if(result.equals(ActionStep.OK)){
                updateAllViews();
                String msg = "Action completed successfully." + "\n";
                if(myGame.getCurrentSchoolBoard().getNick().equals(userMap.get(v))){
                    msg = msg.concat(inputHandler.getNextSelection());
                }else msg = msg.concat("Your turn has ended"+ "\n");
                return msg;
            }
        }
        return "Not your turn";
    }

    public ControlMessages setGameMode(VirtualView v, int i, boolean b){
        if(userMap.containsKey(v)){
            if(gameWasSet){
                return ControlMessages.GAMEWASSET;
            }

            if(i>1&&i<=4){
                difficulty =b;
                numOfPlayers = i;
                creatingGame = false;
                gameWasSet = true;
                this.notifyAll();
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


    private ControlMessages bindView(VirtualView v, String nick){
        userMap.put(v, nick);
        if(userMap.size()==1){
            creatingGame=true;
            return  ControlMessages.INSERTMODE;
        }
        if(userMap.size()==numOfPlayers){
            acceptingUsers = false;
        }
        return ControlMessages.ACCEPTED;
    }

}
