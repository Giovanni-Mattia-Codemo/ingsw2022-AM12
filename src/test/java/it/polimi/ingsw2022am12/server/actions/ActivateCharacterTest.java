package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.exceptions.NotPresent;
import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.ActivateCharacter;
import it.polimi.ingsw2022am12.server.model.characters.CharacterBeggar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in ActivateCharacter class
 */
public class ActivateCharacterTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets a Character
     * from the array of AvailableCharacters, using a correct index, and inserts it in an ArrayList of inputs. It checks
     * if checkInputValidity on the new ActivateCharacter move created returns OK, then it uses the action. In the end it
     * checks if the character we used is the active one
     */
    @Test
    public void checkCheckInputValidityOK(){
        ActivateCharacter testCharacter = new ActivateCharacter();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable character = testGame.getAvailableCharacters().get(0);
        input.add(character);

        //I fill the currentSchoolBoar with some coins to be able to play the character
        testGame.collectCoin();
        testGame.collectCoin();
        testGame.collectCoin();

        Assertions.assertEquals(ActionStep.OK, testCharacter.checkInputValidity(input, testGame));

        Assertions.assertNull(testGame.getActiveCharacterCard());

        testCharacter.useAction(testGame);
        Assertions.assertEquals(character, testGame.getActiveCharacterCard());
    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up; checkInputValidity
     * will return NOTOK if there are not enough coins to pay for a Character, or if a Character is not available
     */
    @Test
    public void checkCheckInputValidityNOTOK(){
        ActivateCharacter testCharacter = new ActivateCharacter();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, true);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();

        try {
            testGame.getCurrentSchoolBoard().removeCoin(testGame.getCurrentSchoolBoard().getFirstCoin());
        } catch (NotPresent e) {
            e.printStackTrace();
        }
        //Not enough coins
        Selectable rightCharacter = testGame.getAvailableCharacters().get(0);
        input.add(rightCharacter);
        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));

        //Character not available
        input.remove(rightCharacter);
        Selectable character = new CharacterBeggar();
        boolean isBeggarPresent = false;
        for(CharacterCard c: testGame.getAvailableCharacters()){
            if (c.getName().getValue() == 1) {
                isBeggarPresent = true;
                break;
            }
        }
        if(!isBeggarPresent){
            input.add(character);
        }

        //I fill the currentSchoolBoar with some coins to be able to play the character
        testGame.collectCoin();
        testGame.collectCoin();
        testGame.collectCoin();

        Assertions.assertEquals(ActionStep.NOTOK, testCharacter.checkInputValidity(input, testGame));
    }
}
