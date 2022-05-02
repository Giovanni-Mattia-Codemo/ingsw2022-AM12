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

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> tmp = new ArrayList<>(game.getAvailableMages());
        selectables.put(0, tmp);
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public String getUserSelectionsMessage() {
        return "To select a mage pick one.";
    }

    /**
     * Method useAction selects the mage, and then ends the turn
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.selectMage(((Mage)score.get(0)).getID());
        game.endTurn();
    }
}