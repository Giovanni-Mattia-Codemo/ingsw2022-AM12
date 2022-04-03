package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Hag Card
 */
public class HagAction extends PossibleAction {
    private DiskColor selection;

    /**
     * "Constructor" Method of HagAction class
     *
     */
    public HagAction(){
        super(1);
    }

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input , Game game){
        for (int i = 0; i < Math.min(getRequiredInputs(),input.size()) ; i++) {
            if(input.get(i) instanceof ColorSelection){
                selection = ((ColorSelection) input.get(i)).getColor();
               return ActionStep.OK;
            }
        } return ActionStep.NOTOK;
    }

    /**
     * Method useAction uses the power of the Hag, and sets the Hag to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.removeStudentsFromRoomsByColor(selection);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
