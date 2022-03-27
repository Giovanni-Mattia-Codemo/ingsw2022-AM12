package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.server.model.PlaceableObject;

/**
 * Interface Position
 */
public interface Position {
     void removeElement(PlaceableObject o);
     void insertElement(PlaceableObject o);
     boolean contains(PlaceableObject o);
}

