package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.Coin;
import it.polimi.ingsw2022am12.server.model.characters.CharacterBeggar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterCardTest {

    @Test
    public void checkCharacterCardMethods(){
        CharacterCard testCard = new CharacterBeggar(); //cost set to 1

        //getCost
        Assertions.assertEquals(1, testCard.getCost());
        //getName
        Assertions.assertEquals(CharacterName.CHARACTER_BEGGAR, testCard.getName());
        //getWasUsed
        Assertions.assertFalse(testCard.wasPayedBefore());
        //insertCoin
        Coin testCoin = new Coin();
        testCard.insertCoin(testCoin);
        Assertions.assertEquals(2, testCard.getCost());
        //setWasUsed
        testCard.setWasUsed(true);
        Assertions.assertTrue(testCard.wasPayedBefore());
        //getPossibleAction
        Assertions.assertNull(testCard.getPossibleAction());
        //getWasUsed
        Assertions.assertTrue(testCard.getWasUsed());


    }
}
