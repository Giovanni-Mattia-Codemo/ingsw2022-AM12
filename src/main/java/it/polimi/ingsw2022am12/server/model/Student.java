package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.DiskColor;

/**
 * Class Student defines a student disk
 */
public class Student extends PlaceableObject implements Selectable {

    private DiskColor color;
    private int positionID;

    /**
     * Default constructor of Student
     *
     */
    public Student(){

    }

    /**
     * Constructor of Student sets the color of the Student
     *
     * @param color of the student
     */
    public Student(DiskColor color){
        this.color = color;
    }

    /**
     * Method setPositionID sets the positionID of the Student
     *
     * @param positionID the int ID of the chosen position
     */
    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    /**
     * Method getPositionID returns the position of the Student as an index
     *
     * @return the position's index
     */
    public int getPositionID() {
        return positionID;
    }

    /**
     * Method setPosition sets the position of the Student
     *
     * @param position the position to set
     */
    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
       setPositionID(((StudentDiskCollection) position).getID());
    }

    /**
     * Method getColor returns the color of the Student
     *
     * @return color
     */
    public DiskColor getColor(){
        return this.color;
    }

    /**
     * Method setColor sets the color of the Student
     *
     * @param color the color to set
     */
    public void setColor(DiskColor color) {
        this.color = color;
    }

    /**
     * Method isEqual checks if two objects have the same values in their fields
     *
     * @param toCompare the Selectable object to compare
     */
    @Override
    public boolean isEqual(Selectable toCompare) {
        if(toCompare instanceof Student){
            if(((Student) toCompare).color==this.color){
                return ((Student) toCompare).positionID == this.positionID;
            }
        }
        return false;
    }
}
