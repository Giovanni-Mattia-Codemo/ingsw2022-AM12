package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.exceptions.NotValidSwap;
import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;


public class MoveFromEntranceToDiningRoom extends PossibleAction {

    private DiskColor colorInEntrance;

    /**
     * "Constructor" Method of MoveFromEntranceToDiningRoom
     *
     */
    public MoveFromEntranceToDiningRoom() {
        super(2);
    }

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
                if (game.getCurrentSchoolBoard().checkMoveStudentFromEntranceToRoom(colorInEntrance)){
                    return ActionStep.OK;
                }else return ActionStep.NOTOK;

            }return ActionStep.NOTOK;
        }else return ActionStep.NOTOK;

    }


    @Override
    public void useAction(Game game){

            game.moveStudentFromEntranceToRoom(colorInEntrance);


    }
}
