package it.polimi.ingsw2022am12.server.controller;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
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

    private void flushSelected(){
        selected.clear();
    }

    private void isValidSelection(){

    }

}
