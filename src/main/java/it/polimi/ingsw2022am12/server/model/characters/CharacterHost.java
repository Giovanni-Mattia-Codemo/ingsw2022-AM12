package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.CharacterName;

/**
 * Class Character defines the main properties of a Host card
 */
public class CharacterHost extends CharacterCard {

    //you can control a professor, even if you are in a tie

    /**
     * Constructor method of the CharacterHost class
     *
     */
    public CharacterHost(){
        super(CharacterName.CHARACTER_HOST, 2);
    }
}
