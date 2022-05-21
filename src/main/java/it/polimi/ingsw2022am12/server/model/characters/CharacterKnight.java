package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.CharacterName;

/**
 * Class Character defines the main properties of a Knight card
 */
public class CharacterKnight extends CharacterCard {

    //+2 influence in turn

    /**
     * Constructor method of the CharacterKnight class
     *
     */
    public CharacterKnight(){
        super(CharacterName.CHARACTER_KNIGHT, 2);
    }
}
