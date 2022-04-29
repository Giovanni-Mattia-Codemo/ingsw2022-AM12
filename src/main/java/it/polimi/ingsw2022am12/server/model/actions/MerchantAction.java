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
     * Constructor method of MerchantAction class
     */
    public MerchantAction(){
        super(1);
    }

    /**
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>();
        for(DiskColor c: DiskColor.values()){
            result.add(new ColorSelection(c));
        }
        selectables.put(0, result);
    }

    /**
     * Method useAction uses the power of the Merchant, and sets the Merchant to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        ((CharacterMerchant)game.getActiveCharacterCard()).setColor(((ColorSelection)score.get(0)).getColor());
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
