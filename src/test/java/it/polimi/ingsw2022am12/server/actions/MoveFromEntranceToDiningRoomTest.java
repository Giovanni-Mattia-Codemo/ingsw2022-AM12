package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MoveFromEntranceToDiningRoom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MoveFromEntranceToDiningRoomTest {

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

        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));

        testMove.useAction(testGame);
        Assertions.assertEquals(initialStudentsInEntranceOfColorRed-1, testGame.getCurrentSchoolBoard().getEntrance().getByColor(DiskColor.RED));
        Assertions.assertEquals(1, testGame.getCurrentSchoolBoard().getDiningRoom().getByColor(DiskColor.RED));

    }

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

        Assertions.assertEquals(ActionStep.HALFOK, testMove.checkInputValidity(input, testGame));

    }

    @Test
    public void checkCheckInputValidityHALFOK2(){
        MoveFromEntranceToDiningRoom testMove = new MoveFromEntranceToDiningRoom();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        input.add(testGame.getCurrentSchoolBoard().getDiningRoom());
        Assertions.assertEquals(ActionStep.HALFOK, testMove.checkInputValidity(input, testGame));
    }

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
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }

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

        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));

    }

}
