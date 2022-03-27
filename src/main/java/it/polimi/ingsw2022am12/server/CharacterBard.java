package it.polimi.ingsw2022am12.server;

import java.util.ArrayList;

public class CharacterBard extends Character{
    //swap students in entrance and room upto 2 times

    public CharacterBard(){
        super("Bard", 1);
    }

    private int swapsDone;
    private ArrayList<Selectable> selected;


    @Override
    public PossibleAction activate() {
        return new BardAction(super.getCost());
    }


}
