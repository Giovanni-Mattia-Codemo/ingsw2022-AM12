package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * IslandTileTest is a class made for the testing of the methods in the IslandTile class
 */
public class IslandTileSetTest {

    /**
     * checkRemoveAllTowers creates three new towers of the same team and inserts them in a new IslandTileSet, then removes
     * them all, and checks if the IslandTileSet is devoid of towers
     */
    @Test
    public void checkRemoveAllTowers(){
        IslandTileSet is0= new IslandTileSet();
        Team team = new Team();
        is0.insertTower(new Tower(team));
        is0.insertTower(new Tower(team));
        is0.insertTower(new Tower(team));

        is0.removeAllTowers();
        Assertions.assertTrue(is0.getTowers().isEmpty());

    }

    /**
     * checkGiveBackNoEntry creates two new NoEntries and places them on the same island; then the island gives back one
     * NoEntry, and we check if only one NoEntry remains on it
     */
    @Test
    public void checkGiveBackNoEntry(){
        IslandTileSet is0= new IslandTileSet();
        NoEntryCollection noEntryCollection = new NoEntryCollection();
        is0.insertNoEntries(new NoEntry(noEntryCollection));
        is0.insertNoEntries(new NoEntry(noEntryCollection));
        try{
            is0.giveBackNoEntry();
        }catch(Exception e){
            e.printStackTrace();
        }
        Assertions.assertEquals(1, is0.getNoEntries().size());
    }

}
