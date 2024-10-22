package it.polimi.ingsw2022am12.client.CLI;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.client.View;
import it.polimi.ingsw2022am12.client.model.ClientGame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * method that shows a message prompt in case of a failed connection to the server
     */
    @Override
    public void connectionFailedPrompt() {
        System.out.println("Connection failed (server not found): input RETRY to attempt to reconnect");
    }

    /**
     * showHelp method allows the player to visualize a guide (if he inputs 0 he will have a guide for the CLI, if he inputs
     * 1 he will receive hints about the rules of the game); both guides are files read with a scanner
     * @param i the index of the help I need
     */
    @Override
    public void showHelp(int i){
        Scanner reader = null;

        try {
            switch (i){
                case 0-> reader = new Scanner(new File("src/main/resources/it/polimi/ingsw2022am12/client/GUI/CliHelp"));
                case 1-> reader = new Scanner(new File("src/main/resources/it/polimi/ingsw2022am12/client/GUI/GameRules"));
                default -> {}
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (reader != null) {
            while(reader.hasNextLine()){
                System.out.println(reader.nextLine());
            }
        }
    }
}
