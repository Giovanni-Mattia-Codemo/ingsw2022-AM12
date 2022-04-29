package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterJester;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMonk;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Monk Card
 */
public class MonkAction extends PossibleAction {

    private int islandID;
    private DiskColor color;

    /**
     * Constructor method of MonkAction class
     */
    public MonkAction(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>(((CharacterMonk) game.getActiveCharacterCard()).getStudents().getStudentsAsSelectables());
        selectables.put(0, result);
        selectables.put(1, game.getIslandList().getIslandsAsSelectable());
    }

    /**
     * Method useAction uses the power of the Monk, and sets the Monk to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.moveStudentFromCardToIsland(color, islandID);
        ((CharacterMonk)game.getActiveCharacterCard()).setWasUsed(true, game);
    }
}
