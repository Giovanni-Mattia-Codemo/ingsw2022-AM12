package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMonk;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Monk Card
 */
public class MonkAction extends PossibleAction {

    /**
     * Constructor method of MonkAction class
     */
    public MonkAction(){
        super(3);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>(((CharacterMonk) game.getActiveCharacterCard()).getStudents().getStudentsAsSelectables());
        ArrayList<Selectable> character = new ArrayList<>();
        character.add(game.getActiveCharacterCard());

        selectables.put(0, character);
        selectables.put(1, result);
        selectables.put(2, game.getIslandList().getIslandsAsSelectable());
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> res = new ArrayList<>();
        res.add(ControlMessages.MONKACTION);
        if(!score.containsKey(0)){
            res.add(ControlMessages.CHARACTERCARD);
        }else if(!score.containsKey(1)){
            res.add(ControlMessages.JESTERACTION1);
        }else if(!score.containsKey(2)){
            res.add(ControlMessages.MONKACTION1);
        }
        return res;
    }

    /**
     * Method useAction uses the power of the Monk, and sets the Monk to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.moveStudentFromCardToIsland(((Student)score.get(1)).getColor(), ((IslandTileSet)score.get(2)).getID());
        ((CharacterMonk)game.getActiveCharacterCard()).setWasUsed(true, game);
    }
}
