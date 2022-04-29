package it.polimi.ingsw2022am12.server.model;

/**
 * Class Mage defines the mage card
 */
public class Mage implements Selectable {

    private final int  ID;

    /**
     * Constructor method defines mageID
     *
     * @param id mageID
     */
    public Mage(int id) {
        ID = id;
    }

    /**
     * Getter method of the Mage class
     *
     * @return ID of the mage
     */
    public int getID() {
        return ID;
    }

    @Override
    public boolean isEqual(Selectable toCompare) {
        if(toCompare instanceof Mage){
            return ((Mage) toCompare).ID == this.ID;
        }
        return false;
    }
}
