package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Mage;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.PlayAssistant;
import it.polimi.ingsw2022am12.server.model.actions.SelectMage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SelectMageTest {

    @Test
    public void checkCheckInputValidityOK(){
        SelectMage testMove = new SelectMage();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable mage = testGame.getAvailableMages().get(0);
        input.add(mage);

        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));
        int numOfAvailableMagesBefore = testGame.getAvailableMages().size();
        testMove.useAction(testGame);
        int numOfAvailableMagesAfter = testGame.getAvailableMages().size();
        Assertions.assertEquals(testGame.getTurnOrder().get(0).getMage(), mage);
        Assertions.assertEquals(numOfAvailableMagesBefore-1, numOfAvailableMagesAfter);
    }

    @Test
    public void checkCheckInputValidityNOTOK(){
        SelectMage testMove = new SelectMage();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable mage = new Mage(7);
        input.add(mage);

        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));

        input.remove(mage);
        Selectable rightMage = testGame.getAvailableMages().get(0);
        testMove.useAction(testGame);
        Assertions.assertEquals(testGame.getTurnOrder().get(0).getMage(), rightMage);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));

    }
}
