package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;

/**
 * Interface PhaseStrategy
 */
public interface PhaseStrategy {

    /**
     * Method that ends turns
     * @param game instance of my game
     */
     void endTurn(Game game);


    /**
     * Method that ends rounds
     * @param game instance of my game
     */
     void endRound(Game game);
  //   ArrayList<Selectable> getValidSelections(Game game);


    /**
     * Method that returns valid actions
     *
     * @param game instance of my game
     * @return List of possible actions
     */
     ArrayList<PossibleAction> getValidActions(Game game);
}
