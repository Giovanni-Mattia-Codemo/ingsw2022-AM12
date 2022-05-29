package it.polimi.ingsw2022am12.client.model;

import it.polimi.ingsw2022am12.DiskColor;

/**
 * ClientIsland represents an Island tile from the client's side
 */
public class ClientIsland {
    private int ID;
    private int number;
    private ClientStudentCollection students;
    private String conqueror;
    private int numOfNoEntries;

    /**
     * Default constructor for the ClientIsland class
     */
    public ClientIsland(){
    }

    /**
     * Constructor of the ClientIsland class
     *
     * @param id my chosen id
     */
    public ClientIsland(int id){
        this.ID = id;
    }

    /**
     * Getter method for ID
     *
     * @return int value of ID
     */
    public int getID() {
        return ID;
    }

    public int getNumOfNoEntries() {
        return numOfNoEntries;
    }

    public void setNumOfNoEntries(int numOfNoEntries) {
        this.numOfNoEntries = numOfNoEntries;
    }

    /**
     * Setter method for ID
     *
     * @param ID int value of ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Setter method for students
     *
     * @param students the collection of students
     */
    public void setStudents(ClientStudentCollection students) {
        this.students = students;
    }

    /**
     * Getter method for students
     *
     * @return the collection of students
     */
    public ClientStudentCollection getStudents() {
        return students;
    }

    public int getIslandStudentsOfColor(DiskColor color){
        return students.getByColor(color);
    }

    /**
     * Setter method for number
     * @param number int value of islands
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Getter method for conqueror
     * @return String conqueror
     */
    public String getConqueror() {
        return conqueror;
    }

    /**
     * Getter method for number
     * @return int number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter method for conqueror
     * @param conqueror name of the conqueror of the island
     */
    public void setConqueror(String conqueror) {
        this.conqueror = conqueror;
    }

    /**
     * updateFromIsland updates the state of the current island using another island
     * @param island the new island
     */
    public void updateFromIsland(ClientIsland island){
        ID = island.getID();
        conqueror = island.getConqueror();
        number = island.getNumber();
        students.updateFromCollection(island.getStudents());
    }
}
