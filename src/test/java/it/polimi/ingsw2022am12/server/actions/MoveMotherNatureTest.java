package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MoveMotherNature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MoveMotherNatureTest {

    @Test
    public void checkCheckInputValidity(){
        MoveMotherNature testMove = new MoveMotherNature();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        testGame.getCurrentSchoolBoard().playAssistant(9);
        IslandTileSet destination;
        int indexMN = testGame.getIslandList().getMotherNatureIndex();
        if(indexMN<10){
            destination = testGame.getIslandList().getByIndex(indexMN+1);
        }else destination = testGame.getIslandList().getByIndex(11-indexMN);

        ArrayList<Selectable> input = new ArrayList<>();
        input.add(destination);

        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));

        testMove.useAction(testGame);
        int newIndexMN = testGame.getIslandList().getMotherNatureIndex();
        Assertions.assertEquals(destination.getID(), testGame.getIslandList().getByIndex(newIndexMN).getID());
    }

    @Test
    public void checkCheckInputValidityNOTOK(){
        MoveMotherNature testMove = new MoveMotherNature();
        ArrayList<String> nicks = new ArrayList<>();
        nicks.add("Nick1");
        nicks.add("Nick2");
        Game testGame = new Game(nicks, false);
        testGame.setUp();

        testGame.getCurrentSchoolBoard().playAssistant(1);
        IslandTileSet destination;
        int indexMN = testGame.getIslandList().getMotherNatureIndex();
        if(indexMN<8){
            destination = testGame.getIslandList().getByIndex(indexMN+3);
        }else destination = testGame.getIslandList().getByIndex(indexMN-3);

        ArrayList<Selectable> input = new ArrayList<>();
        input.add(destination);

        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }
}
