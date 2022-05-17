package it.polimi.ingsw2022am12.client.model;

import it.polimi.ingsw2022am12.DiskColor;

import java.util.ArrayList;

/**
 * Class that represents a StudentDiskCollection from the client's side
 */
public class ClientStudentCollection {

    private int ID;
    private ArrayList<ClientStudent> students;

    /**
     * Constructor method of ClientStudentCollection
     * @param id of the collection
     */
    public ClientStudentCollection(int id){
        this.ID = id;
    }

    /**
     * Default constructor method of ClientStudentCollection
     */
    public ClientStudentCollection(){

    }

    /**
     * Setter method for students
     * @param students list of students in the collection
     */
    public void setStudents(ArrayList<ClientStudent> students) {
        this.students = students;
    }

    /**
     * Setter method for ID
     * @param ID of the collection
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Getter method for ID
     * @return ID of the collection
     */
    public int getID() {
        return ID;
    }

    /**
     * Getter method for students
     * @return list of students in the collection
     */
    public ArrayList<ClientStudent> getStudents() {
        return students;
    }

    public ClientStudent getStudentByID(int id){
        for(ClientStudent st:students){
            if(st.getID()==id){
                return st;
            }
        }
        return null;
    }

    /**
     * getStudents as string visualises the students contained in the collection as a String
     * @return string list of students
     */
    public String getStudentsAsString(){
        String stringStudents = "";
        for(ClientStudent std : students){
            stringStudents = stringStudents.concat(std.getColor()+"\t");
        }
        return stringStudents;
    }

    public void updateFromCollection(ClientStudentCollection clientStudentCollection){
        ArrayList<ClientStudent> tmpCopy = new ArrayList<>(clientStudentCollection.getStudents());
        for(ClientStudent st:students){
            ClientStudent tmp = clientStudentCollection.getStudentByID(st.getID());
            if(tmp!=null){
                tmpCopy.remove(tmp);
            }else{
                st.setColor(null);
            }
        }
        for (ClientStudent stud: tmpCopy){
            for(ClientStudent st: students){
                if(st.getColor()==null){
                    st.updateFromStudent(stud);
                }else{
                    students.add(stud);
                }
                break;
            }
        }
    }

}
