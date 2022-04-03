package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

public class MoveMotherNature extends PossibleAction {

    private int islandID;

    /**
     * "Constructor" Method of MoveMotherNature
     *
     */
    public MoveMotherNature() {
        super(1);
    }

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

    @Override
    public void useAction(Game game) {
        game.moveMotherNature(islandID);
    }
}
