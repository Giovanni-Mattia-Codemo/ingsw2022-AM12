package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterPrincess;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Princess card
 */
public class PrincessAction extends PossibleAction {

    private DiskColor color;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if(input.size()==1){
            if (input.get(0) instanceof Student tmp){
                if(tmp.getPositionID()==((CharacterPrincess)game.getActiveCharacterCard()).getStudents().getID()){
                    if(((CharacterPrincess)game.getActiveCharacterCard()).getStudents().getFirstStudentOfColor(tmp.getColor()).isPresent()){
                        if(!game.getCurrentSchoolBoard().isDiningRoomFull(tmp.getColor())){
                           color = tmp.getColor();
                           return ActionStep.OK;
                        }
                    }
                }
            }
        }
        return ActionStep.NOTOK;
    }

    /**
     * Method useAction uses the power of the Princess, and then sets the Princess to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.moveStudentFromCardToRoom(color);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
