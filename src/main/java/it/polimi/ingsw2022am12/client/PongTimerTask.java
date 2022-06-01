package it.polimi.ingsw2022am12.client;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * Class that represents a Pong TimerTask
 */
public class PongTimerTask extends TimerTask {

    private boolean hasPonged;
    private final Client client;

    /**
     * PongTimerTask class constructor
     * @param client my client
     */
    public PongTimerTask(Client client){
        this.client = client;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        if (hasPonged){
            hasPonged = false;
        }else{
            client.controlMessageToView(new ArrayList<>(List.of(ControlMessages.SERVERUNREACHABLE)));
            client.disconnected();
        }
    }

    /**
     * pong method returns true if there has been a pong response
     */
    public void pong(){
        hasPonged = true;
    }
}
