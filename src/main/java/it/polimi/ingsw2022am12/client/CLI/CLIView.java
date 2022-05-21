package it.polimi.ingsw2022am12.client.CLI;

import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.client.View;
import it.polimi.ingsw2022am12.client.model.ClientGame;
/**
 *
 * CLIView class represents the main functions of the Command Line Interface for the client
 *
 */
public class CLIView implements View {

    /**
     * viewMessage visualizes particular messages in the command line of the client
     *
     * @param message the message that must be shown to the client
     */
    @Override
    public void viewMessage(String message) {
        System.out.println(message);
    }

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
}
