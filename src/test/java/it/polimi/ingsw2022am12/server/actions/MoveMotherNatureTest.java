package it.polimi.ingsw2022am12.server.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.actions.ActionStep;
import it.polimi.ingsw2022am12.server.model.actions.MoveMotherNature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Class used to test the methods in MoveMotherNature class
 */
public class MoveMotherNatureTest {

    /**
     * checkCheckInputValidityOK creates an instance of the game with two players and sets it up; it then plays an Assistant
     * from the array of PlayableAssistants, and gets the index of MotherNature; then it calculates correctly the next
     * destination of MotherNature and puts it in the input ArrayList; in the end we check if checkInputValidity returns OK
     */
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
        input.add(testGame.getIslandList().getByIndex(indexMN));

        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.OK, testMove.checkInputValidity(input, testGame));

        testMove.useAction(testGame);
        int newIndexMN = testGame.getIslandList().getMotherNatureIndex();
        Assertions.assertEquals(destination.getID(), testGame.getIslandList().getByIndex(newIndexMN).getID());
    }

    /**
     * checkCheckInputValidityNOTOK creates an instance of the game with two players and sets it up; it then plays an Assistant
     * from the array of PlayableAssistants, and gets the index of MotherNature; then it calculates wrongly the next destination
     * of MotherNature and puts it in the input ArrayList; in the end we check if checkInputValidity returns NOTOK
     */
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
        input.add(testGame.getIslandList().getByIndex(indexMN));

        testMove.setSelectables(testGame);
        Assertions.assertEquals(ActionStep.NOTOK, testMove.checkInputValidity(input, testGame));
    }
}
