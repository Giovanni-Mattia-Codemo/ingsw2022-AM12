package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;

/**
 * Interface that determines the main features of a new Window in the GUI
 */
public interface Window {

    /**
     * displayScene displays the window in front of the selected client
     *
     * @param client the client of the player
     */
    void displayScene(Client client);

}
