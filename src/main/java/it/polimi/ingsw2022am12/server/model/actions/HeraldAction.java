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
        super(2);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> tmp = new ArrayList<>();
        tmp.add(game.getActiveCharacterCard());
        selectables.put(0, tmp);
        selectables.put(1, game.getIslandList().getIslandsAsSelectable());
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public String getUserSelectionsMessage() {
        String msg = "";
        msg = msg.concat("To use the herald:");
        if(!score.containsKey(0)){
            msg = msg.concat(" select the herald card.");
        }else if(!score.containsKey(1)){
            msg = msg.concat(" select an island to conquer.");
        }
        return msg;
    }

    /**
     * Method useAction uses the power of the Herald, and sets the Herald to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.conquerIsland(((IslandTileSet)score.get(1)).getID());
        game.getActiveCharacterCard().setWasUsed(true);

    }
}
