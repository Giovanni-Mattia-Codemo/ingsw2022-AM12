package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.PrincessAction;
import it.polimi.ingsw2022am12.server.model.characters.CharacterPrincess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PrincessActionTest {

    @Test
    public void checkCheckInputValidityOK() {
        PrincessAction testCharacter = new PrincessAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(2);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_PRINCESS);
        Assertions.assertEquals(CharacterName.CHARACTER_PRINCESS, testGame.getActiveCharacterName());

        Selectable std = ((CharacterPrincess) testGame.getActiveCharacterCard()).getStudents().getByIndex(0);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(std);

        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));
        testCharacter.useAction(testGame);

        Assertions.assertEquals(1, testGame.getCurrentSchoolBoard().getDiningRoom().getAllStudents().size());
    }

    @Test
    public void checkCheckInputValidityNOTOK(){
        PrincessAction testCharacter = new PrincessAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(2);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_PRINCESS);
        Assertions.assertEquals(CharacterName.CHARACTER_PRINCESS, testGame.getActiveCharacterName());

        Selectable std = new Student(DiskColor.RED);
        ArrayList<Selectable> input = new ArrayList<>();
        input.add(std);

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}