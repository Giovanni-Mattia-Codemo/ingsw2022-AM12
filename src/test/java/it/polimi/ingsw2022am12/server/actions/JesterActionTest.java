package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.JesterAction;
import it.polimi.ingsw2022am12.server.model.characters.CharacterJester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in JesterAction class
 */
public class JesterActionTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an JesterCard
     * and activates it; a student from the JesterCard, and one from the Entrance are selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns OK, and we use the action; in the end we check if swap has been done correctly
     *
     */
    @Test
    public void checkCheckInputValidityOK(){
        JesterAction testCharacter = new JesterAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.addCharacter(10);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_JESTER);

        ArrayList<Selectable> input = new ArrayList<>();
        testCharacter.getUserSelectionsMessage();
        input.add(testGame.getActiveCharacterCard());
        testCharacter.getUserSelectionsMessage();
        Student stdJester = ((CharacterJester)testGame.getActiveCharacterCard()).getStudents().getByIndex(0);
        input.add(stdJester);
        Student stdEntrance = testGame.getCurrentSchoolBoard().getEntrance().getByIndex(0);
        testCharacter.getUserSelectionsMessage();
        input.add(stdEntrance);

        testCharacter.setSelectables(testGame);
        testCharacter.getUserSelectionsMessage();
        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input));

        testCharacter.useAction(testGame);
        Assertions.assertTrue(testGame.getCurrentSchoolBoard().getEntrance().contains(stdJester));
        Assertions.assertTrue(((CharacterJester)testGame.getActiveCharacterCard()).getStudents().contains(stdEntrance));
    }

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an JesterCard
     * and activates it; a student from the JesterCard is  selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns HALFOK
     *
     */
    @Test
    public void checkCheckInputValidityHALFOK1(){
        JesterAction testCharacter = new JesterAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.addCharacter(10);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_JESTER);

        ArrayList<Selectable> input = new ArrayList<>();
        input.add(testGame.getActiveCharacterCard());
        Student stdJester = ((CharacterJester)testGame.getActiveCharacterCard()).getStudents().getByIndex(0);
        input.add(stdJester);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input));
    }

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an JesterCard
     * and activates it; a student from the Entrance is  selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns HALFOK
     *
     */
    @Test
    public void checkCheckInputValidityNOTOK3(){
        JesterAction testCharacter = new JesterAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.addCharacter(10);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_JESTER);

        ArrayList<Selectable> input = new ArrayList<>();
        Student stdEntrance = testGame.getCurrentSchoolBoard().getEntrance().getByIndex(0);
        input.add(stdEntrance);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input));
    }

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an JesterCard
     * and activates it; a new student created and a student from the Entrance are selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns NOTOK
     *
     */
    @Test
    public void checkInputValidityNOTOK1(){
        JesterAction testCharacter = new JesterAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.addCharacter(10);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_JESTER);

        ArrayList<Selectable> input = new ArrayList<>();
        Student stdJester = new Student(DiskColor.GREEN);
        input.add(stdJester);
        Student stdEntrance = testGame.getCurrentSchoolBoard().getEntrance().getByIndex(0);
        input.add(stdEntrance);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input));
        testGame.getActiveCharacterCard().getPossibleAction();
    }

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an JesterCard
     * and activates it; a student from the second player's Entrance is selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns NOTOK
     */
    @Test
    public void checkInputValidityNOTOK2(){
        JesterAction testCharacter = new JesterAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.addCharacter(10);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_JESTER);

        ArrayList<Selectable> input = new ArrayList<>();
        Student stdEntrance = testGame.getTurnOrder().get(1).getEntrance().getByIndex(0);
        input.add(stdEntrance);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input));
        testGame.getActiveCharacterCard().setWasUsed(false);
    }
}
