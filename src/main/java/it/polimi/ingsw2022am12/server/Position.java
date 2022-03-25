package it.polimi.ingsw2022am12.server;

/**
 * Interface Position
 */
public interface Position {
    public void removeElement(PlaceableObject o);
    public void insertElement(PlaceableObject o);
    public boolean contains(PlaceableObject o);
}

