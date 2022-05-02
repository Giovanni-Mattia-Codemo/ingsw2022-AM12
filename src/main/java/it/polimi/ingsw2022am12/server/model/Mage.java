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

    /**
     * Method isEqual compares two objects, and checks if they are in the same state
     *
     * @param toCompare the Selectable object to compare
     * @return boolean true if the objects have the same values
     */
    @Override
    public boolean isEqual(Selectable toCompare) {
        if(toCompare instanceof Mage){
            return ((Mage) toCompare).ID == this.ID;
        }
        return false;
    }
}
