package it.polimi.ingsw2022am12.server.model.phases;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.*;

import java.util.ArrayList;

/**
 * Class ActionStrategy defines the action phase of the game
 */
public class ActionStrategy implements PhaseStrategy {

    /**
     * Method endTurn checks if it's the last turn, else it calls the nextTurn method
     *
     * @param game instance of the game
     */
    public void endTurn(Game game) {
        if(game.isLastTurn()){
            endRound(game);
        }
        game.nextTurn();
    }

    /**
     * Method endRound changes the phase into Planning Phase and fills the clouds
     *
     * @param game instance of the game
     */
    public void endRound(Game game) {
        if(game.getIsLastRoundFlag()){
            game.endGame();
        }else{
            game.changePhase(new PlanningStrategy());
            game.fillClouds();
            game.nextRound();
        }

    }
/*

    /**
     * Method getValidSelection returns the possible selections a player can do during the Action Phase
     *
     * @param game instance of the game
     * @return ArrayList of possible actions

    public ArrayList<Selectable> getValidSelections(Game game) {
        ArrayList<Selectable> result = new ArrayList<>();
        if(!game.movedAllDisksThisTurn()){
            result.addAll(game.getStudentsInEntranceOfCurrentTurn());


        }else if(!game.hasMovedMotherNature()){

            result.addAll(game.getIslandsInRange());
        }else result.addAll(game.getSelectableClouds());


        if(game.getActiveCharacter!= null){
            result.add(characters)
        }else if (game.getCharNeedsInput){
            result.add(required inputs)
        }

        return result;
    }*/

    public ArrayList<PossibleAction> getValidActions(Game game) {

        ArrayList<PossibleAction> result= new ArrayList<>();


        if(!game.movedAllDisksThisTurn()){
            result.add(new MoveFromEntranceToDiningRoom());
            result.add(new MoveFromEntranceToIsland());
        }else if(!game.hasMovedMotherNature()){
            result.add(new MoveMotherNature());
        }else result.add(new DrawFromCloud());



        if(game.getActiveCharacterName()==null){

                result.add(new ActivateCharacter());


        }else{
            if(!game.getActiveCharacterCard().getWasUsed()){
                PossibleAction action = game.getActiveCharacterCard().getPossibleAction();
                if(action!=null)result.add(action);
            }

        }


        return result;
    }
}
