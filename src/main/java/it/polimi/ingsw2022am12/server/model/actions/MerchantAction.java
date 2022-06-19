package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMerchant;
import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Merchant Card
 */
public class MerchantAction extends PossibleAction {

    /**
     * Constructor method of MerchantAction class
     */
    public MerchantAction(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {

        ArrayList<Selectable> result = new ArrayList<>();
        for(DiskColor c: DiskColor.values()){
            result.add(new ColorSelection(c));
        }
        selectables.put(1, result);
        ArrayList<Selectable> character = new ArrayList<>();
        character.add(game.getActiveCharacterCard());
        selectables.put(0, character);
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> messages = new ArrayList<>();
        messages.add(ControlMessages.MERCHANTACTION);
        if(!score.containsKey(0)){
            messages.add(ControlMessages.CHARACTERCARD);
        }else if(!score.containsKey(1)){
            messages.add(ControlMessages.COLORSELECTION);
        }
        return messages;
    }

    /**
     * Method useAction uses the power of the Merchant, and sets the Merchant to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        ((CharacterMerchant)game.getActiveCharacterCard()).setColor(((ColorSelection)score.get(1)).getColor());
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
