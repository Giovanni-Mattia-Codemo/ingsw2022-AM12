package it.polimi.ingsw2022am12.client.model;

/**
 * ClientCharacter represents the Character card from the client's side
 */
public class ClientCharacter {

    private String name;
    private boolean hasCoin;
    private int cost;
    private ClientStudentCollection students;
    private int numberOfNoEntries;
    private int noEntryCollectionID;

    /**
     * Constructor method of ClientCharacter class
     *
     * @param name string that represents the character's name
     */
    public ClientCharacter(String name){
        this.name = name;
    }

    /**
     * Default constructor method of ClientCharacter class
     *
     */
    public ClientCharacter(){

    }

    /**
     * Setter method for students
     * @param students a collection of students
     */
    public void setStudents(ClientStudentCollection students) {
        this.students = students;
    }

    /**
     * Setter method for name
     * @param name string that represents the name of the Character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for cost
     * @param cost integer value that represents the cost of the Character
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Setter method for hasCoin
     * @param hasCoin boolean value (true if it has an additional coin)
     */
    public void setHasCoin(boolean hasCoin) {
        this.hasCoin = hasCoin;
    }

    /**
     * Setter method for noEntryCollectionID
     * @param noEntryCollectionID chosen number of NoEntryCollectionID
     */
    public void setNoEntryCollectionID(int noEntryCollectionID) {
        this.noEntryCollectionID = noEntryCollectionID;
    }

    /**
     * Setter method for numberOfNoEntries
     * @param numberOfNoEntries chosen number of NoEntries
     */
    public void setNumberOfNoEntries(int numberOfNoEntries) {
        this.numberOfNoEntries = numberOfNoEntries;
    }

    /**
     * Getter method for name
     *
     * @return string name of the Character
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for cost
     *
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Getter method for students
     *
     * @return students
     */
    public ClientStudentCollection getStudents() {
        return students;
    }

    /**
     * Getter method for numberOfNoEntries
     *
     * @return numberOfNoEntries
     */
    public int getNumberOfNoEntries() {
        return numberOfNoEntries;
    }

    /**
     * Getter method for noEntryCollectionID
     *
     * @return noEntryCollectionID
     */
    public int getNoEntryCollectionID() {
        return noEntryCollectionID;
    }
}
