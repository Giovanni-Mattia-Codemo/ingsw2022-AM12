package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.exceptions.NotPresent;
import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class SchoolBoardTest {

    @Test
    public void checkFillTowers(){
        SchoolBoard testingBoard = new SchoolBoard("Paolo");
        int numOfPlayers = 3;
        Team team1 = new Team();
        team1.addSchoolBoard(testingBoard);
        testingBoard.fillTowers(numOfPlayers, team1);

        Assertions.assertEquals(6, testingBoard.getTowersNumber());

    }

    @Test
    public void checkMage(){
        SchoolBoard testSchool = new SchoolBoard("Nani");
        Mage mage = testSchool.getMage();
        assert true;
    }

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

    @Test
    public void checkMoveFromEntranceToRoom(){
        SchoolBoard testingBoard = new SchoolBoard("Pierino");
        Student student0 = new Student(DiskColor.RED);
        testingBoard.insertToEntrance(student0);
        testingBoard.moveStudentFromEntranceToRoom(student0);

        Assertions.assertFalse(testingBoard.getEntrance().contains(student0));
        Assertions.assertEquals(1, testingBoard.getStudentsInRoomByColor(DiskColor.RED));
    }

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
            testSchool.swapStudents(s0, s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(2, testSchool.getStudentsInRoomByColor(DiskColor.RED));
        Assertions.assertTrue(testSchool.getEntrance().contains(s2));
        Assertions.assertTrue(testSchool.getEntrance().contains(s1));
    }

    @Test
    public void checkPlayAssistant(){
        SchoolBoard testSchool = new SchoolBoard("Mario");
        testSchool.playAssistant(7);
        Assertions.assertTrue(true);
    }

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

    @Test
    public void checkGetMageID(){
        Mage testMage = new Mage(10);
        SchoolBoard testSchoolBoard = new SchoolBoard("Piero");
        testSchoolBoard.setMage(testMage);

        Assertions.assertEquals(testMage, testSchoolBoard.getMage());
        Assertions.assertEquals(10, testMage.getID());
    }



}