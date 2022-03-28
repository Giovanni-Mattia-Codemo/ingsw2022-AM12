package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.server.model.actions.ActionStep;

import java.util.ArrayList;

/**
 * Abstract class PossibleAction defines the type of every possible action a player can do during the game
 */
public abstract class PossibleAction {

    private final int requiredInputs; //number of inputs required to fully use the power of a character


    /**
     * "Constructor" Method of PossibleAction class
     */
    public PossibleAction(int numOfNeededInputs){
        this.requiredInputs= numOfNeededInputs;

    }

    /**
     * "Getter" Method
     *
     * @return int number of inputs needed by my PossibleAction
     */
    public int getRequiredInputs(){return requiredInputs;}

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game){
        return ActionStep.NOTOK;
    }

    public void useAction(Game game){

    }

}
