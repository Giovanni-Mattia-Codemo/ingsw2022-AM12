package it.polimi.ingsw2022am12.server;

import java.util.ArrayList;

/**
 * Interface PhaseStrategy
 */
public interface PhaseStrategy {

    public void endTurn(Game game);
    public void endRound(Game game);
    ArrayList<Selectable> getValidSelections(Game game);
}
