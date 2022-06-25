package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.Bag;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * BagTest is a class made for the testing of the methods in the Bag class
 */
public class BagTest {

    /**
     * CheckDraw checks the draw() method. It puts three students inside the bag (created in two different ways, with different
     * constructors). After checking if the Students have been inserted correctly via the contains() method, it draws two students.
     * In the end it checks if only one Student remains in the Bag.
     */
    @Test
    public void checkDraw(){
        Bag testBag = new Bag();
        Student s0 = new Student(DiskColor.RED);
        Student s1 = new Student(DiskColor.GREEN);
        Student s2 = new Student(DiskColor.BLUE);

        Assertions.assertEquals(0, testBag.amount());

        testBag.insertElement(s0);
        testBag.insertElement(s1);
        testBag.insertElement(s2);

        Assertions.assertTrue(testBag.contains(s0));
        Assertions.assertTrue(testBag.contains(s1));
        Assertions.assertTrue(testBag.contains(s2));

        testBag.draw();
        testBag.draw();

        Assertions.assertEquals(1, testBag.amount());

        StudentDiskCollection newCollection = new StudentDiskCollection();
        Student ss0 = new Student(DiskColor.RED);
        Student ss1 = new Student(DiskColor.GREEN);
        Student ss2 = new Student(DiskColor.BLUE);

        newCollection.insertElement(ss0);
        newCollection.insertElement(ss1);
        newCollection.insertElement(ss2);

        Bag testBag1 = new Bag(newCollection);

        Assertions.assertEquals(3, testBag1.amount());

        Assertions.assertTrue(testBag1.contains(ss0));
        Assertions.assertTrue(testBag1.contains(ss1));
        Assertions.assertTrue(testBag1.contains(ss2));

        testBag1.draw();
        testBag1.draw();

        Assertions.assertEquals(1, testBag.amount());


    }
}