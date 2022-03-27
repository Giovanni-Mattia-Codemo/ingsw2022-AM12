package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class IslandTileSetTest {

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
