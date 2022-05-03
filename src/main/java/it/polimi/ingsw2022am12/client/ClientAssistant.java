package it.polimi.ingsw2022am12.client;

/**
 * ClientAssistant represents the Assistant card from the client's side
 */
public class ClientAssistant {
    private int turnPower;

    public ClientAssistant(int turnPower){
        this.turnPower = turnPower;
    }

    public int getTurnPower() {
        return turnPower;
    }
}
