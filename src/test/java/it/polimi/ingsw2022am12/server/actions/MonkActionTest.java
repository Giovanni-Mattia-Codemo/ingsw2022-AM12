package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MonkAction;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMonk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in MonkAction class
 */
public class MonkActionTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; a student from the MonkCard and the Island with MotherNature on it are selected
     * and placed in the input ArrayList, then we check if checkInputValidity returns OK, and we use the action
     */
    @Test
    public void checkCheckInputValidityOK(){
        MonkAction testCharacter = new MonkAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(0);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_MONK);

        Student student = ((CharacterMonk)testGame.getActiveCharacterCard()).getStudents().getByIndex(0);
        int motherNatureIndex = testGame.getIslandList().getMotherNatureIndex();
        Selectable motherNatureIsland = testGame.getIslandList().getByIndex(motherNatureIndex);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(student);
        input.add(motherNatureIsland);

        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));

        testCharacter.useAction(testGame);
        Assertions.assertTrue(testGame.getIslandList().getByIndex(motherNatureIndex).getStudentCollection().contains(student));

    }

    /**
     * checkCheckInputValidityHALFOK1 creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; a student from the MonkCard is selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns HALFOK
     */
    @Test
    public void checkCheckInputValidityHALFOK1(){
        MonkAction testCharacter = new MonkAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(0);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_MONK);

        Selectable s0 = ((CharacterMonk)testGame.getActiveCharacterCard()).getStudents().getByIndex(0);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(s0);
        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityHALFOK2 creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; the Island with MotherNature on it is selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns HALFOK
     */
    @Test
    public void checkCheckInputValidityHALFOK2(){
        MonkAction testCharacter = new MonkAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(0);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_MONK);

        int motherNatureIndex = testGame.getIslandList().getMotherNatureIndex();
        Selectable motherNatureIsland = testGame.getIslandList().getByIndex(motherNatureIndex);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(motherNatureIsland);

        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK1 creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; a new student wrongly created and the Island with MotherNature on it are selected
     * and placed in the input ArrayList, then we check if checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK1(){
        MonkAction testCharacter = new MonkAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(0);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_MONK);

        Selectable s0 = new Student(DiskColor.BLUE);
        int motherNatureIndex = testGame.getIslandList().getMotherNatureIndex();
        Selectable motherNatureIsland = testGame.getIslandList().getByIndex(motherNatureIndex);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(s0);
        input.add(motherNatureIsland);

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK2 creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; no input is selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK2(){
        MonkAction testCharacter = new MonkAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(0);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_MONK);
        ArrayList<Selectable> input = new ArrayList<>();

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}
