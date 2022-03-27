package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.Character;
import it.polimi.ingsw2022am12.server.model.actions.HagAction;
import it.polimi.ingsw2022am12.server.model.PossibleAction;

public class CharacterHag extends Character {
    //choose color, -3 students of color
    public CharacterHag(){
        super("Hag", 3);
    }

    @Override
    public PossibleAction activate() {
        return new HagAction(super.getCost());
    }
}
