package it.polimi.ingsw2022am12.client;

import java.util.Scanner;

/**
 * CLIHandler class represents the component that handles inputs arriving to the client
 */
public class CLIHandler implements Runnable{

    private final Scanner in;
    private final Client client;
    /**
     * ClientInputHandler class constructor
     *
     * @param in scanner in input
     * @param client my client
     */
    public CLIHandler(Scanner in, Client client){
        this.in = in;
        this.client = client;
    }
    /**
     * run method makes the client able to accept different inputs from command line
     */
    @Override
    public void run() {
        String input;
        while(true){
            try{
                input = in.nextLine();
                ClientInputHandler.handle(input, client);
            }catch (RuntimeException e){
                break;
            }

        }
    }
}
