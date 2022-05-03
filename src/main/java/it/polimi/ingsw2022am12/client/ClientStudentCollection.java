package it.polimi.ingsw2022am12.client;

import java.util.ArrayList;

public class ClientStudentCollection {
    private int ID;
    private ArrayList<ClientStudent> students;

    public ClientStudentCollection(int id){
        this.ID = id;
    }

    public ClientStudentCollection(){

    }

    public void setStudents(ArrayList<ClientStudent> students) {
        this.students = students;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<ClientStudent> getStudents() {
        return students;
    }
}
