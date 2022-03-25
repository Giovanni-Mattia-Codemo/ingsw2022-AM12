package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void checkSetUp(){
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Pippo");
        nicks.add("Pluto");
        Game testGame = new Game(nicks);
        testGame.setUp();

        assertTrue("1",testGame.getBag().amount()==100);
        assertTrue("2",testGame.getCurrentSchoolBoard().getTowersNumber()==8);
        assertTrue("3",testGame.getCloud(0).amount()==3);

        nicks.add("Paperino");
        Game testGame1 = new Game(nicks);
        testGame1.setUp();
        assertTrue("1",testGame1.getBag().amount()==81);
        assertTrue("2",testGame1.getCurrentSchoolBoard().getTowersNumber()==6);
        assertTrue("3",testGame1.getCloud(0).amount()==4);
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
