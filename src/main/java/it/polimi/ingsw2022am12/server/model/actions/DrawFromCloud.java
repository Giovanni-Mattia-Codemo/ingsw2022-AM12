package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;

/**
 * Class that defines the process of drawing from a generic cloud
 */
public class DrawFromCloud extends PossibleAction {

    /**
     * Constructor method of the DrawFromCloud class
     */
    public DrawFromCloud(){
        super(1);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        selectables.put(0, game.getDrawableClouds());
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     * @return string message
     */
    @Override
    public String getUserSelectionsMessage() {
        return "To draw from a cloud select it.";
    }

    /**
     * Method useAction draws from a usable cloud, and then ends the turn
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.drawFromCloud(((StudentDiskCollection)score.get(0)).getID());
        game.endTurn();
    }
}
