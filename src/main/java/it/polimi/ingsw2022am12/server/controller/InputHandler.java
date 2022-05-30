package it.polimi.ingsw2022am12.server.controller;

import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import java.util.ArrayList;

/**
 * Input handler is the class that represents the component which handles the user's inputs
 */
public class InputHandler {
    private final Game myGame;
    private final ArrayList<Selectable> selected;
    private ArrayList<PossibleAction> actions;
    private ArrayList<UpdateFlag> updates;

    /**
     * constructor method of InputHandler class
     * @param myGame instance
     */
    public InputHandler(Game myGame){
        this.myGame = myGame;
        this.selected = new ArrayList<>();
        this.actions = new ArrayList<>();
        this.updates = new ArrayList<>();
        this.updateActions();
    }

    /**
     * getNextSelection shows the user the actions he can perform and what he can select
     * @return String the message the InputHandler sends
     */
    public ArrayList<ControlMessages> getNextSelection(){
        ArrayList<ControlMessages> msg = new ArrayList<>();
        //msg = msg.concat("Things you can input:" + "\n");

        for(PossibleAction a: actions){
            //msg = msg.concat("    "+ a.getUserSelectionsMessage()+ "\n");
            msg.addAll(a.getUserSelectionsMessage());
        }
        return msg;
    }

    /**
     * addSelection adds my input in an array of selections, and returns a value of ActionStep depending on the inputs I
     * selected (it can be NOTOK, HALFOK, OK)
     *
     * @param selection the selected object
     * @return ActionStep the correctness of my selection
     */
    public ActionStep addSelection(Selectable selection){
        selected.add(selection);
        return tryUsingSelection();
    }

    /**
     * getAcceptableSelections creates an array list of valid selections for each possibleAction
     *
     * @return an array of acceptable selections
     */
    public ArrayList<Selectable> getAcceptableSelections(){
        ArrayList<Selectable> selections = new ArrayList<>();
        for(PossibleAction a: actions){
            selections.addAll(a.getSelectables()) ;
        }
        return selections;
    }

    /**
     * flushSelected method empties the array of selections
     */
    private void flushSelected(){
        selected.clear();
    }

    /**
     * updateActions first clears the actions array, then gets all the valid selections from the game, and for
     * every possibleAction in the array, it sets the new selectable objects inside the game
     */
    private void updateActions(){
        actions.clear();
        actions.addAll(myGame.getValidActions());
        for(PossibleAction a: actions){
            a.setSelectables(myGame);
        }
    }

    /**
     * tryUsingSelection creates an array of actions, and check the InputValidity for every action in the array. If it is OK
     * it uses the action, flushes the selections and updates the Actions; if it's HALFOK it raises the counter by one; if
     * it's NOTOK, it removes the possible action from the array. If the counter is greater than 0 by the end of the cycle,
     * the new array is stored in "actions"and returns HALFOK
     *
     * @return ActionStep the correctness of my selections
     */
    private ActionStep tryUsingSelection(){
        int count = 0;
        ArrayList<PossibleAction> newActions = new ArrayList<>(actions);
        for(PossibleAction pa: actions){
            ActionStep result = pa.checkInputValidity(selected, myGame);
            if(result.equals(ActionStep.OK)){
                pa.useAction(myGame);
                setUpdate(pa.getUpdates(myGame));
                flushSelected();
                updateActions();
                return ActionStep.OK;
            }else if(result.equals(ActionStep.HALFOK)){
                count++;
            }else if(result.equals(ActionStep.NOTOK)){
                newActions.remove(pa);
            }
        }
        if(count >0){
            actions = newActions;
            return ActionStep.HALFOK;
        }
        updateActions();
        flushSelected();
        return ActionStep.NOTOK;
    }

    /**
     * Setter method for updates
     * @param updates the updates I need to set
     */
    private void setUpdate(ArrayList<UpdateFlag> updates){
        this.updates = updates;
    }

    /**
     * Getter method for updates
     * @return  updates the updates I need to get
     */
    public ArrayList<UpdateFlag> getUpdates() {
        return updates;
    }
}
