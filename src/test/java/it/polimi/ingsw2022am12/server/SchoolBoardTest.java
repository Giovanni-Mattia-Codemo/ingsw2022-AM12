package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.exceptions.NotPresent;
import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Class used to test the methods of the SchoolBoard class
 */
public class SchoolBoardTest {

    /**
     * checkFillTowers considers a hypothetical game for three players, and checks if one of the SchoolBoards has been
     * correctly filled with six towers
     */
    @Test
    public void checkFillTowers(){
        SchoolBoard testingBoard = new SchoolBoard("Paolo");
        int numOfPlayers = 3;
        Team team1 = new Team();
        team1.addSchoolBoard(testingBoard);
        testingBoard.fillTowers(numOfPlayers, team1);

        Assertions.assertEquals(6, testingBoard.getTowersNumber());

    }

    /**
     * checkMage initializes a new SchoolBoard, then gets its mage
     */
    @Test
    public void checkMage(){
        SchoolBoard testSchool = new SchoolBoard("Nani");
        Mage mage = testSchool.getMage();
        assert true;
    }

    /**
     * checkIsDiningRoomFull creates ten new students of the same color and inserts them in the Dining Room of testingBoard;
     * it then checks if the Dining Room contains them all, and returns true since the Dining Room is actually full
     */
    @Test
    public void checkIsDiningRoomFull(){
        SchoolBoard testingBoard = new SchoolBoard("Pietro");
        Student student0 = new Student(DiskColor.RED);
        Student student1 = new Student(DiskColor.RED);
        Student student2 = new Student(DiskColor.RED);
        Student student3 = new Student(DiskColor.RED);
        Student student4 = new Student(DiskColor.RED);
        Student student5 = new Student(DiskColor.RED);
        Student student6 = new Student(DiskColor.RED);
        Student student7 = new Student(DiskColor.RED);
        Student student8 = new Student(DiskColor.RED);
        Student student9 = new Student(DiskColor.RED);

        try {
            testingBoard.insertToDiningRoom(student0);
            testingBoard.insertToDiningRoom(student1);
            testingBoard.insertToDiningRoom(student2);
            testingBoard.insertToDiningRoom(student3);
            testingBoard.insertToDiningRoom(student4);
            testingBoard.insertToDiningRoom(student5);
            testingBoard.insertToDiningRoom(student6);
            testingBoard.insertToDiningRoom(student7);
            testingBoard.insertToDiningRoom(student8);
            testingBoard.insertToDiningRoom(student9);
        }catch(Exception e){
            e.printStackTrace();
        }

        Assertions.assertEquals(10, testingBoard.getStudentsInRoomByColor(DiskColor.RED));
        Assertions.assertTrue(testingBoard.isDiningRoomFull(DiskColor.RED));

    }

    /**
     * checkRemoveFromRoom creates a new testingBoard; it then creates a new student, places it in the Entrance, and
     * then moves it from the Entrance to the Dining Room. In the end it checks if the Entrance is Empty, while the Dining
     * Room must contain one Student
     *
     */
    @Test
    public void checkMoveFromEntranceToRoom(){
        SchoolBoard testingBoard = new SchoolBoard("Pierino");
        Student student0 = new Student(DiskColor.RED);
        testingBoard.insertToEntrance(student0);
        testingBoard.moveStudentFromEntranceToRoom(student0);

        Assertions.assertFalse(testingBoard.getEntrance().contains(student0));
        Assertions.assertEquals(1, testingBoard.getStudentsInRoomByColor(DiskColor.RED));
    }

    /**
     * checkSwapStudents creates a new testingBoard; it then creates four new students, places two of them in the Entrance, and
     * two in the Dining Room. It swaps only a couple of differently colored students. In the end it checks if the Dining Room
     * contains two students of the same color, while the Entrance contains the other two Students
     *
     */
    @Test
    public void checkSwapStudents(){
    SchoolBoard testSchool = new SchoolBoard("Peperoni");
    Student s0 = new Student(DiskColor.RED);
    Student s1 = new Student(DiskColor.GREEN);
    Student s2 = new Student(DiskColor.BLUE);
    Student s3 = new Student(DiskColor.RED);
    testSchool.insertToEntrance(s0);
    testSchool.insertToEntrance(s1);
        try {
            testSchool.insertToDiningRoom(s2);
            testSchool.insertToDiningRoom(s3);
            testSchool.swapStudents(s2, s0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(2, testSchool.getStudentsInRoomByColor(DiskColor.RED));
        Assertions.assertTrue(testSchool.getEntrance().contains(s2));
        Assertions.assertTrue(testSchool.getEntrance().contains(s1));
    }

    /**
     * checkMage initializes a new SchoolBoard, then plays one of its assistants
     */
    @Test
    public void checkPlayAssistant(){
        SchoolBoard testSchool = new SchoolBoard("Mario");
        testSchool.playAssistant(7);
        Assertions.assertTrue(true);
    }

    /**
     * checkRemoveFromRoom creates a new testingBoard; it then checks if the NotPresent exception is thrown, since
     * the Dining Room is empty
     *
     */
    @Test
    public void checkRemoveFromRoom(){
        SchoolBoard testSchool = new SchoolBoard("Tony");
        String msg = null;
        Student s0 = new Student(DiskColor.RED);
        try {
            testSchool.removeFromDiningRoom(s0);
        } catch (NotPresent e) {
            msg = e.getMessage();
        }
        assert msg != null;
    }

    /**
     * checkGetMageID creates a new mage with a certain ID, then inserts it in a new testingBoard; it then checks if the
     * Mage has been assigned correctly, and if the mageID actually is the initial, chosen value
     */
    @Test
    public void checkGetMageID(){
        Mage testMage = new Mage(10);
        SchoolBoard testSchoolBoard = new SchoolBoard("Piero");
        testSchoolBoard.setMage(testMage);

        Assertions.assertEquals(testMage, testSchoolBoard.getMage());
        Assertions.assertEquals(10, testMage.getID());
    }



}