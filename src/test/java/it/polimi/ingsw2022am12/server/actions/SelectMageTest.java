package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Mage;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.SelectMage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in SelectMage class
 */
public class SelectMageTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets a mage
     * from the array of four Available Mages, using a correct index, and inserts it in an ArrayList of inputs. It checks
     * if checkInputValidity on the new SelectMage move created returns OK, then it uses the action. In the end it checks
     * if we selected the correct mage, and if the available mages are three
     */
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
        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));
        int numOfAvailableMagesBefore = testGame.getAvailableMages().size();
        testMove.useAction(testGame);
        int numOfAvailableMagesAfter = testGame.getAvailableMages().size();
        Assertions.assertEquals(testGame.getTurnOrder().get(0).getMage(), mage);
        Assertions.assertEquals(numOfAvailableMagesBefore-1, numOfAvailableMagesAfter);


        input.clear();

    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up; it then gets a mage
     * from the array of Available Mages, using a wrong index, and inserts it in an ArrayList of inputs. It checks
     * if the checkInputValidity returns NOTOK, and cleans the input
     */
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

        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));

        input.remove(mage);
        Selectable rightMage = testGame.getAvailableMages().get(0);
        input.add(rightMage);
        SelectMage testSelectMage = new SelectMage();

        testMove.checkInputValidity(input, testGame);
        testMove.useAction(testGame);
        Assertions.assertEquals(testGame.getTurnOrder().get(0).getMage(), rightMage);

        testSelectMage.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testSelectMage.checkInputValidity(input, testGame));

    }
}
