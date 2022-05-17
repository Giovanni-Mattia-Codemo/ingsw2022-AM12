package it.polimi.ingsw2022am12.client.CLI;

import it.polimi.ingsw2022am12.client.View;
import it.polimi.ingsw2022am12.client.model.ClientGame;

public class CLIView implements View {
    @Override
    public void viewMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void updateGameView(ClientGame game) {
        System.out.println(game.getBoardStringToView());
    }
}
