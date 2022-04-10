package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMerchant;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Merchant Card
 */
public class MerchantAction extends PossibleAction {

    private DiskColor color;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if (input.size()==1){
            if(input.get(0) instanceof ColorSelection){
                color= ((ColorSelection) input.get(0)).getColor();
                return ActionStep.OK;
            }
        }return ActionStep.NOTOK;
    }

    /**
     * Method useAction uses the power of the Merchant, and sets the Merchant to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        ((CharacterMerchant)game.getActiveCharacterCard()).setColor(color);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
