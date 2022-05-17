package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Class created to test the methods in the IslandTileList class
 */
public class IslandTileListTest {

    /**
     * checkMergeIslands creates two islands, and initializes them with a Tower, a NoEntry and a Student each. It then
     * merges them in the latter island. In the end it checks if the "new" island resulting from the merge contains
     * the students, noEntries and towers from both the previous ones
     */
    @Test
    public void checkMergeIslands(){


        IslandTileList islandTileList = new IslandTileList();
        Student s0 = new Student(DiskColor.RED);
        Student s1 = new Student(DiskColor.BLUE);
        Team t0 = new Team();

        Tower tw0 = new Tower(t0);
        Tower tw1 = new Tower(t0);
        NoEntry nE0= new NoEntry(new NoEntryCollection());
        NoEntry nE1= new NoEntry(new NoEntryCollection());
        int startingSize = islandTileList.numOfIslandSets();
        IslandTileSet is2= islandTileList.getByIndex(2);
        is2.insertStudent(s0);
        is2.insertTower(tw0);
        is2.insertNoEntries(nE0);
        IslandTileSet is3= islandTileList.getByIndex(3);
        is3.insertTower(tw1);
        is3.insertStudent(s1);
        is3.insertNoEntries(nE1);

        islandTileList.mergeIslands(is2, is3);
        Assertions.assertTrue(is3.getStudents().contains(s0));
        Assertions.assertTrue(is3.getStudents().contains(s1));
        Assertions.assertTrue(is3.getTowers().contains(tw0));
        Assertions.assertTrue(is3.getTowers().contains(tw1));
        Assertions.assertEquals(2, is3.getNoEntries().size());

        Assertions.assertEquals(islandTileList.numOfIslandSets(), startingSize - 1);

    }

    /**
     * checkCheckAndMerge places three towers on three new IslandTileSets and merges the leftmost island with the one
     * on its right, it then checks I have two islands less in my list, while MotherNature is still on the same island.
     * It repeats the process two times
     */
    @Test
    public void checkCheckAndMerge() {
        //check that motherIsland still points to the right island after a merge that involves it

        IslandTileList islandTileList = new IslandTileList();
        int startNumOfIslands = islandTileList.numOfIslandSets();

         //merge to the right
            Team team = new Team();
            Tower t0 = new Tower(team);
            Tower t1 = new Tower(team);
            Tower t2 = new Tower(team);
            IslandTileSet is1 = islandTileList.getByIndex(0);
            IslandTileSet is2 = islandTileList.getByIndex(1);
            IslandTileSet is3 = islandTileList.getByIndex(2);
            is3.insertTower(t2);
            is1.insertTower(t0);
            is2.insertTower(t1);
            islandTileList.moveMotherNature(is1);
            islandTileList.checkAndMerge(1);
            Assertions.assertEquals(is1, islandTileList.getByIndex(islandTileList.getMotherNatureIndex()));

            Assertions.assertEquals(startNumOfIslands - 2, islandTileList.numOfIslandSets());

        //merge to the right
        IslandTileList islandTileList1 = new IslandTileList();
        Team team1 = new Team();
        Tower t3 = new Tower(team1);
        int startNumOfIslands1 = islandTileList1.numOfIslandSets();

        IslandTileSet is4 = islandTileList1.getByIndex(0);
        IslandTileSet is5 = islandTileList1.getByIndex(1);
        IslandTileSet is6 = islandTileList1.getByIndex(2);
        is4.insertTower(t0);
        is5.insertTower(t3);
        is6.insertTower(t1);
        islandTileList1.moveMotherNature(is4);
        islandTileList1.checkAndMerge(1);
        Assertions.assertEquals(is4, islandTileList1.getByIndex(islandTileList1.getMotherNatureIndex()));

        Assertions.assertEquals(startNumOfIslands1, islandTileList1.numOfIslandSets());

    }

    /**
     * checkCheckAndMerge2 places three towers on three new IslandTileSets and merges the leftmost island with the one
     * on its right, it then checks I have one less island in my list, while MotherNature is still on the same island.
     */
    @Test
    public void checkCheckAndMerge2(){

        IslandTileList islandTileList = new IslandTileList();
        int startNumOfIslands = islandTileList.numOfIslandSets();

        //merge to the right
        Team team = new Team();
        Team team1 = new Team();
        Tower t0 = new Tower(team);
        Tower t1 = new Tower(team);
        Tower t2 = new Tower(team1);
        IslandTileSet is1 = islandTileList.getByIndex(0);
        IslandTileSet is2 = islandTileList.getByIndex(1);
        IslandTileSet is3 = islandTileList.getByIndex(2);
        is3.insertTower(t1);
        is1.insertTower(t2);
        is2.insertTower(t0);
        islandTileList.moveMotherNature(is1);
        islandTileList.checkAndMerge(1);
        Assertions.assertEquals(is1, islandTileList.getByIndex(islandTileList.getMotherNatureIndex()));

        Assertions.assertEquals(startNumOfIslands - 1, islandTileList.numOfIslandSets());
    }


    /**
     * checkDistanceFromMotherNature creates a new IslandTileList and gets MotherNature's position, then it chooses an island
     * which is 7 positions behind it, and checks if the distance is actually seven
     */
    @Test
    public void checkDistanceFromMotherNature(){
        IslandTileList islandTileList = new IslandTileList();

        int motherNaturePose = islandTileList.getMotherNatureIndex();
        IslandTileSet islandTmp = islandTileList.getByIndex((motherNaturePose+7)%12);
        int distance = islandTileList.distanceFromMotherNature(islandTmp);
        Assertions.assertEquals(7, distance);

    }
}
