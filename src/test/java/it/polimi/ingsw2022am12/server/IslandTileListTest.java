package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class IslandTileListTest {

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


    @Test
    public void checkDistanceFromMotherNature(){
        IslandTileList islandTileList = new IslandTileList();

        int motherNaturePose = islandTileList.getMotherNatureIndex();
        IslandTileSet islandTmp = islandTileList.getByIndex((motherNaturePose+7)%12);
        int distance = islandTileList.distanceFromMotherNature(islandTmp);
        Assertions.assertEquals(7, distance);

    }
}
