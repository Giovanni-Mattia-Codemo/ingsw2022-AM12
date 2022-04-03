package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.actions.HagAction;
import it.polimi.ingsw2022am12.server.model.PossibleAction;

public class CharacterHag extends CharacterCard {
    //choose color, -3 students of color
    public CharacterHag(){
        super(CharacterName.CHARACTER_HAG, 3);
    }

    @Override
    public PossibleAction getPossibleAction() {

        return new HagAction();
    }
}
