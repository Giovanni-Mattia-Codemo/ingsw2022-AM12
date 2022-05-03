package it.polimi.ingsw2022am12.client;

import java.util.ArrayList;

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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setConqueror(String conqueror) {
        this.conqueror = conqueror;
    }
}
