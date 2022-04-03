package it.polimi.ingsw2022am12.server.model.phases;

import it.polimi.ingsw2022am12.server.model.PhaseStrategy;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.actions.PlayAssistant;

import java.util.ArrayList;

/**
 * Class PlanningStrategy defines the planning phase of the game
 */
public class PlanningStrategy implements PhaseStrategy {

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
     * Method getValidActions returns a list of possible actions for the current player
     *
     * @param game instance of the game
     * @return ArrayList of possible actions
     */
    @Override
    public ArrayList<PossibleAction> getValidActions(Game game) {
        ArrayList<PossibleAction> result= new ArrayList<>();
        result.add(new PlayAssistant());
        return result;
    }
/*
    /**
     * Method getValidSelection returns the possible selections a player can do during the Planning Phase
     *
     * @param game instance of the game
     * @return ArrayList of possible actions

    public ArrayList<Selectable> getValidSelections(Game game) {

        return new ArrayList<>(game.getCurrentSchoolBoard().getPlayableAssistants());
    }
*/

}
