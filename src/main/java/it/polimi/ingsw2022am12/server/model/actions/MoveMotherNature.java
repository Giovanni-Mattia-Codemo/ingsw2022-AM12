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

    /**
     * Constructor method of MoveMotherNature class
     */
    public MoveMotherNature(){
        super(2);
    }

    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable>result = new ArrayList<>();
        result.add(game.getIslandList().getByIndex(game.getIslandList().getMotherNatureIndex()));
        selectables.put(0, result);

        ArrayList<Selectable> tmp = new ArrayList<>(game.getIslandList().getIslandsAsSelectable());
        tmp.removeIf(a->!game.checkIfIslandInRange(((IslandTileSet)a)));
        selectables.put(1, tmp);
    }

    @Override
    public String getUserSelectionsMessage() {
        String msg = "";
        msg = msg.concat("To move mother nature: ");
        if(!score.containsKey(0)){
            msg = msg.concat(" select the island with mother nature.");
        }else if(!score.containsKey(1)){
            msg = msg.concat(" select the island where you want to move her.");
        }
        return msg;
    }

    /**
     * Method useAction moves Mother Nature
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.moveMotherNature(((IslandTileSet)score.get(1)).getID());
    }
}
