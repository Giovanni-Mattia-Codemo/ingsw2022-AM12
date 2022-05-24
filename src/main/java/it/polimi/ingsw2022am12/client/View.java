package it.polimi.ingsw2022am12.client;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.client.model.ClientGame;

import java.util.ArrayList;

/**
 * Interface that defines the main features of a View component
 */
public interface View {

    /**
     * viewMessage visualizes a message on a certain view
     *
     * @param message the message I want to visualise
     */
    void viewMessage(String message);


    void viewControlMessages(ArrayList<ControlMessages> msg);

    /**
     * updateGameView updates the visualization of my game, modifying it according to the changes in the game's state
     *
     * @param game the current state of the game
     * @param flag an update flag that signals the main changes
     */
    void updateGameView(ClientGame game, UpdateFlag flag);
}
