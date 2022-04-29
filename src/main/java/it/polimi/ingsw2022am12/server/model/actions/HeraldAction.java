package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Herald Card
 */
public class HeraldAction extends PossibleAction {

    /**
     * Constructor Method of HeraldAction class
     */
    public HeraldAction(){
        super(1);
    }

    /**
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        selectables.put(0, game.getIslandList().getIslandsAsSelectable());
    }

    /**
     * Method useAction uses the power of the Herald, and sets the Herald to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.conquerIsland(((IslandTileSet)score.get(0)).getID());
        game.getActiveCharacterCard().setWasUsed(true);

    }
}
