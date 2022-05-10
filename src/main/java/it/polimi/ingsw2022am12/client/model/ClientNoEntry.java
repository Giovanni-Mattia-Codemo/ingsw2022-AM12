package it.polimi.ingsw2022am12.client.model;

/**
 * Class that represents the NoEntry tile from the client's side
 */
public class ClientNoEntry {

    private final int ID;

    /**
     * Constructor method of the ClientNoEntry class
     *
     * @param ID my chosen ID
     */
    public ClientNoEntry(int ID){
        this.ID = ID;
    }

    /**
     * Getter method of ID
     *
     * @return int value of the ID
     */
    public int getID() {
        return ID;
    }
}
