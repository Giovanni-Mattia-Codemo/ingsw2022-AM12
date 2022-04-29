package it.polimi.ingsw2022am12.server.model;

/**
 * Interface Selectable
 */
public interface Selectable{

    /**
     * Method isEqual checks if two objects have the same values in their fields
     *
     * @param toCompare the Selectable object to compare
     */
    boolean isEqual(Selectable toCompare);

}