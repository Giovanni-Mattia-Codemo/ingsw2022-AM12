package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.exceptions.NotValidSwap;
import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;

public class GameTest {

    @Test
    public void checkSetUp() throws Exception {
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Pippo");
        nicks.add("Pluto");
        Game testGame = new Game(nicks);
        testGame.setUp();

        Assertions.assertEquals(100, testGame.getBag().amount());
        Assertions.assertEquals(8, testGame.getCurrentSchoolBoard().getTowersNumber());
        Assertions.assertEquals(3, testGame.getCloud(0).amount());



        nicks.add("Paperino");
        Game testGame1 = new Game(nicks);


        testGame1.setUp();


        Assertions.assertEquals(81, testGame1.getBag().amount());
        Assertions.assertEquals(6, testGame1.getCurrentSchoolBoard().getTowersNumber());
        Assertions.assertEquals(4, testGame1.getCloud(0).amount());
    }

    @Test
    public void checkFillIslands(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int motherNaturePose = testGame.getIslandList().getMotherNatureIndex();
        int opposedToMotherNature = (motherNaturePose+6)%12;
        int randomPose1 = (motherNaturePose+3)%12;
        int randomPose2 = (motherNaturePose+9)%12;
        int randomPose3 = (motherNaturePose+2)%12;

        Assertions.assertTrue(testGame.getIslandList().getByIndex(motherNaturePose).getStudents().isEmpty());
        Assertions.assertTrue(testGame.getIslandList().getByIndex(opposedToMotherNature).getStudents().isEmpty());

        Assertions.assertEquals(1, testGame.getIslandList().getByIndex(randomPose1).getStudents().size());
        Assertions.assertEquals(1, testGame.getIslandList().getByIndex(randomPose2).getStudents().size());
        Assertions.assertEquals(1, testGame.getIslandList().getByIndex(randomPose3).getStudents().size());

    }

    @Test
    public void checkConquerIsland(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Student red0 = new Student(DiskColor.RED);
        Student red1 = new Student(DiskColor.RED);
        int motherNaturePose = testGame.getIslandList().getMotherNatureIndex();
        testGame.getIslandList().getByIndex(motherNaturePose).insertStudent(red0);

        testGame.getCurrentSchoolBoard().insertToEntrance(red1); //illegal but checked elsewhere

        try {
            testGame.moveStudentFromEntranceToRoom(red1);
        } catch (NotValidSwap e) {
            e.printStackTrace();
        }

        testGame.conquerIsland(motherNaturePose);

        Assertions.assertFalse(testGame.getIslandList().getByIndex(motherNaturePose).getTowers().isEmpty());
    }

    @Test
    public void checkMoveStudentFromEntranceToIsland(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Student red0 = new Student(DiskColor.RED);
        int motherNaturePose = testGame.getIslandList().getMotherNatureIndex();
        IslandTileSet testMotherNature = testGame.getIslandList().getByIndex(motherNaturePose);

        testGame.getCurrentSchoolBoard().insertToEntrance(red0); //illegal but checked elsewhere

        testGame.moveStudentFromEntranceToIsland(red0, testMotherNature);

        Assertions.assertEquals(7, testGame.getCurrentSchoolBoard().getEntrance().amount());
        Assertions.assertTrue(testMotherNature.getStudents().contains(red0));
        Assertions.assertEquals(1, testMotherNature.getStudents().size());
    }

    @Test
    public void checkMoveMotherNature(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int motherNaturePose = testGame.getIslandList().getMotherNatureIndex();
        testGame.moveMotherNature(5);
        Assertions.assertEquals((motherNaturePose+5)%12,testGame.getIslandList().getMotherNatureIndex());
    }

    @Test
    public void checkDrawFromCloud(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StudentDiskCollection cloud = testGame.getCloud(0);

        testGame.drawFromCloud(cloud);

        Assertions.assertEquals(10, testGame.getCurrentSchoolBoard().getEntrance().amount());
        Assertions.assertEquals(0, cloud.amount());

    }

    @Test
    public void checkCorrectOrder(){
        
    }

    @Test
    public void checkPayCoins(){

    }

    @Test
    public void checkCollectCoin(){

    }
}
