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

    /**
     * getByColor returns the number of students of a certain color
     * @param color of the students
     * @return number of students of that colo
     */
    public int getByColor(DiskColor color){
        return (int) this.students.stream().filter(x->x.getColor()==color).count();
    }

    /**
     * updateFromCollection updates a current collection using the data from a new one
     *
     * @param clientStudentCollection the new clientStudentCollection
     */
    public void updateFromCollection(ClientStudentCollection clientStudentCollection){
        ArrayList<ClientStudent> tmpCopy = new ArrayList<>(clientStudentCollection.getStudents());
        ArrayList<ClientStudent> copyStudents = new ArrayList<>(students);
        ArrayList<ClientStudent> toAdd = new ArrayList<>();

        for (ClientStudent tmp : tmpCopy) {
            ClientStudent stud;
            boolean found = false;
            for (int j = 0; j < copyStudents.size(); j++) {
                stud = copyStudents.get(j);
                if (tmp.getColor() == stud.getColor()) {
                    found = true;
                    copyStudents.remove(stud);

                    break;
                }
            }
            if (!found) {
                toAdd.add(tmp);
            }

        }

        for(ClientStudent st: toAdd){
            if(!copyStudents.isEmpty()){
                copyStudents.get(0).setColor(st.getColor());
                copyStudents.remove(0);
            }else{
                students.add(new ClientStudent(st.getColor(), this.getID()));
            }

        }
        for(ClientStudent student : copyStudents){
            student.setColor(null);
        }
    }

}

