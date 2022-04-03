package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;

public class CharacterHost extends CharacterCard {
    //prendi professori anche in parità

    public CharacterHost(){
        super(CharacterName.CHARACTER_HOST, 2);
    }
}
