package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.NoEntry;
import it.polimi.ingsw2022am12.server.model.NoEntryCollection;
import it.polimi.ingsw2022am12.server.model.Selectable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * NoEntryCollectionTest is a class made for the testing of the methods in the NoEntryCollection class
 */
public class NoEntryCollectionTest {

    /**
     * checkInsertAndRemoveAndContainsElement creates a new NoEntry and inserts it in two different NoEntryCollections;
     * it removes the new NoEntry from one of the collections, then checks if the first collection is empty, while the
     * second still contains the NoEntry
     */
    @Test
    public void checkInsertAndRemoveAndContainsElement(){
        NoEntryCollection noEntryCollection0 = new NoEntryCollection();
        NoEntry noEntry = new NoEntry(noEntryCollection0);
        NoEntryCollection noEntryCollection1 = new NoEntryCollection();
        NoEntryCollection noEntryCollection2 = new NoEntryCollection();

        noEntryCollection0.removeElement(noEntry);
        noEntryCollection1.insertElement(noEntry);

        Assertions.assertTrue(noEntryCollection0.getAllNoEntries().isEmpty());
        Assertions.assertEquals(0, noEntryCollection0.noEntriesSize());
        Assertions.assertTrue(noEntryCollection1.contains(noEntry));
        Assertions.assertTrue(noEntryCollection2.getAllNoEntries().isEmpty());
        Assertions.assertEquals(1,noEntryCollection2.getMyId());

    }
}
