package it.polimi.ingsw2022am12.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.ControlMessagesAdapter;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlagAdapterFactory;
import it.polimi.ingsw2022am12.client.adapter.GameStateAdapter;
import it.polimi.ingsw2022am12.client.model.ClientGame;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

/**
 *
 *
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
                    //System.out.println("Server message handler scanner exception");
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
        Gson gson = new Gson();
        Map map = gson.fromJson(message, Map.class);
        String tag = (String) map.get("tag");
        map.remove("tag");
        String res = null;
        switch (tag){
            case "GameState":
                res = gson.toJson(map);
                gson = new GsonBuilder().registerTypeAdapter(ClientGame.class, new GameStateAdapter()).create();
                ClientGame tmp = gson.fromJson(res, ClientGame.class);
                client.updateLastSavedGame(tmp);
                break;

            case "UpdateFlag":
                res = gson.toJson(map);
                gson = new GsonBuilder().registerTypeAdapterFactory(new UpdateFlagAdapterFactory()).create();
                UpdateFlag flag = gson.fromJson(res, UpdateFlag.class);
                client.updateGameState(flag);
                break;

            case "Nick":
                String nick = (String) map.get("nick");
                client.setThisClientNick(nick);
                break;

            case "ControlMessages":
                res = gson.toJson(map);
                gson = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ControlMessagesAdapter()).create();
                ArrayList<ControlMessages> msg = gson.fromJson(res, ArrayList.class );
                client.controlMessageToView(msg);
                break;

            case "Ping":
                pong.pong();
                break;

            default:
                System.out.println("Weird message from server");
                break;
        }

    }
}
