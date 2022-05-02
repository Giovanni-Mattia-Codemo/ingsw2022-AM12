package it.polimi.ingsw2022am12.server.model.actions;

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
        super(2);
    }

    /**
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>(((CharacterMonk) game.getActiveCharacterCard()).getStudents().getStudentsAsSelectables());
        selectables.put(0, result);
        selectables.put(1, game.getIslandList().getIslandsAsSelectable());
    }

    @Override
    public String getUserSelectionsMessage() {
        String msg = "";
        msg = msg.concat("To use the monk:");
        if(!score.containsKey(0)){
            msg = msg.concat(" select a student on the card.");
        }else if(!score.containsKey(1)){
            msg = msg.concat(" select the island to place it on.");
        }
        return msg;
    }

    /**
     * Method useAction uses the power of the Monk, and sets the Monk to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.moveStudentFromCardToIsland(((Student)score.get(0)).getColor(), ((IslandTileSet)score.get(1)).getID());
        ((CharacterMonk)game.getActiveCharacterCard()).setWasUsed(true, game);
    }
}
