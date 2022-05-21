package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.server.model.actions.BardAction;
import it.polimi.ingsw2022am12.server.model.PossibleAction;

/**
 * Class Character defines the main properties of a Bard card
 */
public class CharacterBard extends CharacterCard {

    //swaps students from entrance to room, up to two times

    private int swapsDone;

    /**
     * Constructor method of the CharacterBard class
     *
     */
    public CharacterBard(){
        super(CharacterName.CHARACTER_BARD, 1);
    }

    /**
     * Method PossibleAction allows me to know the Bard power (swaps from entrance to room)
     *
     * @return PossibleAction the Bard action I can use
     */
    @Override
    public PossibleAction getPossibleAction() {
        if(swapsDone<2)return new BardAction();
        return null;
    }

    /**
     * Method setWasUsed tells if the character was already used through a boolean flag
     *
     * @param isUsed true if the card was used, else false
     */
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