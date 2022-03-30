package it.polimi.ingsw2022am12.server.model;

/**
 * Class Student defines a student disk
 */
public class Student extends PlaceableObject implements Selectable {

    private  DiskColor  color;

    private int positionID;

    public Student(){

    }

    /**
     * Constructor of Student sets the color of the Student and its initial position
     *
     * @param color of the student
     */
    public Student(DiskColor color){

        this.color = color;

    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public int getPositionID() {
        return positionID;
    }

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

    public void setColor(DiskColor color) {
        this.color = color;
    }

}
