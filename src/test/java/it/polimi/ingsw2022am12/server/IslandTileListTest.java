package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class IslandTileListTest {

    @Test
    public void checkMoveMotherNature(){
        

    }

    @Test
    public void checkMergeIslands(){
        IslandTileList islandTileList = new IslandTileList();
        Student s0 = new Student(DiskColor.RED);
        int startingSize = islandTileList.numOfIslandSets();
        IslandTileSet is2= islandTileList.getByIndex(2);
        Assertions.assertEquals(is2.getID(), islandTileList.getByIndex(2).getID());
        IslandTileSet is3= islandTileList.getByIndex(3);
        is2.insertStudent(s0);
        islandTileList.mergeIslands(is2, is3);
        Assertions.assertTrue(is3.getStudents().contains(s0));
        Assertions.assertEquals(islandTileList.numOfIslandSets(), startingSize - 1);

    }

    @Test
    public void checkCheckAndMerge(){
        IslandTileList islandTileList = new IslandTileList();
        int startNumOfIslands = islandTileList.numOfIslandSets();
        Team team = new Team();
        SchoolBoard testSchool = new SchoolBoard("name");
        team.addSchoolBoard(testSchool);
        Tower t0 = new Tower(team);
        Tower t1 = new Tower(team);
        IslandTileSet is1 = islandTileList.getByIndex(0);
        IslandTileSet is2 = islandTileList.getByIndex(1);
        is1.insertTower(t0);
        is2.insertTower(t1);
        islandTileList.checkAndMerge(1);
        Assertions.assertEquals(startNumOfIslands - 1, islandTileList.numOfIslandSets());
    }
}
