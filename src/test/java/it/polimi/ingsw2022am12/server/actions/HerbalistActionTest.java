package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.HerbalistAction;
import it.polimi.ingsw2022am12.server.model.characters.CharacterHerbalist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HerbalistActionTest {

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

        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));

        testCharacter.useAction(testGame);
        Assertions.assertFalse(testGame.getIslandList().getByIndex(motherNatureIndex).getNoEntries().isEmpty());
    }

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

        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

    @Test
    public void checkCheckInputValidityHALFOK2(){
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

        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

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

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

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

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}
