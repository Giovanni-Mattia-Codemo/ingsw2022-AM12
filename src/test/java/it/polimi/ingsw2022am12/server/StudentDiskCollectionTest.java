package it.polimi.ingsw2022am12.server;



import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * StudentDiskCollectionTest is a class made for the testing of the methods in the StudentDiskCollection class
 */
public class StudentDiskCollectionTest {


    /**
     * checkGetByIndex creates three new Students and inserts them in a StudentDiskCollection in order; it then checks that
     * every new element corresponds to the element put in the collection in a certain index, since the indexing respects
     * the order of the insertElement() calls for s0, s2, s1
     */
    @Test
    public void checkGetByIndex(){
        StudentDiskCollection studentDiskCollection = new StudentDiskCollection();
        Student s0 = new Student(DiskColor.RED);
        Student s1 = new Student(DiskColor.RED);
        Student s2 = new Student(DiskColor.RED);
        studentDiskCollection.insertElement(s0);
        studentDiskCollection.insertElement(s2);
        studentDiskCollection.insertElement(s1);
        Assertions.assertEquals(s0, studentDiskCollection.getByIndex(0));
        Assertions.assertEquals(s2, studentDiskCollection.getByIndex(1));
        Assertions.assertEquals(s1, studentDiskCollection.getByIndex(2));
    }


    /**
     * checkRemoveElement inserts two Students in a StudentDiskCollection, then removes one. It then checks if the collection
     * still contains the Student that hasn't been removed, and does not contain the one that has instead been removed
     */
    @Test
    public void checkRemoveElement(){
        StudentDiskCollection studentDiskCollection0 = new StudentDiskCollection();

        Student s0 = new Student(DiskColor.RED);
        Student s1 = new Student(DiskColor.RED);
        studentDiskCollection0.insertElement(s0);
        studentDiskCollection0.insertElement(s1);
        try {
            studentDiskCollection0.removeElement(s0);
        }catch(Exception e){ System.out.println("Not valid move");}

        Assertions.assertFalse(studentDiskCollection0.getAllStudents().contains(s0));
        Assertions.assertTrue(studentDiskCollection0.getAllStudents().contains(s1));
    }


    /**
     * checkInsertElement inserts a new student in the collection, then checks if the StudentDiskCollection contains
     * such Student
     */
    @Test
    public void checkInsertElement(){
        Student s0 = new Student(DiskColor.RED);
        StudentDiskCollection sc0 = new StudentDiskCollection();

        sc0.insertElement(s0);

        Assertions.assertTrue(sc0.getAllStudents().contains(s0));
    }

}




