package it.polimi.ingsw2022am12.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.client.adapter.GameStateAdapter;

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
            System.out.println("working on a game state");
            Gson gson = new GsonBuilder().registerTypeAdapter(ClientGame.class, new GameStateAdapter()).create();
            ClientGame tmp = gson.fromJson(message, ClientGame.class);
            System.out.println("sending the update to main client");
            client.updateGameState(tmp);
            client.showServerMessage(message);

        }else client.showServerMessage(message);

    }
}
