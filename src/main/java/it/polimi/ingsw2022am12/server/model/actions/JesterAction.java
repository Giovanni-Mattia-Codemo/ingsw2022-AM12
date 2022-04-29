package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterHerbalist;
import it.polimi.ingsw2022am12.server.model.characters.CharacterJester;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Jester Card
 */
public class JesterAction extends PossibleAction {



    /**
     * Constructor method of JesterAction class
     */
    public JesterAction(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>(((CharacterJester) game.getActiveCharacterCard()).getStudents().getStudentsAsSelectables());
        selectables.put(0, result);
        selectables.put(1, game.getCurrentSchoolBoard().getEntrance().getStudentsAsSelectables());
    }

    /**
     * Method useAction uses the power of the Jester, and sets the Jester to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {

        game.jesterSwap(s0, s1);
        game.getActiveCharacterCard().setWasUsed(true);

    }
}
