package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;

/**
 * Class Character defines the main properties of a Beggar card
 */
public class CharacterBeggar extends CharacterCard {
    //+2 range madrenatura

    /**
     * Constructor method of the CharacterBeggar class
     *
     */
    public CharacterBeggar(){
        super(CharacterName.CHARACTER_BEGGAR, 1 );
    }


}
