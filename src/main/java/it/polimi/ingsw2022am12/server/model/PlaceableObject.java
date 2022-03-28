package it.polimi.ingsw2022am12.server.model;

/**
 * Abstract class PlaceableObject defines the concept of an Object characterised by its position, and ability to move
 */
public abstract class PlaceableObject {
    protected Position position;

    /**
     * "Getter" method
     *
     * @return Position the position of my PlaceableObject
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Method setPosition changes the position attribute of the Object
     *
     * @param position new position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

}