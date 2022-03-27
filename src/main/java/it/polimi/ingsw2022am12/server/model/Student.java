package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.server.model.DiskColor;
import it.polimi.ingsw2022am12.server.model.PlaceableObject;
import it.polimi.ingsw2022am12.server.model.Selectable;

public class Student extends PlaceableObject implements Selectable {

    private final DiskColor  color;

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

    @Override
    public String getSelectableType() {
        return "Student";
    }
}
