package it.polimi.ingsw2022am12.client.CLI;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.client.View;
import it.polimi.ingsw2022am12.client.model.ClientGame;

import java.util.ArrayList;

/**
 *
 * CLIView class represents the main functions of the Command Line Interface for the client
 *
 */
public class CLIView implements View {

    /**
     * updateGameView prints a string that visualizes the changes in the current game state
     *
     * @param game the state of the current game
     * @param flag a particular flag that signals the main changes in the game
     */
    @Override
    public void updateGameView(ClientGame game, UpdateFlag flag) {
        System.out.println(game.getBoardStringToView());
    }

    /**
     * viewControlMessages shows a list of messages in the CLI
     * @param msgs the list of messages I want to show
     */
    @Override
    public void viewControlMessages(ArrayList<ControlMessages> msgs) {
        for(ControlMessages message : msgs){
            System.out.println(message.getMessage());
        }
    }
}
