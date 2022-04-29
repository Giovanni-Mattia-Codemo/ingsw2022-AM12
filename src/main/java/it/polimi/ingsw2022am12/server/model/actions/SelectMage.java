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

    /**
     * Constructor method of SelectMage class
     */
    public SelectMage(){
        super(1);
    }

    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> tmp = new ArrayList<>(game.getAvailableMages());
        selectables.put(0, tmp);
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