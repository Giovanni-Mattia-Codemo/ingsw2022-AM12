package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
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
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> res = new ArrayList<>();
        res.add(ControlMessages.MOVEFROMENTRANCETOISLAND);
        if(!score.containsKey(0)){
            res.add(ControlMessages.BARDACTION1);
        }else if(!score.containsKey(1)){
            res.add(ControlMessages.MOVEFROMENTRANCETOISLAND2);
        }
        return res;
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

    /**
     * getUpdates receives an UpdateFlag for the SchoolBoards and the Islands
     *
     * @param game the instance of my game
     * @return the list of updateFlags
     */
    @Override
    public ArrayList<UpdateFlag> getUpdates(Game game) {
        ArrayList<UpdateFlag> updates = new ArrayList();
        updates.add(new UpdateFlagSchool(game.getCurrentSchoolBoard().getNick()));
        updates.add(new UpdateFlag(Flag.ISLANDS));
        return updates;
    }
}
