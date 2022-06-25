package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.DiskColor;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Class StudentDiskCollection defines a list of type Student
 */
public class StudentDiskCollection implements Position, Selectable {

    private static int numOfStudentCollections;
    private final int id;
    private final ArrayList<Student> students;

    /**
     * Constructor method creates a new list of type Student
     */
    public StudentDiskCollection() {
        this.students = new ArrayList<>();
        id=numOfStudentCollections;
        numOfStudentCollections++;

    }

    /**
     * Constructor method of StudentDiskCollection
     *
     * @param students contained in the collection
     * @param id of the collection
     */
    public StudentDiskCollection(ArrayList<Student> students, int id){
        this.students = new ArrayList<>(students);
        this.id = id;


    }

    /**
     * Constructor method creates a new list of type Student, using an input as the ID
     */
    public StudentDiskCollection(int i){
        id = i;
        students = null;
    }

    /**
     * Getter method of StudentDiskCollection
     *
     * @return ID of the StudentDiskCollection
     */
    public int getID(){
        return this.id;
    }

    /**
     * Method getAllStudents returns the copy of the students present in the StudentDiskCollection
     *
     * @return ArrayList students
     */
    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> studentsToSend = null;
        if (students != null) {
            studentsToSend = new ArrayList<>(students);
        }
        return studentsToSend;
    }

    /**
     * Method getStudentsAsSelectables returns a list of Students as a list of Selectable objects
     *
     * @return ArrayList students
     */
    public ArrayList<Selectable> getStudentsAsSelectables(){
        ArrayList<Selectable> studentsToSend = null;
        if(students!=null){
            studentsToSend = new ArrayList<>(students);
        }
        return studentsToSend;
    }

    /**
     * Method getByColor returns the number of Students of a certain color in the StudentDiskCollection
     *
     * @param color by which to get
     * @return int number of students of this color
     */
    public int getByColor(DiskColor color){
        int number=0;
        if (this.students != null) {
            number = (int) this.students.stream().filter(x->x.getColor()==color).count();
        }
        return number;
    }

    /**
     * Method getByIndex returns the selected Student by its index in the array
     *
     * @param idx index of the array students
     * @return Student
     */
    public Student getByIndex(int idx){
        Student student = null;
        if (this.students != null) {
            student= this.students.get(idx);
        }
        return student;
    }

    /**
     * Method amount returns the number of Student contained in the class's list
     *
     * @return int amount of students in the collection
     */
    public int amount(){
        int amount =0;
        if (this.students != null) {
            amount = this.students.size();
        }
        return amount;
    }

    /**
     * Method getFirstStudentOfColor returns the first student in the list by color
     *
     * @param color of the student
     * @return Optional<Student>
     */
    public Optional<Student> getFirstStudentOfColor(DiskColor color){
        if (students != null) {
            return students.stream().filter(s->s.getColor()==color).findFirst();
        }
        return Optional.empty();
    }

    /**
     * Method removeElement takes a PlaceableObject o and removes it from the list of students.
     * The object removed must be of type Student.
     *
     * @param o PlaceableObject to be removed
     */
    @Override
    public void removeElement(PlaceableObject o) {
        if (students != null) {
            students.remove((Student) o);
        }
    }

    /**
     * Method insertElement takes a PlaceableObject and adds it to the list of students, updating its position
     * The object inserted must be of type Student.
     *
     * @param o PlaceableObject to be inserted
     */
    @Override
    public void insertElement(PlaceableObject o) {
        if(o!=null){
            if (students != null) {
                students.add((Student) o);
            }
            o.setPosition(this);
        }
    }

    /**
     * Method contains checks if the collection contains a specific Student
     *
     * @param o Student to be checked
     * @return true if present, false otherwise
     */
    @Override
    public boolean contains(PlaceableObject o){
        boolean result = false;
        if (students != null) {
            result = students.contains(o);
        }
        return result;
    }

    /**
     * Method isEqual compares two objects, and checks if they are in the same state
     *
     * @param toCompare the Selectable object to compare
     * @return boolean true if the objects have the same values
     */
    @Override
    public boolean isEqual(Selectable toCompare) {
        if(toCompare instanceof StudentDiskCollection){
            return (((StudentDiskCollection) toCompare).id)==this.id;

        }
        return false;
    }
}
