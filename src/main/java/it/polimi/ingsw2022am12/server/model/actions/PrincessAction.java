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
     * Constructor method of PrincessAction class
     */
    public PrincessAction(){
        super(1);
    }

    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> tmp = new ArrayList<>(((CharacterPrincess) game.getActiveCharacterCard()).getStudents().getStudentsAsSelectables());
        tmp.removeIf(a -> game.getCurrentSchoolBoard().isDiningRoomFull(((Student) a).getColor()));
        selectables.put(0, tmp);
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
