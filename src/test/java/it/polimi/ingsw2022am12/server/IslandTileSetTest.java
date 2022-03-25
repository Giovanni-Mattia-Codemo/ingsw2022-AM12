package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;

public class IslandTileSetTest {

    @Test

    public void checkRemoveAllTowers(){
        IslandTileSet is0= new IslandTileSet();
        Team team = new Team();
        is0.insertTower(new Tower(team));
        is0.insertTower(new Tower(team));
        is0.insertTower(new Tower(team));

        is0.removeAllTowers();
        assertTrue(is0.getTowers().isEmpty());

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
        assertTrue(is0.getNoEntries().size()==1);


    }

}
