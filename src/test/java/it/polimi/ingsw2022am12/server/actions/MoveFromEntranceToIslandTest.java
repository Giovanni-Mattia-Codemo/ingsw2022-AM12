package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MoveFromEntranceToIsland;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MoveFromEntranceToIslandTest {

    @Test
    public void checkCheckInputValidityOK(){
        MoveFromEntranceToIsland testMove = new MoveFromEntranceToIsland();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
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
        int initialStudentsOnIslandOfColorRed = testGame.getIslandList().getByIndex(4).getStudentCollection().getByColor(DiskColor.RED);

        IslandTileSet destination = testGame.getIslandList().getByIndex(4);
        input.add(destination);

        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));

        testMove.useAction(testGame);
        Assertions.assertEquals(initialStudentsInEntranceOfColorRed-1, testGame.getCurrentSchoolBoard().getEntrance().getByColor(DiskColor.RED));
        Assertions.assertEquals(initialStudentsOnIslandOfColorRed+1, testGame.getIslandList().getByIndex(4).getStudentCollection().getByColor(DiskColor.RED));
    }

    @Test
    public void checkCheckInputValidityHALFOK1(){
        MoveFromEntranceToIsland testMove = new MoveFromEntranceToIsland();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
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
        MoveFromEntranceToIsland testMove = new MoveFromEntranceToIsland();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        IslandTileSet destination = testGame.getIslandList().getByIndex(4);
        input.add(destination);

        Assertions.assertEquals(ActionStep.HALFOK, testMove.checkInputValidity(input, testGame));
    }

    @Test
    public void checkCheckInputValidityNOTOK1(){
        MoveFromEntranceToIsland testMove = new MoveFromEntranceToIsland();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        IslandTileSet testSet = new IslandTileSet();
        input.add(testSet);
        testSet.setID(30);

        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));

    }

    @Test
    public void checkCheckInputValidityNOTOK2(){ //Checked both with testSet and without it
        MoveFromEntranceToIsland testMove = new MoveFromEntranceToIsland();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        Student sRed = new Student(DiskColor.RED);
        input.add(sRed);

        IslandTileSet testSet = new IslandTileSet();
        input.add(testSet);
        testSet.setID(30);

        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }
}
