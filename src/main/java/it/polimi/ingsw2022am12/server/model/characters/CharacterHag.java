package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.actions.HagAction;
import it.polimi.ingsw2022am12.server.model.PossibleAction;

/**
 * Class Character defines the main properties of a Hag card
 */
public class CharacterHag extends CharacterCard {

    //choose a color, remove three (or less, if less are present) students of that color in dining rooms

    /**
     * Constructor method of the CharacterHag class
     *
     */
    public CharacterHag(){
        super(CharacterName.CHARACTER_HAG, 3);
    }

    /**
     * Method PossibleAction allows me to know the Hag power (removing students of a certain color from diningRoom)
     *
     * @return PossibleAction the Hag action I can use
     */
    @Override
    public PossibleAction getPossibleAction() {

        return new HagAction();
    }
}
