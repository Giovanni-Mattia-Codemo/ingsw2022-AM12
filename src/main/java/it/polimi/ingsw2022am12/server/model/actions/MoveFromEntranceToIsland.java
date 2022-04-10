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
        boolean isIsland = false;
        int islandIndex = 0;


        for (Selectable s: input) {
            if(s instanceof Student){
                if(game.getCurrentSchoolBoard().getEntrance().getID()==((Student) s).getPositionID()){
                    inEntrance = true;
                    inEntranceIndex = input.indexOf(s);
                }else return ActionStep.NOTOK;
            }else if (s instanceof IslandTileSet){
                if(game.getIslandList().getByIndex(((IslandTileSet) s).getID())!=null){
                    isIsland=true;
                    islandIndex = input.indexOf(s);
                }else return ActionStep.NOTOK;
            }else return ActionStep.NOTOK;
        }

        if(input.size()==1){
            return ActionStep.HALFOK;
        }else if (input.size()==2){
            if(inEntrance&&isIsland){
                colorInEntrance = ((Student)input.get(inEntranceIndex)).getColor();
                islandID= ((IslandTileSet)input.get(islandIndex)).getID();
                return ActionStep.OK;
            }return ActionStep.NOTOK;
        }else return ActionStep.NOTOK;

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
