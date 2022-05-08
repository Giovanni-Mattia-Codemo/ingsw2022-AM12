package it.polimi.ingsw2022am12.server;

import java.util.TimerTask;

public class PingTimerTask extends TimerTask {

    private boolean hasPinged;
    private final VirtualView mine;

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

    public void ping(){
        hasPinged = true;
    }
}
