package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MoveFromEntranceToDiningRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in MoveFromEntranceToDiningRoom class
 */
public class MoveFromEntranceToDiningRoomTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets a student
     * from the Entrance, if present, or else it inserts a new one in it. Then it adds the student, and a DiningRoom in
     * the input array, checks if checkInputValidity returns OK and uses the action
     */
    @Test
    public void checkCheckInputValidityOK(){ //also useAction

        MoveFromEntranceToDiningRoom testMove = new MoveFromEntranceToDiningRoom();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        Student sRed = new Student(DiskColor.RED);
        if(testGame.getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(DiskColor.RED).isPresent()) {
            Student stEn = testGame.getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(DiskColor.RED).get();
            input.add(stEn);
        }else {
            testGame.getCurrentSchoolBoard().insertToEntrance(sRed);
            input.add(sRed);
        }

        int initialStudentsInEntranceOfColorRed = testGame.getCurrentSchoolBoard().getEntrance().getByColor(DiskColor.RED);

        input.add(testGame.getCurrentSchoolBoard().getDiningRoom());

        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));

        testMove.useAction(testGame);
        Assertions.assertEquals(initialStudentsInEntranceOfColorRed-1, testGame.getCurrentSchoolBoard().getEntrance().getByColor(DiskColor.RED));
        Assertions.assertEquals(1, testGame.getCurrentSchoolBoard().getDiningRoom().getByColor(DiskColor.RED));

    }

    /**
     * checkCheckInputValidityHALFOK1 creates an instance of the game with two players and sets it up; it then gets a student
     * from the Entrance, if present, or else it inserts a new one in it. Then it adds the student in
     * the input array and checks if checkInputValidity returns HALFOK
     */
    @Test
    public void checkCheckInputValidityHALFOK1(){
        MoveFromEntranceToDiningRoom testMove = new MoveFromEntranceToDiningRoom();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        Student sRed = new Student(DiskColor.RED);
        if(testGame.getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(DiskColor.RED).isPresent()) {
            Student stEn = testGame.getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(DiskColor.RED).get();
            input.add(stEn);
        }else {
            testGame.getCurrentSchoolBoard().insertToEntrance(sRed);
            input.add(sRed);
        }

        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.HALFOK, testMove.checkInputValidity(input, testGame));

    }

    /**
     * checkCheckInputValidityHALFOK2 creates an instance of the game with two players and sets it up; it then inserts a
     * DiningRoom in the input array and checks if checkInputValidity returns HALFOK
     */
    @Test
    public void checkCheckInputValidityNOTOK3(){
        MoveFromEntranceToDiningRoom testMove = new MoveFromEntranceToDiningRoom();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        input.add(testGame.getCurrentSchoolBoard().getDiningRoom());
        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK1 creates an instance of the game with two players and sets it up; it then creates a
     * new student and adds it in the input array; then it checks if checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK1(){
        MoveFromEntranceToDiningRoom testMove = new MoveFromEntranceToDiningRoom();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        Student sRed = new Student(DiskColor.RED);
        input.add(sRed);

        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK2 creates an instance of the game with two players and sets it up; it then gets a student
     * from the Entrance, if present, or else it inserts a new one in it. Then it adds the student, and the DiningRoom of the
     * second player in the input array; in the end it checks if checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK2(){ //Also tested with only the notCurrentPlayerRoom and no student in input
        MoveFromEntranceToDiningRoom testMove = new MoveFromEntranceToDiningRoom();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        Student sRed = new Student(DiskColor.RED);
        if(testGame.getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(DiskColor.RED).isPresent()) {
            Student stEn = testGame.getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(DiskColor.RED).get();
            input.add(stEn);
        }else {
            testGame.getCurrentSchoolBoard().insertToEntrance(sRed);
            input.add(sRed);
        }

        StudentDiskCollection notCurrentPlayerRoom = testGame.getTurnOrder().get(1).getDiningRoom();
        input.add(notCurrentPlayerRoom);
        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));

    }

}
