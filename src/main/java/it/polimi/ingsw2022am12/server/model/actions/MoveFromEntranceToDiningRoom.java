package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of moving a student from the entrance of a SchoolBoard to the Dining Room
 */
public class MoveFromEntranceToDiningRoom extends PossibleAction {

    private DiskColor colorInEntrance;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {

        boolean inEntrance = false;
        int inEntranceIndex = 0;
        boolean isRoom = false;


        for (Selectable s: input) {
            if(s instanceof Student){
                if(game.getCurrentSchoolBoard().getEntrance().getID()==((Student) s).getPositionID()){
                    inEntrance = true;
                    inEntranceIndex = input.indexOf(s);
                }else return ActionStep.NOTOK;
            }else if (s instanceof StudentDiskCollection){
                if(((StudentDiskCollection) s).getID()==game.getCurrentSchoolBoard().getDiningRoom().getID()){
                    isRoom=true;
                }else return ActionStep.NOTOK;
            }else return ActionStep.NOTOK;
        }

        if(input.size()==1){
                return ActionStep.HALFOK;
        }else if (input.size()==2){
            if(inEntrance&&isRoom){

                colorInEntrance = ((Student)input.get(inEntranceIndex)).getColor();
                if (!game.getCurrentSchoolBoard().isDiningRoomFull(colorInEntrance)){
                    return ActionStep.OK;
                }else return ActionStep.NOTOK;

            }return ActionStep.NOTOK;
        }else return ActionStep.NOTOK;

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
