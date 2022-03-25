package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;

public class NoEntryCollectionTest {

    @Test
    public void checkInsertAndRemoveElement(){
        NoEntryCollection noEntryCollection0 = new NoEntryCollection();
        NoEntry noEntry = new NoEntry(noEntryCollection0);
        NoEntryCollection noEntryCollection1 = new NoEntryCollection();

        noEntryCollection0.removeElement(noEntry);
        noEntryCollection1.insertElement(noEntry);

        assertTrue(noEntryCollection0.getAllNoEntries().isEmpty());
        assertTrue(noEntryCollection1.getAllNoEntries().contains(noEntry));
    }
}
