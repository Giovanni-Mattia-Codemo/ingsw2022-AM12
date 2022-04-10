package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.DrawFromCloud;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DrawFromCloudTest {

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
        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));

        testMove.useAction(testGame);
        Assertions.assertFalse(testGame.checkIfCloudDrawableByID(0));
        Assertions.assertEquals(0, testGame.getCloud(0).amount());
    }

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
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }
}
