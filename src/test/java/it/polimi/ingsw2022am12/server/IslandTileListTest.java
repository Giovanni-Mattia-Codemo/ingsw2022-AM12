package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class IslandTileListTest {

    @Test
    public void checkMoveMotherNature(){

        IslandTileList islandTileList = new IslandTileList();
        int index0 = islandTileList.getMotherNatureIndex();
        int steps = 5;
        int size = islandTileList.numOfIslandSets();
        islandTileList.moveMotherNature(steps);
        assertTrue(islandTileList.getMotherNatureIndex()==(index0+steps)%size);


    }

    @Test
    public void checkMergeIslands(){
        IslandTileList islandTileList = new IslandTileList();
        Student s0 = new Student(DiskColor.RED);
        int startingSize = islandTileList.numOfIslandSets();
        IslandTileSet is2= islandTileList.getByIndex(2);
        IslandTileSet is3= islandTileList.getByIndex(3);
        is2.insertStudent(s0);
        islandTileList.mergeIslands(is2, is3);
        assertTrue(is3.getStudents().contains(s0));
        assertTrue(islandTileList.numOfIslandSets()==startingSize-1);

    }

    @Test
    public void checkCheckAndMerge(){
        IslandTileList islandTileList = new IslandTileList();
        int startNumOfIslands = islandTileList.numOfIslandSets();
        int startingSize = islandTileList.numOfIslandSets();
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
        assertTrue(startNumOfIslands-1==islandTileList.numOfIslandSets());
    }
}
