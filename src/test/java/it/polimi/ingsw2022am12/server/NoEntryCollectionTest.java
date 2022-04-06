package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.NoEntry;
import it.polimi.ingsw2022am12.server.model.NoEntryCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class NoEntryCollectionTest {

    @Test
    public void checkInsertAndRemoveAndContainsElement(){
        NoEntryCollection noEntryCollection0 = new NoEntryCollection();
        NoEntry noEntry = new NoEntry(noEntryCollection0);
        NoEntryCollection noEntryCollection1 = new NoEntryCollection();

        noEntryCollection0.removeElement(noEntry);
        noEntryCollection1.insertElement(noEntry);

        Assertions.assertTrue(noEntryCollection0.getAllNoEntries().isEmpty());
        Assertions.assertTrue(noEntryCollection1.contains(noEntry));
    }
}
