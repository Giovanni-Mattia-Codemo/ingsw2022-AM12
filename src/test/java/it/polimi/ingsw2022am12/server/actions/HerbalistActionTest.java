package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.HerbalistAction;
import it.polimi.ingsw2022am12.server.model.characters.CharacterHerbalist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


/**
 * Class used to test the methods in HerbalistAction class
 */
public class HerbalistActionTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an HerbalistCard
     * and activates it; a NoEntry and the Island with MotherNature on it are selected and placed in the input ArrayList,
     * then we check if checkInputValidity returns OK, and we use the action
     *
     */
    @Test
    public void checkCheckInputValidityOK(){
        HerbalistAction testCharacter = new HerbalistAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.collectCoin();
        testGame.addCharacter(3);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_HERBALIST);

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable noEntry = ((CharacterHerbalist)testGame.getActiveCharacterCard()).getNoEntryCollection().getFirstNoEntry();
        input.add(noEntry);
        int motherNatureIndex = testGame.getIslandList().getMotherNatureIndex();
        Selectable motherNatureIsland = testGame.getIslandList().getByIndex(motherNatureIndex);
        input.add(motherNatureIsland);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));

        testCharacter.useAction(testGame);
        Assertions.assertFalse(testGame.getIslandList().getByIndex(motherNatureIndex).getNoEntries().isEmpty());
    }

    /**
     * checkCheckInputValidityHALFOK1 creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; a NoEntry is selected and placed in the input
     * ArrayList, then we check if checkInputValidity returns HALFOK
     *
     */
    @Test
    public void checkCheckInputValidityHALFOK1(){
        HerbalistAction testCharacter = new HerbalistAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.collectCoin();
        testGame.addCharacter(3);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_HERBALIST);

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable noEntry = ((CharacterHerbalist)testGame.getActiveCharacterCard()).getNoEntryCollection().getFirstNoEntry();
        input.add(noEntry);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityHALFOK2 creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; an island with MotherNature is selected and placed in the input
     * ArrayList, then we check if checkInputValidity returns HALFOK
     */
    @Test
    public void checkCheckInputValidityNOTOK3(){
        HerbalistAction testCharacter = new HerbalistAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.collectCoin();
        testGame.addCharacter(3);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_HERBALIST);

        ArrayList<Selectable> input = new ArrayList<>();
        int motherNatureIndex = testGame.getIslandList().getMotherNatureIndex();
        Selectable motherNatureIsland = testGame.getIslandList().getByIndex(motherNatureIndex);
        input.add(motherNatureIsland);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK1 creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; a student is selected and placed wrongly in the input ArrayList, then we check if
     * checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK1(){
        HerbalistAction testCharacter = new HerbalistAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.collectCoin();
        testGame.addCharacter(3);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_HERBALIST);

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable noEntry = new Student(DiskColor.BLUE);
        input.add(noEntry);
        int motherNatureIndex = testGame.getIslandList().getMotherNatureIndex();
        Selectable motherNatureIsland = testGame.getIslandList().getByIndex(motherNatureIndex);
        input.add(motherNatureIsland);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK1 creates an instance of the game with two players and sets it up; it then gets an
     * HerbalistCard and activates it; a student is selected and placed wrongly in the input ArrayList, together with an
     * island with a wrong index, then we check if checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK2(){
        HerbalistAction testCharacter = new HerbalistAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.collectCoin();
        testGame.addCharacter(3);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_HERBALIST);

        ArrayList<Selectable> input = new ArrayList<>(); //Also tested with empty input
        Selectable noEntry = new Student(DiskColor.BLUE);
        input.add(noEntry);
        IslandTileSet island = new IslandTileSet();
        island.setID(30);
        input.add(island);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}
