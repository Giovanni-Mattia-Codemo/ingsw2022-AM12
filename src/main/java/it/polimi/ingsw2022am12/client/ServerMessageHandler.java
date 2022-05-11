package it.polimi.ingsw2022am12.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.client.adapter.GameStateAdapter;
import it.polimi.ingsw2022am12.client.model.ClientGame;

import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

/**
 * ServerMessageHandler represents the layer of the client which deals with the messages from the server
 */
public class ServerMessageHandler implements Runnable {

    private final Scanner scanner;
    private final Client client;
    private PongTimerTask pong;

    /**
     * Constructor method ServerMessageHandler
     *
     * @param scanner the client's scanner that registers the input
     * @param client my client
     */
    public ServerMessageHandler(Scanner scanner, Client client){
            this.scanner = scanner;
            this.client = client;
    }

    /**
     * method run has the ServerMessageHandler wait for the pong message, and handle the messages incoming from the server
     */
    @Override
    public void run() {
        String message;

        Timer timer = new Timer();
        pong = new PongTimerTask(client);
        timer.schedule(pong, 18000, 9000 );
        while (true){
            try {
                while (scanner.hasNextLine()) {
                    message = scanner.nextLine();
                    handle(message, client);
                }
            } catch (RuntimeException e) {
                    System.out.println("server message handler scanner exception");
                    timer.cancel();
                    break;
                }
            }
        }


    /**
     * handle method deals with the messages coming from the server; if the message starts with "{" it means it's a gameState update
     * and so the game's state from the client's side is updated, else if it starts with "Pong" it sets hasPonged to true, else it just shows the Server's message
     *
     * @param message the message incoming from the server
     * @param client the client that is receiving the message
     */
    private void handle(String message, Client client){

        if(message.startsWith("{")){
            Gson gson = new Gson();
            Map map = gson.fromJson(message, Map.class);
            map.remove("tag");
            String res = gson.toJson(map);
            gson = new GsonBuilder().registerTypeAdapter(ClientGame.class, new GameStateAdapter()).create();
            ClientGame tmp = gson.fromJson(res, ClientGame.class);
            client.updateGameState(tmp);
        }else if(message.equals("Pong")){
            pong.pong();
        }else client.showServerMessage(message);

    }
}
