package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of moving a student from the entrance of a SchoolBoard to an island
 */
public class MoveFromEntranceToIsland extends PossibleAction {


    private DiskColor colorInEntrance;
    private int islandID;

    /**
     * Constructor method of MoveFromEntranceToIsland class
     */
    public MoveFromEntranceToIsland(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        selectables.put(0, game.getCurrentSchoolBoard().getEntrance().getStudentsAsSelectables());
        selectables.put(1, game.getIslandList().getIslandsAsSelectable());
    }

    /**
     * Method useAction moves a Student from the entrance of a SchoolBoard to an island
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){
        game.moveStudentFromEntranceToIsland(colorInEntrance, islandID);
    }

}
