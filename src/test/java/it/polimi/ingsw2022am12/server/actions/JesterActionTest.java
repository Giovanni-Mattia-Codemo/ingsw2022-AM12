package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.JesterAction;
import it.polimi.ingsw2022am12.server.model.characters.CharacterJester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JesterActionTest {

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
        Student stdJester = ((CharacterJester)testGame.getActiveCharacterCard()).getStudents().getByIndex(0);
        input.add(stdJester);
        Student stdEntrance = testGame.getCurrentSchoolBoard().getEntrance().getByIndex(0);
        input.add(stdEntrance);

        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));

        testCharacter.useAction(testGame);
        Assertions.assertTrue(testGame.getCurrentSchoolBoard().getEntrance().contains(stdJester));
        Assertions.assertTrue(((CharacterJester)testGame.getActiveCharacterCard()).getStudents().contains(stdEntrance));
    }

    @Test
    public void checkCheckInputValidityHALOK1(){
        JesterAction testCharacter = new JesterAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.addCharacter(10);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_JESTER);

        ArrayList<Selectable> input = new ArrayList<>();
        Student stdJester = ((CharacterJester)testGame.getActiveCharacterCard()).getStudents().getByIndex(0);
        input.add(stdJester);

        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

    @Test
    public void checkCheckInputValidityHALOK2(){
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

        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

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

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

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

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}
