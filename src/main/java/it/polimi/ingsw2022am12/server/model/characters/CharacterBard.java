package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.Character;
import it.polimi.ingsw2022am12.server.model.actions.BardAction;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

public class CharacterBard extends Character {
    //swap students in entrance and room upto 2 times
    private int swapsDone;
    private ArrayList<Selectable> selected;

    public CharacterBard(){
        super("Bard", 1);
    }

    @Override
    public PossibleAction activate() {
        return new BardAction(super.getCost());
    }


}
