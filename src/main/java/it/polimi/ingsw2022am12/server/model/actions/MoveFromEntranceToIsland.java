package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.updateFlag.Flag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlagSchool;
import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of moving a student from the entrance of a SchoolBoard to an island
 */
public class MoveFromEntranceToIsland extends PossibleAction {

    /**
     * Constructor method of MoveFromEntranceToIsland class
     */
    public MoveFromEntranceToIsland(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        selectables.put(0, game.getCurrentSchoolBoard().getEntrance().getStudentsAsSelectables());
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
        msg = msg.concat("To move a student from the entrance to an island: ");
        if(!score.containsKey(0)){
            msg = msg.concat(" select the student from the entrance.");
        }else if(!score.containsKey(1)){
            msg = msg.concat(" select the chosen island.");
        }
        return msg;
    }

    /**
     * Method useAction moves a Student from the entrance of a SchoolBoard to an island
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){
        game.moveStudentFromEntranceToIsland(((Student)score.get(0)).getColor(), ((IslandTileSet)score.get(1)).getID());
    }

    @Override
    public ArrayList<UpdateFlag> getUpdates(Game game) {
        ArrayList<UpdateFlag> updates = new ArrayList();
        updates.add(new UpdateFlagSchool(game.getCurrentSchoolBoard().getNick()));
        updates.add(new UpdateFlag(Flag.ISLANDS));
        return updates;
    }
}
