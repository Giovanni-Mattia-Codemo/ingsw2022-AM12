package it.polimi.ingsw2022am12.server;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class StudentDiskCollectionTest {


    @Test
    public void checkGetByIndex(){
        StudentDiskCollection studentDiskCollection = new StudentDiskCollection();
        Student s0 = new Student(DiskColor.RED);
        Student s1 = new Student(DiskColor.RED);
        Student s2 = new Student(DiskColor.RED);
        studentDiskCollection.insertElement(s0);
        studentDiskCollection.insertElement(s2);
        studentDiskCollection.insertElement(s1);
        assertEquals(s0, studentDiskCollection.getByIndex(0));
        assertEquals(s2, studentDiskCollection.getByIndex(1));
        assertEquals(s1, studentDiskCollection.getByIndex(2));
    }



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

        assertFalse(studentDiskCollection0.getAllStudents().contains(s0));
        assertTrue(studentDiskCollection0.getAllStudents().contains(s1));
    }


    @Test
    public void checkInsertElement(){
        Student s0 = new Student(DiskColor.RED);
        StudentDiskCollection sc0 = new StudentDiskCollection();

        sc0.insertElement(s0);

        assertTrue(sc0.getAllStudents().contains(s0));
    }

}




