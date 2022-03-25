package it.polimi.ingsw2022am12.server;

import java.util.ArrayList;

/**
 * Class PlanningStrategy defines the planning phase of the game
 */
public class PlanningStrategy implements PhaseStrategy{

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
     * Method endRound changes the phase into Action Phase and orders the turnOrder for the next round
     *
     * @param game instance of the game
     */
    @Override
    public void endRound(Game game) {
        game.changePhase(new ActionStrategy());
        game.correctOrder(); //notify
    }

    /**
     * Method getValidSelection returns the possible selections a player can do during the Planning Phase
     *
     * @param game instance of the game
     * @return ArrayList of possible actions
     */
    @Override
    public ArrayList<Selectable> getValidSelections(Game game) {
        ArrayList<Selectable> result = new ArrayList<>();
        result.addAll(game.getCurrentSchoolBoard().getPlayableAssistants());
        return result;
    }

}
