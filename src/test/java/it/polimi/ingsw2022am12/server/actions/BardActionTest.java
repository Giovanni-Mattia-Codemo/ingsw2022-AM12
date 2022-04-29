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

/**
 * Class used to test the methods in BardAction class
 */
public class BardActionTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then activates the
     * BardCard; it places two students, one in the Dining Room and one in the Entrance. It puts them in the input ArrayList,
     * checks if checkInputValidity returns OK and uses the Action
     *
     */
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

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));

        testCharacter.useAction(testGame);
        Assertions.assertTrue(testGame.getCurrentSchoolBoard().getEntrance().contains(s1));
        Assertions.assertNotNull(testGame.getCurrentSchoolBoard().getDiningRoom().getFirstStudentOfColor(DiskColor.RED));
    }

    /**
     * checkCheckInputValidityHALFOK1 creates an instance of the game with two players and sets it up; it then activates the
     * BardCard; it places one student in the Entrance. It puts them in the input ArrayList, and checks if
     * checkInputValidity returns HALFOK
     *
     */
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

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityHALFOK2 creates an instance of the game with two players and sets it up; it then activates the
     * BardCard; it places one student in the Dining Room. It puts them in the input ArrayList, and checks if
     * checkInputValidity returns HALFOK
     *
     */
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

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.HALFOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK1 creates an instance of the game with two players and sets it up; it then activates the
     * BardCard; it gets one student from the Entrance of the second player and puts it the input ArrayList, and checks if
     * checkInputValidity returns NOTOK
     *
     */

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

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

    /**
     * checkCheckInputValidityNOTOK2 creates an instance of the game with two players and sets it up; it then activates the
     * BardCard; it gets one student from the Dining Room of the second player and puts it the input ArrayList, and checks if
     * checkInputValidity returns NOTOK
     *
     */
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

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }

}
