package it.polimi.ingsw2022am12.server.model;

/**
 * Class ColorSelection is used to identify the specific color of a Selectable type object
 */
public class ColorSelection implements Selectable {

    private final DiskColor color;

    /**
     * Constructor method of the class. Sets the color of the Selectable type
     *
     * @param color of the Selectable
     */
    public ColorSelection(DiskColor color){
        this.color = color;
    }

    /**
     * Method getColor returns the DiskColor attribute of the class
     *
     * @return DiskColor color
     */
    public DiskColor getColor(){
       return this.color;
    }

    /**
     * Method isEqual checks if two objects have the same values in their fields
     *
     * @param toCompare the Selectable object to compare
     */
    @Override
    public boolean isEqual(Selectable toCompare) {
        if(toCompare instanceof ColorSelection){
            return ((ColorSelection) toCompare).color == this.color;
        }
        return false;
    }
}
