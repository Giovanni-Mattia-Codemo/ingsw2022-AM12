package it.polimi.ingsw2022am12.server;

public class Student extends PlaceableObject implements Selectable{

    private DiskColor color;

    /**
     * Constructor of Student sets the color of the Student and its initial position
     * @param color of the student

     */
    public Student(DiskColor color){

        this.color = color;

    }

    /**
     * Method getColor returnes the color of the Student
     *
     * @return color
     */
    public DiskColor getColor(){
        return this.color;
    }

}
