package it.polimi.ingsw2022am12.server.model.phases;

import it.polimi.ingsw2022am12.server.model.PhaseStrategy;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.Game;

import java.util.ArrayList;

/**
 * Class SetupStrategy defines the setup phase of the game
 */
public class SetupStrategy implements PhaseStrategy {

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
     * Method endRound changes the phase into Planning Phase
     *
     * @param game instance of the game
     */
    @Override
    public void endRound(Game game) {
        game.changePhase(new PlanningStrategy());
    }

    /**
     * Method getValidSelection returns the possible selections a player can do during the Action Phase
     *
     * @param game instance of the game
     * @return ArrayList of possible actions
     */
    @Override
    public ArrayList<Selectable> getValidSelections(Game game) {
        return game.getAvailableMages();
    }
}
