package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;

public class CharacterCentaur extends CharacterCard {

    //doesn't count towers while calculating influence

    /**
     * Constructor method of the CharacterCentaur class
     *
     */
    public CharacterCentaur(){
        super(CharacterName.CHARACTER_CENTAUR, 3);
    }
}
