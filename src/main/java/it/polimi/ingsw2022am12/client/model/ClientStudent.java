package it.polimi.ingsw2022am12.client.model;

/**
 * Class that represents the Student disk from the client's side
 */
public class ClientStudent {

    private String color;
    private int ID;

    /**
     * Constructor method of the ClientStudent class
     *
     * @param color of the student
     * @param ID of the student
     */
    public ClientStudent(String color, int ID){
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
    public void setColor(String color) {
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
    public String getColor() {
        return color;
    }

    /**
     * Getter method of ID
     * @return ID of the student
     */
    public int getID() {
        return ID;
    }
}
