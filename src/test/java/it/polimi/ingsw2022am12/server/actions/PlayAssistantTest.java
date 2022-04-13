package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.PlayAssistant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Class used to test the methods in PlayAssistant class
 */
public class PlayAssistantTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then gets an Assistant
     * from the array of Playable Assistants, using a correct index, and inserts it in an ArrayList of inputs. It checks
     * if checkInputValidity on the new PlayAssistant move created returns OK, then it uses the action. In the end it checks
     * if we selected the correct assistant, and if the new availableMages number is reduced by one
     */
    @Test
    public void checkCheckInputValidityOK(){
        PlayAssistant testMove = new PlayAssistant();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable assistantToPlay = testGame.getCurrentSchoolBoard().getPlayableAssistants().get(0);
        input.add(assistantToPlay);

        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));
        int playableAssistantsBefore = testGame.getCurrentSchoolBoard().getPlayableAssistants().size();

        testMove.useAction(testGame);
        int playableAssistantsAfter = testGame.getTurnOrder().get(0).getPlayableAssistants().size();

        Assertions.assertEquals("Nick2", testGame.getCurrentSchoolBoard().getNick());
        Assertions.assertEquals(playableAssistantsBefore-1, playableAssistantsAfter);
    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up; it then gets an Assistant
     * from the array of PlayableAssistants, using a wrong index, and inserts it in an ArrayList of inputs. It checks
     * if the checkInputValidity returns NOTOK
     */
    @Test
    public void checkCheckInputValidityNOTOK(){
        PlayAssistant testMove = new PlayAssistant();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        ArrayList<Selectable> input = new ArrayList<>();
        Selectable assistantToPlay = new Assistant(11,3);
        input.add(assistantToPlay);

        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }
}
