package it.polimi.ingsw2022am12.server.model;

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
     * printStudents prints the Students contained in a certain collection
     */
    public void printStudents(){
        String students = " ";
        for (DiskColor c : DiskColor.values()) {
            students = students.concat( getByColor(c)+" "+ c+" / ");
        }
        System.out.print(students);
    }

    /**
     * Method getAllStudents returns the copy of the students present in the StudentDiskCollection
     *
     * @return ArrayList students
     */
    public ArrayList<Student> getAllStudents(){
        return new ArrayList<>(students);
    }

    public ArrayList<Selectable> getStudentsAsSelectables(){
        return new ArrayList<>(students);
    }

    /**
     * Method getByColor returns the number of Students of a certain color in the StudentDiskCollection
     *
     * @param color by which to get
     * @return int number of students of this color
     */
    public int getByColor(DiskColor color){
        return (int) this.students.stream().filter(x->x.getColor()==color).count();
    }

    /**
     * Method getByIndex returns the selected Student by its index in the array
     *
     * @param idx index of the array students
     * @return Student
     */
    public Student getByIndex(int idx){
        return this.students.get(idx);
    }

    /**
     * Method amount returns the number of Student contained in the class's list
     *
     * @return int
     */
    public int amount(){
       return (this.students.size());
    }

    /**
     * Method getFirstStudentOfColor returns the first student in the list by color
     *
     * @param color of the student
     * @return Optional<Student>
     */
    public Optional<Student> getFirstStudentOfColor(DiskColor color){
        return students.stream().filter(s->s.getColor()==color).findFirst();
    }

    /**
     * Method removeElement takes a PlaceableObject o and removes it from the list of students.
     * The object removed must be of type Student.
     *
     * @param o PlaceableObject to be removed
     */
    @Override
    public void removeElement(PlaceableObject o) {
        try{students.remove((Student) o);
        }catch (ClassCastException e) {
            System.out.println("ClassCastException in StudentDiskCollection.removeElement()");
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
        try{
            if(o!=null){
                students.add((Student) o);
                o.setPosition(this);
            }
        }catch (ClassCastException e) {
            System.out.println("ClassCastException in StudentDiskCollection.insertElement()");
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
        return students.contains(o);
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
