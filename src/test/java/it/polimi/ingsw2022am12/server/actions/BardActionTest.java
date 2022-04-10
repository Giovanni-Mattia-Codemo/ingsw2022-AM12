package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.DiskColor;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.BardAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class BardActionTest {

    @Test
    public void checkCheckInputValidityOK(){
        BardAction testCharacter = new BardAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(4);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_BARD);
        Assertions.assertEquals(CharacterName.CHARACTER_BARD,testGame.getActiveCharacterName());

        ArrayList<Selectable> input = new ArrayList<>();

        Student s0 = new Student(DiskColor.RED);
        Student s1 = new Student(DiskColor.BLUE);
        testGame.getCurrentSchoolBoard().getEntrance().insertElement(s0);
        testGame.getCurrentSchoolBoard().getDiningRoom().insertElement(s1);
        input.add(s0);
        input.add(s1);

        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));

        testCharacter.useAction(testGame);
        Assertions.assertTrue(testGame.getCurrentSchoolBoard().getEntrance().contains(s1));
        Assertions.assertNotNull(testGame.getCurrentSchoolBoard().getDiningRoom().getFirstStudentOfColor(DiskColor.RED));
    }

    @Test
    public void checkCheckInputValidityHALFOK1(){
        BardAction testCharacter = new BardAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(4);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_BARD);
        Assertions.assertEquals(CharacterName.CHARACTER_BARD,testGame.getActiveCharacterName());

        ArrayList<Selectable> input = new ArrayList<>();

        Student s0 = new Student(DiskColor.RED);
        testGame.getCurrentSchoolBoard().getEntrance().insertElement(s0);
        input.add(s0);

        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

    @Test
    public void checkCheckInputValidityHALFOK2(){
        BardAction testCharacter = new BardAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(4);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_BARD);
        Assertions.assertEquals(CharacterName.CHARACTER_BARD,testGame.getActiveCharacterName());

        ArrayList<Selectable> input = new ArrayList<>();

        Student s1 = new Student(DiskColor.BLUE);
        testGame.getCurrentSchoolBoard().getDiningRoom().insertElement(s1);
        input.add(s1);

        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }


    @Test
    public void checkCheckInputValidityNOTOK1(){
        BardAction testCharacter = new BardAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(4);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_BARD);
        Assertions.assertEquals(CharacterName.CHARACTER_BARD,testGame.getActiveCharacterName());

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable s0 = testGame.getTurnOrder().get(1).getEntrance().getByIndex(1);
        input.add(s0);

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

    @Test
    public void checkCheckInputValidityNOTOK2(){
        BardAction testCharacter = new BardAction();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.addCharacter(4);

        testGame.collectCoin();
        testGame.payAndSetActiveCharacter(CharacterName.CHARACTER_BARD);
        Assertions.assertEquals(CharacterName.CHARACTER_BARD,testGame.getActiveCharacterName());

        ArrayList<Selectable> input = new ArrayList<>();
        Student stdInRoomOfSecondPlayer = new Student();
        testGame.getTurnOrder().get(1).insertToDiningRoom(stdInRoomOfSecondPlayer);
        input.add(stdInRoomOfSecondPlayer);

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

}
