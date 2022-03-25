package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TowerCollectionTest {

    @Test
    public void checkInsertElement(){
        TowerCollection towerCollection0 = new TowerCollection();
        Team team0= new Team();
        Tower s0 = new Tower(team0);
        Tower s1 = new Tower(team0);

        towerCollection0.insertElement(s0);
        towerCollection0.insertElement(s1);

        assertTrue(towerCollection0.getTowers().contains(s0));
        assertTrue(towerCollection0.getTowers().contains(s1));
    }

    @Test
    public void checkRemoveElement(){
        TowerCollection towerCollection0 = new TowerCollection();
        Team team0= new Team();
        Tower s0 = new Tower(team0);

        towerCollection0.insertElement(s0);
        towerCollection0.removeElement(s0);

        assertFalse(towerCollection0.getTowers().contains(s0));
    }

}