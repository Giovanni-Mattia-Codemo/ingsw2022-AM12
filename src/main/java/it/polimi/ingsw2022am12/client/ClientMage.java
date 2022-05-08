package it.polimi.ingsw2022am12.client;

/**
 * ClientMage represents a mage from the client's side
 */
public class ClientMage {

    private int ID;

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
