package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;

/**
 * Interface PhaseStrategy
 */
public interface PhaseStrategy {

     void endTurn(Game game);
     void endRound(Game game);
  //   ArrayList<Selectable> getValidSelections(Game game);
     ArrayList<PossibleAction> getValidActions(Game game);
}
