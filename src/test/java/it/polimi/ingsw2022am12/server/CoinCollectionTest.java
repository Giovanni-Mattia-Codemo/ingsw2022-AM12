package it.polimi.ingsw2022am12.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CoinCollectionTest {

    @Test
    public void checkInsertElement(){
        Coin c0 = new Coin();
        CoinCollection coinCollection = new CoinCollection();

        coinCollection.insertElement(c0);

        Assertions.assertTrue(coinCollection.getAllCoins().contains(c0));
    }

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
