package it.polimi.ingsw2022am12.server.controller;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import java.util.ArrayList;

public class InputHandler {
    private final Game myGame;
    private final ArrayList<Selectable> selected;
    private ArrayList<PossibleAction> actions;

    public InputHandler(Game myGame){
        this.myGame = myGame;
        this.selected = new ArrayList<>();
        this.actions = new ArrayList<>();
        updateActions();
    }

    public String getNextSelection(){
        String msg = "";
        msg = msg.concat("Things you can input:" + "\n");
        for(PossibleAction a: actions){
            msg = msg.concat("    "+ a.getUserSelectionsMessage()+ "\n");
        }
        return msg;
    }


    public ActionStep addSelection(Selectable selection){
        selected.add(selection);
        return tryUsingSelection();
    }

    public ArrayList<Selectable> getAcceptableSelections(){
        ArrayList<Selectable> selections = new ArrayList<>();
        for(PossibleAction a: actions){
            selections.addAll(a.getSelectables()) ;
        }
        return selections;
    }

    private void flushSelected(){
        selected.clear();
    }

    private void updateActions(){
        actions.clear();
        actions.addAll(myGame.getValidActions());
        for(PossibleAction a: actions){
            a.setSelectables(myGame);
        }
    }

    private ActionStep tryUsingSelection(){
        int count = 0;
        ArrayList<PossibleAction> newActions = new ArrayList<>(actions);
        for(PossibleAction pa: actions){
            ActionStep result = pa.checkInputValidity(selected, myGame);
            if(result.equals(ActionStep.OK)){
                pa.useAction(myGame);
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

}
