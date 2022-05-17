package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.Bag;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * BagTest is a class made for the testing of the methods in the Bag class
 */
public class BagTest {

    /**
     * CheckDraw checks the draw() method. It puts some students inside the bag. After checking if the Students have been
     * inserted correctly via the contains() method, it draws two students. In the end it checks if only one Student remains
     * in the Bag.
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
    }
}