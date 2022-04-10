package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.HagAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class HagActionTest {

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
        input.add(color);

        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));

        testCharacter.useAction(testGame);
        Assertions.assertEquals(0, testGame.getCurrentSchoolBoard().getDiningRoom().amount());
    }

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

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

}
