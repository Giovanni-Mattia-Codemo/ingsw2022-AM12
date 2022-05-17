package it.polimi.ingsw2022am12.client;

import it.polimi.ingsw2022am12.client.model.ClientGame;

public interface View {

    public void viewMessage(String message);
    public void updateGameView(ClientGame game);
}
