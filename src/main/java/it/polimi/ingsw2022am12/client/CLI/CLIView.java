package it.polimi.ingsw2022am12.client.CLI;

import it.polimi.ingsw2022am12.client.View;

public class CLIView implements View {
    @Override
    public void viewMessage(String message) {
        System.out.println(message);
    }
}
