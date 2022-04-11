package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Hag Card
 */
public class HagAction extends PossibleAction {

    private DiskColor selection;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my HagAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input , Game game){
        if(input.size()==1) {
            if(input.get(0) instanceof ColorSelection){
                selection = ((ColorSelection) input.get(0)).getColor();
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
