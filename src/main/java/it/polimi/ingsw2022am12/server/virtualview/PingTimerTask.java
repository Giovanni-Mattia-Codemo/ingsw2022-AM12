package it.polimi.ingsw2022am12.server.virtualview;

import java.util.TimerTask;

/**
 * Class that represents a Ping TimerTask
 */
public class PingTimerTask extends TimerTask {

    private boolean hasPinged;
    private final VirtualView mine;

    /**
     * Constructor method of PingTimerTask
     * @param v my Virtual View
     */
    public PingTimerTask(VirtualView v){
        mine=v;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        if (hasPinged){
            hasPinged = false;
        }else mine.disconnected();
    }

    /**
     * ping method returns true if there has been a ping message
     */
    public void ping(){
        hasPinged = true;
    }
}
