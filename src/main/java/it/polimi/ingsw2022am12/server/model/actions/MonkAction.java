package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMonk;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Monk Card
 */
public class MonkAction extends PossibleAction {

    private int islandID;
    private DiskColor color;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {

        boolean isStudent=false;
        int studentIndex = 0;
        boolean isIsland = false;
        int islandIndex = 0;

        for (Selectable s: input){
            if(s instanceof Student){
                if(((CharacterMonk)game.getActiveCharacterCard()).getStudents().getID()==((Student) s).getPositionID()){
                    if(((CharacterMonk)game.getActiveCharacterCard()).getStudents().getFirstStudentOfColor(((Student) s).getColor()).isPresent()){
                        studentIndex=input.indexOf(s);
                        isStudent= true;
                    }else return ActionStep.NOTOK;
                }else return ActionStep.NOTOK;
            }else if(s instanceof IslandTileSet){
                if(game.getIslandList().getByIndex(((IslandTileSet) s).getID())!=null){
                    islandIndex = input.indexOf(s);
                    isIsland = true;
                }else return ActionStep.NOTOK;
            }else return ActionStep.NOTOK;
        }

        if(input.size()==1){
            return ActionStep.HALFOK;
        }else if(input.size()==2){
            if(isIsland&&isStudent) {
                color = ((Student) input.get(studentIndex)).getColor();
                islandID = ((IslandTileSet) input.get(islandIndex)).getID();
                return ActionStep.OK;
            }
        }
        return ActionStep.NOTOK;


    }

    /**
     * Method useAction uses the power of the Monk, and sets the Monk to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.moveStudentFromCardToIsland(color, islandID);
        ((CharacterMonk)game.getActiveCharacterCard()).setWasUsed(true, game);
    }
}
