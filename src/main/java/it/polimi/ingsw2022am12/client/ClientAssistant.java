package it.polimi.ingsw2022am12.client;

/**
 * ClientAssistant represents the Assistant card from the client's side
 */
public class ClientAssistant {

    private int turnPower;

    /**
     * Constructor method of ClientAssistant class
     *
     * @param turnPower integer that represents the turn order
     */
    public ClientAssistant(int turnPower){
        this.turnPower = turnPower;
    }

    public ClientAssistant(){

    }

    public void setTurnPower(int turnPower) {
        this.turnPower = turnPower;
    }

    /**
     * Getter method for turnPower
     *
     * @return int turn power
     */
    public int getTurnPower() {
        return turnPower;
    }
}
