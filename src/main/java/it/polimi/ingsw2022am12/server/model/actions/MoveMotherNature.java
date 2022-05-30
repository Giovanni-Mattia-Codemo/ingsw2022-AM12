package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.Flag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
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

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable>result = new ArrayList<>();
        result.add(game.getIslandList().getByIndex(game.getIslandList().getMotherNatureIndex()));
        selectables.put(0, result);

        ArrayList<Selectable> tmp = new ArrayList<>(game.getIslandList().getIslandsAsSelectable());
        tmp.removeIf(a->!game.checkIfIslandInRange(((IslandTileSet)a)));
        selectables.put(1, tmp);
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> res = new ArrayList<>();
        res.add(ControlMessages.MOVEMOTHERNATURE);
        if(!score.containsKey(0)){
            res.add(ControlMessages.MOVEMOTHERNATURE1);
        }else if(!score.containsKey(1)){
            res.add(ControlMessages.MOVEMOTHERNATURE2);
        }
        return res;
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
    
    @Override
    public ArrayList<UpdateFlag> getUpdates(Game game){
        ArrayList<UpdateFlag> update = new ArrayList<>();
        update.add(new UpdateFlag(Flag.FULLGAME));
        return update;
    }
}
