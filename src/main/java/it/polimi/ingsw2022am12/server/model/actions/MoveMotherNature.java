package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

/**
 * Class that defines the process of moving MotherNature
 */
public class MoveMotherNature extends PossibleAction {

    private int islandID;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        for(Selectable s: input){
            if (s instanceof IslandTileSet){
                IslandTileSet tmp = game.getIslandList().getByIndex(((IslandTileSet) s).getID());
                if(tmp!=null) {
                    if (game.checkIfIslandInRange(tmp)) {
                        islandID = ((IslandTileSet) s).getID();
                        return ActionStep.OK;
                    }
                }
            }
        }
        return ActionStep.NOTOK;

    }

    /**
     * Method useAction moves Mother Nature
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.moveMotherNature(islandID);
    }
}
