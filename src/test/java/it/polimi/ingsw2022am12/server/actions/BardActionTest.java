package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.controller.InputHandler;
import it.polimi.ingsw2022am12.server.model.*;
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
        testGame.getActiveCharacterCard().getPossibleAction();
        Assertions.assertEquals(CharacterName.CHARACTER_BARD,testGame.getActiveCharacterName());

        ArrayList<Selectable> input = new ArrayList<>();
        Student stdInRoomOfSecondPlayer = new Student();
        testGame.getTurnOrder().get(1).insertToDiningRoom(stdInRoomOfSecondPlayer);
        input.add(stdInRoomOfSecondPlayer);

        testCharacter.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
        testGame.getActiveCharacterCard().setWasUsed(false);
    }

    @Test
    public void testingStuff(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        int num = 2;
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        InputHandler inputHandler = new InputHandler(testGame);
        Student st = new Student(DiskColor.RED);
        System.out.println(inputHandler.addSelection(st));


        Mage mg = testGame.getAvailableMages().get(0);
        System.out.println(inputHandler.addSelection(mg));
        Mage mg2 = testGame.getAvailableMages().get(0);
        System.out.println(inputHandler.addSelection(mg2));



        for (int i = 0; i < num; i++) {
            Assistant ass = testGame.getPlayableAssistants().get(0);
            System.out.println(inputHandler.addSelection(ass));
        }

        for(int j = 0; j<num; j++){
            for (int i = 0; i < 2; i++) {
                Student st0 = testGame.getCurrentSchoolBoard().getEntrance().getByIndex(0);
                System.out.println(inputHandler.addSelection(st0));

                StudentDiskCollection diningRoom = testGame.getCurrentSchoolBoard().getDiningRoom();
                System.out.println(inputHandler.addSelection(diningRoom));
            }

            Student st1 = testGame.getCurrentSchoolBoard().getEntrance().getByIndex(0);
            System.out.println(inputHandler.addSelection(st1));
            System.out.println(inputHandler.addSelection(testGame.getIslandList().getIslandsAsSelectable().get(0)));

            ArrayList<Selectable> tmp = new ArrayList<>(testGame.getIslandList().getIslandsAsSelectable());
            tmp.removeIf(a->!testGame.checkIfIslandInRange(((IslandTileSet)a)));

            System.out.println(inputHandler.addSelection(testGame.getIslandList().getByIndex(testGame.getIslandList().getMotherNatureIndex())));
            System.out.println(inputHandler.addSelection(tmp.get(0)));

            System.out.println(inputHandler.addSelection(testGame.getDrawableClouds().get(0)));
        }
    }
}