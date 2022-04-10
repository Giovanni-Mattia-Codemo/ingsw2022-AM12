package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Mage;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

/**
 * Class that defines the process of selecting a mage
 */
public class SelectMage extends PossibleAction {

    private int mageID;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if(input.size()==1){
            if(input.get(0) instanceof Mage) {
                ArrayList<Mage> tmp = game.getAvailableMages();
                for(Mage m: tmp){
                    if(m.getID()==((Mage) input.get(0)).getID()){
                        mageID=((Mage) input.get(0)).getID();
                        return ActionStep.OK;
                    }
                }
            }
        }return ActionStep.NOTOK;
    }

    /**
     * Method useAction selects the mage, and then ends the turn
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.selectMage(mageID);
        game.endTurn();
    }
}
