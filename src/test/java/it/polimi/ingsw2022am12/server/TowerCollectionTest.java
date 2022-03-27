package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.Team;
import it.polimi.ingsw2022am12.server.model.Tower;
import it.polimi.ingsw2022am12.server.model.TowerCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TowerCollectionTest {

    @Test
    public void checkInsertElement(){
        TowerCollection towerCollection0 = new TowerCollection();
        Team team0= new Team();
        Tower s0 = new Tower(team0);
        Tower s1 = new Tower(team0);

        towerCollection0.insertElement(s0);
        towerCollection0.insertElement(s1);

        Assertions.assertTrue(towerCollection0.getTowers().contains(s0));
        Assertions.assertTrue(towerCollection0.getTowers().contains(s1));
    }

    @Test
    public void checkRemoveElement(){
        TowerCollection towerCollection0 = new TowerCollection();
        Team team0= new Team();
        Tower s0 = new Tower(team0);

        towerCollection0.insertElement(s0);
        towerCollection0.removeElement(s0);

        Assertions.assertFalse(towerCollection0.getTowers().contains(s0));
    }

}