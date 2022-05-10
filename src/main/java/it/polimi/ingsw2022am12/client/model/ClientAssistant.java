package it.polimi.ingsw2022am12.client.model;

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

    /**
     * Default constructor method of ClientAssistant class
     *
     */
    public ClientAssistant(){

    }

    /**
     * Setter method for turnPower
     *
     * @param turnPower  int turn power
     */
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
