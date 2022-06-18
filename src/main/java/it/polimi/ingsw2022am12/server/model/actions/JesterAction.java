package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.server.model.*;
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
        super(3);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>(((CharacterJester) game.getActiveCharacterCard()).getStudents().getStudentsAsSelectables());
        ArrayList<Selectable> character = new ArrayList<>();
        character.add(game.getActiveCharacterCard());

        selectables.put(0, character);
        selectables.put(1, result);
        selectables.put(2, game.getCurrentSchoolBoard().getEntrance().getStudentsAsSelectables());
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> messages = new ArrayList<>();
        messages.add(ControlMessages.JESTERACTION);
        if (!score.containsKey(0)) {
            messages.add(ControlMessages.CHARACTERCARD);
        }else if(!score.containsKey(1)){
            messages.add(ControlMessages.JESTERACTION1);
        }else if(!score.containsKey(2)){
            messages.add(ControlMessages.JESTERACTION2);
        }
        return messages;
    }

    /**
     * Method useAction uses the power of the Jester, and sets the Jester to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {

        game.jesterSwap(((Student)score.get(1)).getColor(), ((Student)score.get(2)).getColor());
        game.getActiveCharacterCard().setWasUsed(true);

    }
}
