package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.PrincessAction;
import it.polimi.ingsw2022am12.server.model.characters.CharacterPrincess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in PrincessAction class
 */
public class PrincessActionTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up, and then creates a new
     * PrincessAction; it pays and activates the PrincessCard, and gets a StudentDisk from it. The Student is put in the
     * input ArrayList; in the end we check if the checkInputValidity returns OK, and if the student has been moved
     * correctly to the dining room
     */
    @Test
    public void checkCheckInputValidityOK() {
        PrincessAction testCharacter = new PrincessAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(2);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_PRINCESS);
        Assertions.assertEquals(CharacterName.CHARACTER_PRINCESS, testGame.getActiveCharacterName());

        Selectable std = ((CharacterPrincess) testGame.getActiveCharacterCard()).getStudents().getByIndex(0);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(std);
        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));
        testCharacter.useAction(testGame);
        Assertions.assertEquals(1, testGame.getCurrentSchoolBoard().getDiningRoom().getAllStudents().size());
        testGame.getActiveCharacterCard().getPossibleAction();
        ((CharacterPrincess)testGame.getActiveCharacterCard()).setWasUsed(true, testGame);
        ((CharacterPrincess)testGame.getActiveCharacterCard()).setWasUsed(false, testGame);
    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up, and then creates a new
     * PrincessAction; it pays and activates the PrincessCard, but creates a new student and puts it in the input array.
     * In the end we check if checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK(){
        PrincessAction testCharacter = new PrincessAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(2);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_PRINCESS);
        Assertions.assertEquals(CharacterName.CHARACTER_PRINCESS, testGame.getActiveCharacterName());

        Selectable std = new Student(DiskColor.RED);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(std);
        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}