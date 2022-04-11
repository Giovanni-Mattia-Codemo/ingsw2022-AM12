package it.polimi.ingsw2022am12.server.model;

/**
 * Interface Position
 */
public interface Position {

     /**
      * Method that removes objects
      *
      * @param o object to be removed
      */
     void removeElement(PlaceableObject o);

     /**
      * Method that inserts objects
      *
      * @param o object to be inserted
      */
     void insertElement(PlaceableObject o);

     /**
      * Method that checks if an object is contained in a collection
      *
      * @param o object to be checked
      * @return true if the object is contained in the collection, else false
      */
     boolean contains(PlaceableObject o);
}

