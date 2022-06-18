package it.polimi.ingsw2022am12.client.model;

/**
 * ClientMage represents a mage from the client's side
 */
public class ClientMage {

    private final int ID;

    /**
     * Constructor method of ClientMage class
     *
     * @param ID the chosen ID of my mage
     */
    public ClientMage(int ID){
        this.ID = ID;
    }

    /**
     * Getter method of the ID
     *
     * @return int value of the ID
     */
    public int getID() {
        return ID;
    }
}
