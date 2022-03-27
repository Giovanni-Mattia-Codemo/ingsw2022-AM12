package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;

public class GameTest {

    @Test
    public void checkSetUp(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Pippo");
        nicks.add("Pluto");
        Game testGame = new Game(nicks);
        testGame.setUp();

        Assertions.assertEquals(100, testGame.getBag().amount());
        Assertions.assertEquals(8, testGame.getCurrentSchoolBoard().getTowersNumber());
        Assertions.assertEquals(3, testGame.getCloud(0).amount());

        nicks.add("Paperino");
        Game testGame1 = new Game(nicks);
        testGame1.setUp();
        Assertions.assertEquals(81, testGame1.getBag().amount());
        Assertions.assertEquals(6, testGame1.getCurrentSchoolBoard().getTowersNumber());
        Assertions.assertEquals(4, testGame1.getCloud(0).amount());
    }

    @Test
    public void checkFillIslands(){

    }

    @Test
    public void checkConquerIsland(){

    }

    @Test
    public void checkMOveStudentFromEntranceToIsland(){

    }

    @Test
    public void checkMoveMotherNature(){

    }

    @Test
    public void checkMoveStudentFromEntranceToRoom(){

    }

    @Test
    public void checkDrawFromCloud(){

    }

    @Test
    public void checkFillClouds(){

    }

    @Test
    public void checkCorrectOrder(){

    }

    @Test
    public void checkPayCoins(){

    }

    @Test
    public void checkCollectCoin(){

    }
}
