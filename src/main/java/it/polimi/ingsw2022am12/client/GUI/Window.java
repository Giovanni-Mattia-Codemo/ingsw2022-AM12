package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;

public interface Window {

    void displayScene(Client client, String nick);
    void displayConfirmScene();
    void closeAfterSelection();

}
