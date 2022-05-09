package it.polimi.ingsw2022am12.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.communication.Ping;
import it.polimi.ingsw2022am12.communication.PingAdapter;

import java.util.TimerTask;

public class ClientPingTimerTask extends TimerTask {

    private final Client myClient;

    public ClientPingTimerTask(Client client){
        this.myClient = client;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Ping.class, new PingAdapter()).create();
        String ping = gson.toJson(new Ping());
        myClient.forwardJson(ping);
    }
}
