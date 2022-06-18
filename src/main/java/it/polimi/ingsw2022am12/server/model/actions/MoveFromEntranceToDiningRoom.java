package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlagSchool;
import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of moving a student from the entrance of a SchoolBoard to the Dining Room
 */
public class MoveFromEntranceToDiningRoom extends PossibleAction {

    /**
     * Constructor method of MoveFromEntranceToDiningRoom class
     */
    public MoveFromEntranceToDiningRoom(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>();
        result.add(game.getCurrentSchoolBoard().getDiningRoom());
        ArrayList<Selectable> tmp = new ArrayList<>(game.getCurrentSchoolBoard().getEntrance().getStudentsAsSelectables());
        tmp.removeIf(a -> game.getCurrentSchoolBoard().isDiningRoomFull(((Student) a).getColor()));
        selectables.put(0, tmp);
        selectables.put(1, result);
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> res = new ArrayList<>();
        res.add(ControlMessages.MOVEFROMENTRANCETODININGROOM);
        if(!score.containsKey(0)){
            res.add(ControlMessages.BARDACTION1);
        }else if(!score.containsKey(1)){
            res.add(ControlMessages.DININGROOM);
        }
        return res;
    }

    /**
     * Method useAction moves a Student from the entrance of a SchoolBoard to the DiningRoom
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){

            game.moveStudentFromEntranceToRoom(((Student)score.get(0)).getColor());

    }

    /**
     * getUpdates receives an UpdateFlag for the SchoolBoards
     *
     * @param game the instance of my game
     * @return the list of updateFlags
     */
    @Override
    public ArrayList<UpdateFlag> getUpdates(Game game) {
        ArrayList<UpdateFlag> updates = new ArrayList<>();
        updates.add(new UpdateFlagSchool(game.getCurrentSchoolBoard().getNick()));
        return updates;
    }
}
