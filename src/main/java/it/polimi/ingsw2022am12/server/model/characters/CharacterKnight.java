package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;

public class CharacterKnight extends CharacterCard {
    //+2 influenza in turno

    public CharacterKnight(){
        super(CharacterName.CHARACTER_KNIGHT, 2);
    }
}
