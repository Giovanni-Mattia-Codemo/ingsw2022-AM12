package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of a generic Character Card
 */
public class ActivateCharacter extends PossibleAction {

    /**
     * Constructor method of ActivateCharacter class
     */
    public ActivateCharacter(){
        super(1);
    }

    /**
     * setSelectables inserts in a "selectables" map the playableCharacters, with a key value 0
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        selectables.put(0, game.getPlayableCharacters());
    }

    /**
     * getUserSelectionsMessage returns a message based on what the user wants to select
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages>  getUserSelectionsMessage() {
        ArrayList<ControlMessages> messages = new ArrayList<>();
        messages.add(ControlMessages.CHARACTERACTION);
        return messages;
    }

    /**
     * Method useAction uses the power of the Character that does not require particular inputs, and sets the Character to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.payAndSetActiveCharacter(((CharacterCard)score.get(0)).getName());
    }

}
