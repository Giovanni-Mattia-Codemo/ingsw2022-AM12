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
     * viewControlMessages visualizes messages on the View
     *
     * @param msg the list of ControlMessages
     */
    void viewControlMessages(ArrayList<ControlMessages> msg);

    /**
     * updateGameView updates the visualization of my game, modifying it according to the changes in the game's state
     *
     * @param game the current state of the game
     * @param flag an update flag that signals the main changes
     */
    void updateGameView(ClientGame game, UpdateFlag flag);

    /**
     * connectionFailedPrompt sends a prompt in case the connection to the server has failed
     */
    void connectionFailedPrompt();

    /**
     * showHelp method allows the player to visualize a guide
     * @param i the index of the help I need
     */
    void showHelp(int i);
}
