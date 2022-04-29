package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;

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

    @Override
    public void setSelectables(Game game) {
        selectables.put(0, game.getPlayableCharacters());
    }

    /**
     * Method useAction uses the power of the Character that does not require particular inputs, and sets the Character to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.payAndSetActiveCharacter(characterName);
    }
}
