package it.polimi.ingsw2022am12.server;

import java.util.ArrayList;

/**
 * Class ActionStrategy defines the action phase of the game
 */
public class ActionStrategy implements PhaseStrategy{

    /**
     * Method endTurn checks if it's the last turn, else it calls the nextTurn method
     *
     * @param game instance of the game
     */
    @Override
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
    @Override
    public void endRound(Game game) {
        game.changePhase(new PlanningStrategy());
        game.fillClouds();
    }

    /**
     * Method getValidSelection returns the possible selections a player can do during the Action Phase
     *
     * @param game instance of the game
     * @return ArrayList of possible actions
     */
    @Override
    public ArrayList<Selectable> getValidSelections(Game game) {
        ArrayList<Selectable> result = new ArrayList<>();
        if(!game.movedAllDisksThisTurn()){
            result.addAll(game.getStudentsInEntranceOfCurrentTurn());


        }else if(!game.hasMovedMotherNature()){

            result.addAll(game.getIslandsInRange());
        }else result.addAll(game.getSelectableClouds());

/*
        if(game.getActiveCharacter!= null){
            result.add(characters)
        }else if (game.getCharNeedsInput){
            result.add(required inputs)
        }
*/
        return result;
    }
}
