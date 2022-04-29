package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.Coin;
import it.polimi.ingsw2022am12.server.model.characters.CharacterBeggar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * CharacterCardTest is a class made for the testing of the methods in the CharacterCard class
 */
public class CharacterCardTest {

    /**
     * CheckCharacterCardMethods() checks every method. It creates a character (in this case a Beggar), and then checks
     * if its cost and name have been assigned correctly. It checks if the character was paid before (returns false because
     * the character has just been created). Then it inserts a new coin in the CoinCollection held by the card and checks if
     * there are only two of them in it. It sets wasUsed to true and then checks if the character was already paid (in this
     * case it returns true, since we added a +1 coin on the character). Since the Beggar has no possible action, it checks if
     * getPossibleAction is null.
     *
     */
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
