package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MerchantAction;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMerchant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in MerchantAction class
 */
public class MerchantActionTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an MerchantCard
     * and activates it, the color pink is selected and inserted in the input ArrayList; in the end we check if
     * checkInputValidity returns OK, and use the MerchantAction
     */
    @Test
    public void checkCheckInputValidityOK(){
        MerchantAction testCharacter = new MerchantAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.collectCoin();
        testGame.collectCoin();
        testGame.addCharacter(11);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_MERCHANT);

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable color = new ColorSelection(DiskColor.PINK);
        input.add(color);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));
        ((CharacterMerchant)testGame.getActiveCharacterCard()).getColor();
        ((CharacterMerchant)testGame.getActiveCharacterCard()).setWasUsed(true);
    }

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an MerchantCard
     * and activates it, a yellow student is wrongly selected and inserted in the input ArrayList; in the end we check if
     * checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK(){
        MerchantAction testCharacter = new MerchantAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
        testGame.collectCoin();
        testGame.collectCoin();
        testGame.addCharacter(11);
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_MERCHANT);

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable color = new Student(DiskColor.YELLOW);
        input.add(color);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
        ((CharacterMerchant)testGame.getActiveCharacterCard()).setColor(DiskColor.PINK);
        testGame.getActiveCharacterCard().setWasUsed(false);
    }
}
