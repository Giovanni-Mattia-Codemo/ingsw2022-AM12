package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class BagTest {

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