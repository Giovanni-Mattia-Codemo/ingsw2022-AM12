package it.polimi.ingsw2022am12.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.client.adapter.GameStateAdapter;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

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

    @Override
    public void run() {
        String message;
        Timer timer = new Timer();
        pong = new PongTimerTask(client);
        timer.schedule(pong, 18000, 9000 );
        while (true){
            message = scanner.nextLine();
            handle(message, client);
        }
    }

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
