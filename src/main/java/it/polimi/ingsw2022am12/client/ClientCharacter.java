package it.polimi.ingsw2022am12.client;

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

    public ClientCharacter(){

    }

    public void setStudents(ClientStudentCollection students) {
        this.students = students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setHasCoin(boolean hasCoin) {
        this.hasCoin = hasCoin;
    }

    public void setNoEntryCollectionID(int noEntryCollectionID) {
        this.noEntryCollectionID = noEntryCollectionID;
    }

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
