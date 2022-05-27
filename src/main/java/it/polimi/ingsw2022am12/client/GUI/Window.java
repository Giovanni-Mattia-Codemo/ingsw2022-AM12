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
     * @param nick the name of the player
     */
    void displayScene(Client client, String nick);

    /**
     * displays a popup that allows me to confirm or deny my selection
     */
    void displayConfirmScene();

    /**
     * invokes the actions necessary to go on with a game after the selection
     */
    void closeAfterSelection();

}
