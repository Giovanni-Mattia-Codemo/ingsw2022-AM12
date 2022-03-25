package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class BagTest {

    @Test
    public void checkDraw(){
        Bag testBag = new Bag();
        Student s0 = new Student(DiskColor.RED);
        Student s1 = new Student(DiskColor.GREEN);
        Student s2 = new Student(DiskColor.BLUE);

        assertTrue(testBag.amount()==0);

        testBag.insertElement(s0);
        testBag.insertElement(s1);
        testBag.insertElement(s2);

        assertTrue(testBag.contains(s0));
        assertTrue(testBag.contains(s1));
        assertTrue(testBag.contains(s2));

        testBag.draw();
        testBag.draw();

        assertTrue(testBag.amount()==1);


    }
}
