package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.DiskColor;
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

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> tmp = new ArrayList<>(((CharacterPrincess) game.getActiveCharacterCard()).getStudents().getStudentsAsSelectables());
        tmp.removeIf(a -> game.getCurrentSchoolBoard().isDiningRoomFull(((Student) a).getColor()));
        selectables.put(0, tmp);
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public String getUserSelectionsMessage() {
        return "To use the princess select a color";
    }

    /**
     * Method useAction uses the power of the Princess, and then sets the Princess to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.moveStudentFromCardToRoom(((Student)score.get(0)).getColor());
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
