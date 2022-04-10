package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.PlayAssistant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PlayAssistantTest {

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
