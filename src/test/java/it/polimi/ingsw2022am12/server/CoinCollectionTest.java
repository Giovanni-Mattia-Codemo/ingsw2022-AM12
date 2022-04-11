package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.Coin;
import it.polimi.ingsw2022am12.server.model.CoinCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * CoinCollectionTest is a class made for the testing of the methods in the CoinCollection class
 */
public class CoinCollectionTest {

    /**
     * checkInsertElement inserts a Coin in the CoinCollection, then checks if the CoinCollection contains such Coin
     */
    @Test
    public void checkInsertElement(){
        Coin c0 = new Coin();
        CoinCollection coinCollection = new CoinCollection();

        coinCollection.insertElement(c0);

        Assertions.assertTrue(coinCollection.getAllCoins().contains(c0));
    }

    /**
     * checkRemoveElement inserts two Coin in the CoinCollection, then removes one. It then checks if the CoinCollection
     * still contains the Coin that hasn't been removed, and does not contain the one that has instead been removed
     */
    @Test
    public void checkRemoveElement(){
        Coin c0 = new Coin();
        Coin c1 = new Coin();
        CoinCollection coinCollection = new CoinCollection();

        coinCollection.insertElement(c0);
        coinCollection.insertElement(c1);

        coinCollection.removeElement(c0);

        Assertions.assertTrue(coinCollection.getAllCoins().contains(c1));
        Assertions.assertFalse(coinCollection.getAllCoins().contains(c0));

    }

}
