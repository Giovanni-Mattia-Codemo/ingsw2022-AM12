package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.HagAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in HagAction class
 */
public class HagActionTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an HagCard
     * and activates it, and inserts a blue student in the Dining Room; the color blue is selected and inserted in the
     * input ArrayList; in the end we check if checkInputValidity returns OK, and check if the Dining Room is empty after
     * using the HagAction
     */
    @Test
    public void checkCheckInputValidityOK(){
        HagAction testCharacter = new HagAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.collectCoin();
        testGame.collectCoin();


        Student s0 = new Student(DiskColor.BLUE);
        testGame.getCurrentSchoolBoard().insertToDiningRoom(s0);
        testGame.addCharacter(5);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_HAG);
        Selectable color = new ColorSelection(DiskColor.BLUE);
        ArrayList<Selectable> input = new ArrayList<>();
        testCharacter.getUserSelectionsMessage();
        input.add(testGame.getActiveCharacterCard());
        testCharacter.getUserSelectionsMessage();
        input.add(color);
        testCharacter.setSelectables(testGame);
        testCharacter.getUserSelectionsMessage();
        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input));

        testCharacter.useAction(testGame);
        Assertions.assertEquals(0, testGame.getCurrentSchoolBoard().getDiningRoom().amount());
    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up; it then gets an HagCard
     * and activates it, and inserts a blue student in the Dining Room; a blue student is selected and wrongly inserted in the
     * input ArrayList; in the end we check if checkInputValidity returns NOTOK
     */
    @Test
    public void checkInputValidityNOTOK(){
        HagAction testCharacter = new HagAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.collectCoin();
        testGame.collectCoin();


        Student s0 = new Student(DiskColor.BLUE);
        testGame.getCurrentSchoolBoard().insertToDiningRoom(s0);
        testGame.addCharacter(5);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_HAG);
        Selectable std = new Student(DiskColor.BLUE);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(std);
        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input));
        testGame.getActiveCharacterCard().getPossibleAction();
    }

}
