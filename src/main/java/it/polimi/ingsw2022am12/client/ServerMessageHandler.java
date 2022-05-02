package it.polimi.ingsw2022am12.client;

import java.util.Scanner;

public class ServerMessageHandler implements Runnable {
    private Scanner scanner;
    private Client client;

    public ServerMessageHandler(Scanner scanner, Client client){
            this.scanner = scanner;
            this.client = client;
    }

    @Override
    public void run() {
        String message= null;
        while (true){
            message = scanner.nextLine();
            handle(message, client);
        }
    }

    private void handle(String message, Client client){

        if(message.startsWith("{")){
            //deserialize game state
            //client.updateView(game state);
        }else client.showServerMessage(message);

    }
}
