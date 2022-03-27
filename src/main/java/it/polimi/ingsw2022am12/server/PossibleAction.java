package it.polimi.ingsw2022am12.server;

import java.util.ArrayList;

public abstract class PossibleAction {

    private final int requiredInputs;


    public PossibleAction(int i){
        this.requiredInputs=i;

    }

    public int getRequiredInputs(){return requiredInputs;}
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game){
        return ActionStep.NOTOK;
    }
    public void useAction(Game game){

    }

}
