package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of moving a student from the entrance of a SchoolBoard to the Dining Room
 */
public class MoveFromEntranceToDiningRoom extends PossibleAction {

    private DiskColor colorInEntrance;

    /**
     * Constructor method of MoveFromEntranceToDiningRoom class
     */
    public MoveFromEntranceToDiningRoom(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable objects
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
     * Method useAction moves a Student from the entrance of a SchoolBoard to the DiningRoom
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){

            game.moveStudentFromEntranceToRoom(colorInEntrance);


    }
}
