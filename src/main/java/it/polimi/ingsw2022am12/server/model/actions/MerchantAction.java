package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMerchant;

import java.util.ArrayList;

public class MerchantAction extends PossibleAction {

    public MerchantAction(){
         super(1);
    }

    private DiskColor color;

    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if (input.size()==1){
            if(input.get(0) instanceof ColorSelection){
                color= ((ColorSelection) input.get(0)).getColor();
                return ActionStep.OK;
            }
        }return ActionStep.NOTOK;
    }

    @Override
    public void useAction(Game game) {
        ((CharacterMerchant)game.getActiveCharacterCard()).setColor(color);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
