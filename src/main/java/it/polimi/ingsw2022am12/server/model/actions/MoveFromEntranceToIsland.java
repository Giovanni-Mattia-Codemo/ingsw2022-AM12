package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

public class MoveFromEntranceToIsland extends PossibleAction {

    /**
     * "Constructor" Method of PossibleAction class
     *
     */
    public MoveFromEntranceToIsland() {
        super(2);
    }

    private DiskColor colorInEntrance;
    private int islandID;


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
                islandID= islandIndex;
                return ActionStep.OK;

            }return ActionStep.NOTOK;
        }else return ActionStep.NOTOK;

    }


    @Override
    public void useAction(Game game){
        game.moveStudentFromEntranceToIsland(colorInEntrance, islandID);
    }

}
