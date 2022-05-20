package it.polimi.ingsw2022am12.client;

import it.polimi.ingsw2022am12.UpdateFlag;
import it.polimi.ingsw2022am12.client.model.ClientGame;

public interface View {

    public void viewMessage(String message);

    /**
     * updateGameView updates the visualization of my game, modifying it according to the changes in the game's state
     *
     * @param game the current state of the game
     * @param flag an update flag that signals the main changes
     */
    public void updateGameView(ClientGame game, UpdateFlag flag);
}
