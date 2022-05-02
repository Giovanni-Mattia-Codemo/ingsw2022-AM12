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
     * setSelectables method sets the selectable map, associating a key value to a certain object
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
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public String getUserSelectionsMessage() {
        String msg = "";
        msg = msg.concat("To use the jester:");
        if(!score.containsKey(0)){
            msg = msg.concat(" select a student on the card.");
        }else if(!score.containsKey(1)){
            msg = msg.concat(" select the student to swap in your entrance.");
        }
        return msg;
    }

    /**
     * Method useAction uses the power of the Jester, and sets the Jester to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {

        game.jesterSwap(((Student)score.get(0)).getColor(), ((Student)score.get(1)).getColor());
        game.getActiveCharacterCard().setWasUsed(true);

    }
}
