package it.polimi.ingsw2022am12.client;

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
        }else client.disconnected();
    }

    /**
     * pong method returns true if there has been a pong response
     */
    public void pong(){
        hasPonged = true;
    }
}
