package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.DrawFromCloud;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in DrawFromCloud class
 */
public class DrawFromCloudTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets a cloud
     * from the array of Clouds, using a correct index, and inserts it in an ArrayList of inputs. It checks
     * if checkInputValidity on the new DrawFromCloud move created returns OK, then it uses the action. In the end it
     * checks if the island is not drawable anymore, and if the number of Students in the cloud is zero
     */
    @Test
    public void checkCheckInputValidityOK(){
        DrawFromCloud testMove = new DrawFromCloud();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable cloud = testGame.getCloud(0);
        input.add(cloud);
        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input));

        testMove.useAction(testGame);
        Assertions.assertFalse(testGame.checkIfCloudDrawableByID(0));
        Assertions.assertEquals(0, testGame.getCloud(0).amount());
    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up; it then creates a new
     * empty Cloud and inserts it the input ArrayList, and checks if checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK(){
        DrawFromCloud testMove = new DrawFromCloud();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable cloud = new StudentDiskCollection();
        input.add(cloud);
        testMove.setSelectables(testGame);
        testMove.getUpdates(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input));
    }

}
