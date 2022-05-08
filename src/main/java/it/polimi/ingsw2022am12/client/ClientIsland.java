package it.polimi.ingsw2022am12.client;

public class ClientIsland {
    private int ID;
    private int number;
    private ClientStudentCollection students;
    private String conqueror;

    public ClientIsland(){
    }

    public ClientIsland(int id){
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    /**
     * Setter method for number
     * @param number int value of islands
     */
    public void setNumber(int number) {
        this.number = number;
    }

    public void setConqueror(String conqueror) {
        this.conqueror = conqueror;
    }
}
