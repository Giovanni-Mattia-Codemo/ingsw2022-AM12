package it.polimi.ingsw2022am12.server.model;

/**
 * Class Student defines a student disk
 */
public class Student extends PlaceableObject implements Selectable {

    private final DiskColor  color;

    /**
     * Constructor of Student sets the color of the Student and its initial position
     *
     * @param color of the student
     */
    public Student(DiskColor color){

        this.color = color;

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
     * Method getSelectableType returns the name of the Selectable type
     *
     * @return String Student
     */
    public String getSelectableType() {
        return "Student";
    }
}
