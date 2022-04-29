package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.HeraldAction;
import it.polimi.ingsw2022am12.server.model.actions.MonkAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in HeraldAction class
 */
public class HeraldActionTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an HeraldCard
     * and activates it, an island is selected and inserted in the input ArrayList; in the end we check if checkInputValidity
     * returns OK and use the BardAction
     */
    @Test
    public void checkCheckInputValidityOK(){
        HeraldAction testCharacter = new HeraldAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.collectCoin();
        testGame.collectCoin();
        testGame.addCharacter(6);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_HERALD);

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable island = testGame.getIslandList().getByIndex(4);
        input.add(island);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));
        testGame.getActiveCharacterCard().getPossibleAction();
    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up; it then gets an HeraldCard
     * and activates it, an island with a wrong index is selected and inserted in the input ArrayList; in the end we check
     * if checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK(){
        MonkAction testCharacter = new MonkAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(0);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_MONK);

        ArrayList<Selectable> input = new ArrayList<>();
        IslandTileSet island = new IslandTileSet();
        island.setID(30);
        input.add(island);
        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}
