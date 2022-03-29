package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.actions.BardAction;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

public class CharacterBard extends CharacterCard {
    //swap students in entrance and room upto 2 times
    private int swapsDone;
    private ArrayList<Selectable> selected;

    public CharacterBard(){
        super("Bard", 1);
    }

    @Override
    public PossibleAction getPossibleAction() {
        if(swapsDone<2)return new BardAction();
        return null;
    }

    @Override
    public void setWasUsed(boolean isUsed){
        if(isUsed) {
            if (swapsDone < 2) {
                swapsDone++;
            } else super.setWasUsed(true);
        }else{
            swapsDone = 0;
            super.setWasUsed(false);
        }
    }



}
