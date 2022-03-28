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
     * Method getSelectableType returns the name of the Selectable type
     *
     * @return String Mage
     */
    public String getSelectableType() {
        return "Mage";
    }
}
