package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MoveFromEntranceToIsland;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in MoveFromEntranceToIsland class
 */
public class MoveFromEntranceToIslandTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets a student
     * from the Entrance, if present, or else it inserts a new one in it. Then it adds the student, and destination island in
     * the input array, checks if checkInputValidity returns OK and uses the action
     */
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
        testMove.getUserSelectionsMessage();
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
        testMove.getUserSelectionsMessage();
        input.add(destination);
        testMove.getUserSelectionsMessage();
        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));

        testMove.useAction(testGame);
        testMove.getUpdates(testGame);
        Assertions.assertEquals(initialStudentsInEntranceOfColorRed-1, testGame.getCurrentSchoolBoard().getEntrance().getByColor(DiskColor.RED));
        Assertions.assertEquals(initialStudentsOnIslandOfColorRed+1, testGame.getIslandList().getByIndex(4).getStudentCollection().getByColor(DiskColor.RED));
    }

    /**
     * checkCheckInputValidityHALFOK! creates an instance of the game with two players and sets it up; it then gets a student
     * from the Entrance, if present, or else it inserts a new one in it. Then it adds the student in
     * the input array, and checks if checkInputValidity returns HALFOK
     */
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
        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.HALFOK, testMove.checkInputValidity(input, testGame));

    }

    /**
     * checkCheckInputValidityHALFOK2 creates an instance of the game with two players and sets it up; then it adds the
     * destination island in the input array, and checks if checkInputValidity returns HALFOK
     */
    @Test
    public void checkCheckInputValidityNOTOK3(){
        MoveFromEntranceToIsland testMove = new MoveFromEntranceToIsland();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        IslandTileSet destination = testGame.getIslandList().getByIndex(4);
        input.add(destination);
        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK1 creates an instance of the game with two players and sets it up; then it adds a destination
     * island with a wrong index in the input array; in the end it checks if checkInputValidity returns NOTOK
     */
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
        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));

    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up; then it adds a
     * newly created student, and a destination island with a wrong index in the input array; in the end it checks if
     * checkInputValidity returns NOTOK
     */
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
