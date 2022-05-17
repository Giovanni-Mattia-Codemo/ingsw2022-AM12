package it.polimi.ingsw2022am12.client.model;

import it.polimi.ingsw2022am12.DiskColor;

/**
 * Class that represents the Student disk from the client's side
 */
public class ClientStudent {

    private DiskColor color;
    private int ID;

    /**
     * Constructor method of the ClientStudent class
     *
     * @param color of the student
     * @param ID of the student
     */
    public ClientStudent(DiskColor color, int ID){
        this.color = color;
        this.ID = ID;
    }

    /**
     * Default constructor for the ClientStudent class
     */
    public ClientStudent(){

    }

    /**
     * Setter method of color
     * @param color of the student
     */
    public void setColor(DiskColor color) {
        this.color = color;
    }

    /**
     * Setter method of ID
     * @param ID of the student
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Getter method of color
     * @return color of the student
     */
    public DiskColor getColor() {
        return color;
    }

    /**
     * Getter method of ID
     * @return ID of the student
     */
    public int getID() {
        return ID;
    }

    public void updateFromStudent(ClientStudent student){
        color = student.getColor();
        ID = student.getID();
    }
}
