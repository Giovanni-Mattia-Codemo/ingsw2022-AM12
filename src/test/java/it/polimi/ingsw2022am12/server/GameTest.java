package it.polimi.ingsw2022am12.server;

import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterBeggar;
import it.polimi.ingsw2022am12.server.model.characters.CharacterJester;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMonk;
import it.polimi.ingsw2022am12.server.model.characters.CharacterPrincess;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import org.junit.jupiter.api.Assertions;

/**
 * Class used to test the methods in the Game class
 */
public class GameTest {


    /**
     * checkSetUp creates an instance of the game for four players and sets it up, checking if the Characters are created
     * correctly, then it checks if the team and schoolBoards assignment has been done correctly
     */
    @Test
    public void checkSetUp()  {
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Pippo");
        nicks.add("Pluto");
        nicks.add("Bob");
        nicks.add("Pier");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        //check that there are no characters with the same name
        Map<CharacterName, CharacterCard> tmp= new HashMap<>();

         ArrayList<CharacterCard> chars = testGame.getAvailableCharacters();

         for(CharacterCard c: chars){
            if(tmp.put(c.getName(), c) != null){
               Assertions.fail();
            }
         }

         Assertions.assertEquals(testGame.getTeams().get(0).getSchoolBoards().get(0),testGame.getSchoolBoardByNick("Pippo"));
         Assertions.assertEquals(testGame.getTeams().get(1).getSchoolBoards().get(0),testGame.getSchoolBoardByNick("Pluto"));
         Assertions.assertEquals(testGame.getTeams().get(0).getSchoolBoards().get(1),testGame.getSchoolBoardByNick("Bob"));
         Assertions.assertEquals(testGame.getTeams().get(1).getSchoolBoards().get(1),testGame.getSchoolBoardByNick("Pier"));


    }

    /**
     * checkFillIslands creates an instance of the game for two players and sets it up, checking the island where MotherNature is,
     * and the one opposed to it are empty, while the other ten islands (we chose three islands randomly) contain one student
     */
    @Test
    public void checkFillIslands(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int motherNaturePose = testGame.getIslandList().getMotherNatureIndex();
        int opposedToMotherNature = (motherNaturePose+6)%12;
        int randomPose1 = (motherNaturePose+3)%12;
        int randomPose2 = (motherNaturePose+9)%12;
        int randomPose3 = (motherNaturePose+2)%12;

        Assertions.assertTrue(testGame.getIslandList().getByIndex(motherNaturePose).getStudents().isEmpty());
        Assertions.assertTrue(testGame.getIslandList().getByIndex(opposedToMotherNature).getStudents().isEmpty());

        Assertions.assertEquals(1, testGame.getIslandList().getByIndex(randomPose1).getStudents().size());
        Assertions.assertEquals(1, testGame.getIslandList().getByIndex(randomPose2).getStudents().size());
        Assertions.assertEquals(1, testGame.getIslandList().getByIndex(randomPose3).getStudents().size());

    }

    /**
     * checkConquerIsland creates an instance of the game for two players and sets it up, then places a red student on the
     * island where MotherNature is found, and another red student in the Dining Room of the first player. In the end it
     * checks if the island with MotherNature on it is not devoid of towers, since the first player has conquered it
     */

    @Test
    public void checkConquerIsland(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);

        testGame.setUp();

        Student red0 = new Student(DiskColor.RED);
        Student red1 = new Student(DiskColor.RED);
        int motherNaturePose = testGame.getIslandList().getMotherNatureIndex();
        testGame.getIslandList().getByIndex(motherNaturePose).insertStudent(red0);

        testGame.getCurrentSchoolBoard().insertToEntrance(red1); //illegal but checked elsewhere


        testGame.moveStudentFromEntranceToRoom(red1.getColor());


        testGame.conquerIsland(motherNaturePose);

        Assertions.assertFalse(testGame.getIslandList().getByIndex(motherNaturePose).getTowers().isEmpty());
    }

    /**
     * checkMoveStudentFromEntranceToIsland creates an instance of the game for two players and sets it up, then places a
     * red student in the Entrance, even if it's already full (Illegal action but checked elsewhere), then it moves it on the
     * island where MotherNature is found; in the end it checks if the island with MotherNature on it contains the red student,
     * and the Entrance now has only seven students
     */
    @Test
    public void checkMoveStudentFromEntranceToIsland(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Student red0 = new Student(DiskColor.RED);
        int motherNaturePose = testGame.getIslandList().getMotherNatureIndex();
        IslandTileSet testMotherNature = testGame.getIslandList().getByIndex(motherNaturePose);

        testGame.getCurrentSchoolBoard().insertToEntrance(red0);
        testGame.moveStudentFromEntranceToIsland(red0.getColor(), testMotherNature.getID());

        Assertions.assertTrue(testGame.checkIfIsCurrentPlayer(testGame.getCurrentSchoolBoard().getNick()));
        Assertions.assertEquals(7, testGame.getCurrentSchoolBoard().getEntrance().amount());
        Assertions.assertTrue(testMotherNature.getStudentCollection().getFirstStudentOfColor(red0.getColor()).isPresent());
        Assertions.assertEquals(1, testMotherNature.getStudents().size());
    }

    /**
     * checkMoveMotherNature creates an instance of the game for two players and sets it up, then the index of an island,
     * and places MotherNature there. In the end it checks if the selected index and the index of Mother Nature are the same
     */
    @Test
    public void checkMoveMotherNature(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        IslandTileSet island = testGame.getIslandList().getByIndex(3);
        testGame.moveMotherNature(island.getID());

        Assertions.assertEquals(testGame.getIslandList().getMotherNatureIndex(), island.getID());
    }

    /**
     * checkDrawFromCloud creates an instance of the game for two players and sets it up, then gets the first cloud and
     * draws its three students. In the end it checks if that cloud is empty, while the Entrance contains three more students
     */
    @Test
    public void checkDrawFromCloud(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StudentDiskCollection cloud = testGame.getCloud(0);

        testGame.drawFromCloud(cloud.getID());

        Assertions.assertEquals(10, testGame.getCurrentSchoolBoard().getEntrance().amount());
        Assertions.assertEquals(0, cloud.amount());

    }

    /**
     * checkDrawFromCloud creates an instance of the game for two players and sets it up, then has the two players play
     * their assistants (the second player must wait an endTurn() from the first); then it checks if the player who played
     * the assistant with the lowest value is the first in the next turn order
     */
    @Test
    public void checkCorrectOrder(){

        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Leaving SetupStrategy
        testGame.endTurn();
        testGame.getCurrentStrategy().getValidActions(testGame);
        testGame.endTurn();
        //Entering PlanningStrategy

        SchoolBoard schoolBoard1 = testGame.getCurrentSchoolBoard();
        ArrayList<Assistant> playableAssistants = schoolBoard1.getRemainingAssistants();
        int assistantPower = playableAssistants.get(7).getTurnPower();
        testGame.playAssistant(assistantPower);
        testGame.getCurrentStrategy().getValidActions(testGame);

        testGame.endTurn();

        SchoolBoard schoolBoard2 = testGame.getCurrentSchoolBoard();
        testGame.playAssistant(1);

        testGame.endTurn();

        Assertions.assertEquals(schoolBoard1, testGame.getTurnOrder().get(1));
        Assertions.assertEquals(schoolBoard2, testGame.getTurnOrder().get(0));
        Assertions.assertFalse(testGame.getIsLastRoundFlag());

        Assertions.assertFalse(testGame.movedAllDisksThisTurn());

        testGame.endTurn();
        testGame.getCurrentStrategy().getValidActions(testGame);
        testGame.endTurn();

        testGame.endGame();

    }

    /**
     * checkPayAndCollectCoin creates an instance of the game for two players and sets it up, then checks if the free
     * coins are 20 - numberOfPlayers, alas 18. Then it collects another coin, checking if only 17 free remain; in the end
     * the first player pays one of his two coins and puts it back in the free coins
     */
    @Test
    public void checkPayAndCollectCoin() {

        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        try {
            testGame.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(18, testGame.getFreeCoins().size());

        testGame.collectCoin();

        Assertions.assertEquals(17, testGame.getFreeCoins().size());

        testGame.payCoins(1);

        Assertions.assertEquals(1, testGame.getCurrentSchoolBoard().getNumOfCoins());
        Assertions.assertEquals(18, testGame.getFreeCoins().size());
    }

    /**
     * checkMoveStudentFromCardToIsland creates an instance of the game for two players and sets it up, then places a
     * red student on the MonkCard, then it moves it on the first island; in the end it checks if the island
     * contains that student while the MonkCard is empty
     */
    @Test
    public void checkMoveFromCardToIsland(){

        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        Student s0 = new Student(DiskColor.RED);
        CharacterMonk characterMonk = new CharacterMonk();
        testGame.forceSetActiveCharacter(characterMonk);
        characterMonk.getStudents().insertElement(s0);
        testGame.moveStudentFromCardToIsland(DiskColor.RED, 0);

        Assertions.assertEquals(0, characterMonk.getStudents().amount());
        Assertions.assertTrue(testGame.getIslandList().getByIndex(0).getStudentCollection().contains(s0));


    }

    /**
     * checkMoveStudentFromCardToRoom creates an instance of the game for two players and sets it up, then places a
     * red student on the PrincessCard, then it moves it in the Dining Room; in the end it checks if the Dining Room
     * has one student of that color while the PrincessCard is empty
     */
    @Test
    public void checkMoveStudentFromCardToRoom(){

        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();


        Student s0 = new Student(DiskColor.RED);
        CharacterPrincess characterPrincess = new CharacterPrincess();
        testGame.forceSetActiveCharacter(characterPrincess);
        characterPrincess.getStudents().insertElement(s0);
        testGame.moveStudentFromCardToRoom(DiskColor.RED);

        Assertions.assertEquals(0, characterPrincess.getStudents().amount());
        Assertions.assertEquals(1, testGame.getCurrentSchoolBoard().getStudentsInRoomByColor(DiskColor.RED));

    }

    /**
     * checkMoveStudentFromEntranceToRoom creates an instance of the game for two players and sets it up, then places a
     * red student in the Entrance, even if it's already full (Illegal action but checked elsewhere), then it moves it in
     * the Dining Room; in the end it checks if the Dining Room has one student of that color
     * and the Entrance now has only seven students
     */
    @Test
    public void checkMoveStudentFromEntranceToRoom(){

        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        Student red0 = new Student(DiskColor.RED);
        testGame.getCurrentSchoolBoard().insertToEntrance(red0); //illegal but checked elsewhere

        testGame.moveStudentFromEntranceToRoom(DiskColor.RED);

        Assertions.assertEquals(7, testGame.getCurrentSchoolBoard().getEntrance().amount());
        Assertions.assertEquals(1, testGame.getCurrentSchoolBoard().getStudentsInRoomByColor(DiskColor.RED));

    }

    /**
     * checkJesterSwap creates an instance of the game for two players and sets it up, then activates forcibly the JesterCard,
     * placing a student on it, and one in the Entrance; after completing the swap, it checks if it has been done correctly
     * by checking the content of the Entrance and the Jester Card
     */
    @Test
    public void checkJesterSwap(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.assignTeams();

        Student s0 = new Student(DiskColor.RED);
        Student s1 = new Student(DiskColor.BLUE);
        CharacterJester characterJester = new CharacterJester();
        testGame.forceSetActiveCharacter(characterJester);
        characterJester.getStudents().insertElement(s0);
        testGame.getCurrentSchoolBoard().getEntrance().insertElement(s1);

        testGame.jesterSwap(DiskColor.RED, DiskColor.BLUE);

        Assertions.assertTrue(testGame.getCurrentSchoolBoard().getEntrance().contains(s0));
        Assertions.assertTrue(characterJester.getStudents().contains(s1));
        Assertions.assertFalse(testGame.getCurrentSchoolBoard().getEntrance().contains(s1));
        Assertions.assertFalse(characterJester.getStudents().contains(s0));

    }

    /**
     * checkFillClouds creates an instance of the game for two players and sets it up, then checks if the first cloud
     * contains the right amount of students (three); then it creates an instance of the game for three players and sets
     * it up, and checks if the first cloud contains the right amount of students (four)
     */
    @Test
    public void checkFillClouds(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("NICK1");
        nicks.add("NICK2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        int cloudID = testGame.getCloud(0).getID();
        Assertions.assertEquals(3, testGame.getCloud(0).amount());
        Assertions.assertTrue(testGame.checkIfCloudDrawableByID(cloudID));

        nicks.add("NICK3");

        Game testGame1 = new Game(nicks, false);
        testGame1.setUp();
        Assertions.assertEquals(4, testGame1.getCloud(0).amount());



    }

    /**
     * checkCheckIfIslandInRange creates an instance of the game for two players and sets it up, then plays an Assistant
     * with range 3, and selects an island that is 4 tiles distant from Mother Nature, so hat CheckIfIslandInRange returns
     * false; then it activates the BeggarCard, whose powers consists in expanding the range of an Assistant, and now
     * checkIfIslandInRange returns true
     */
    @Test
    public void checkCheckIfIslandInRange(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        testGame.playAssistant(6);//has range 3
        IslandTileList tmp = testGame.getIslandList();
        testGame.moveMotherNature(0);
        IslandTileSet is0 = tmp.getByIndex(tmp.getMotherNatureIndex()+4);

        Assertions.assertFalse(testGame.checkIfIslandInRange(is0));

        CharacterBeggar beg= new CharacterBeggar();
        testGame.forceSetActiveCharacter(beg);

        Assertions.assertTrue(testGame.checkIfIslandInRange(is0));
    }

    /**
     * checkPayAndSetActiveCharacter creates an instance of the game for two players and sets it up, and collects a few
     * coins; then it activates and pays the first character in the deck, checking if the paid coins have been removed
     * correctly and the selected character is the chosen one
     */
    @Test
    public void checkPayAndSetActiveCharacter(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();


        testGame.collectCoin();
        testGame.collectCoin();
        testGame.collectCoin();

        CharacterCard tmp = testGame.getAvailableCharacters().get(0);
        testGame.payAndSetActiveCharacter(tmp.getName());

        Assertions.assertEquals(5-tmp.getCost(), testGame.getCurrentSchoolBoard().getNumOfCoins());
        Assertions.assertEquals(tmp.getName(),testGame.getActiveCharacterName());

    }

    /**
     * checkSelectMage creates an instance of the game for three players and sets it up, then selects a mage from the
     * ArrayList of four mages; then it checks if the mage has been selected correctly and availableMages are only three
     */
    @Test
    public void checkRemoveStudentsFromRoomByColor(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        nicks.add("Nick3");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

         ArrayList<SchoolBoard>tmp =   testGame.getTurnOrder();


        for(int i=0; i<5; i++){
            testGame.getTurnOrder().get(0).insertToDiningRoom(new Student(DiskColor.RED));
        }
        for(int i=0; i<2; i++){
            testGame.getTurnOrder().get(1).insertToDiningRoom(new Student(DiskColor.RED));
        }

        Map<String, Integer> oldAmounts = new HashMap<>();
        for(SchoolBoard s: tmp){
            oldAmounts.put(s.getNick(), s.getStudentsInRoomByColor(DiskColor.RED));
        }

        testGame.removeStudentsFromRoomsByColor(DiskColor.RED);

        Assertions.assertEquals(oldAmounts.get(testGame.getTurnOrder().get(0).getNick())-Math.min(3, oldAmounts.get(testGame.getTurnOrder().get(0).getNick())), testGame.getTurnOrder().get(0).getStudentsInRoomByColor(DiskColor.RED));
        Assertions.assertEquals(oldAmounts.get(testGame.getTurnOrder().get(1).getNick())-Math.min(3, oldAmounts.get(testGame.getTurnOrder().get(1).getNick())), testGame.getTurnOrder().get(1).getStudentsInRoomByColor(DiskColor.RED));
        Assertions.assertEquals(oldAmounts.get(testGame.getTurnOrder().get(2).getNick())-Math.min(3, oldAmounts.get(testGame.getTurnOrder().get(2).getNick())), testGame.getTurnOrder().get(2).getStudentsInRoomByColor(DiskColor.RED));

    }

    /**
     * checkSelectMage creates an instance of the game for three players and sets it up, then selects a mage from the
     * ArrayList of four mages; then it checks if the mage has been selected correctly and availableMages are only three
     */
    @Test
    public void checkSelectMage(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        nicks.add("Nick3");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        Mage mage = testGame.getAvailableMages().get(0);
        testGame.selectMage(mage.getID());

        Assertions.assertEquals(mage, testGame.getCurrentSchoolBoard().getMage());
        Assertions.assertEquals(3, testGame.getAvailableMages().size());

        testGame.endTurn();

        Mage mage2 = testGame.getAvailableMages().get(0);
        testGame.selectMage(mage2.getID());
        Assertions.assertEquals(mage2, testGame.getCurrentSchoolBoard().getMage());

    }

    @Test
    public void checkInsertNoEntry(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        nicks.add("Nick3");
        Game testGame = new Game(nicks, true);
        testGame.setUp();
    }

}

