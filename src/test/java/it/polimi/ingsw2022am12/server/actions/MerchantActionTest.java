package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MerchantAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MerchantActionTest {

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
        testCharacter.useAction(testGame);
        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));
    }

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

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}
