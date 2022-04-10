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
        if(input.size()==1){
            if(input.get(0) instanceof IslandTileSet){
                if((game.getIslandList().getByIndex(((IslandTileSet) input.get(0)).getID()))!=null){
                    islandID=((IslandTileSet)input.get(0)).getID();
                    return ActionStep.OK;
                }
            }
        }return ActionStep.NOTOK;
    }

    /**
     * Method useAction uses the power of the Herald, and sets the Herald to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.conquerIsland(islandID);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
