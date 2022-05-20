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
    public String getUserSelectionsMessage() {
        String msg = "";
        msg = msg.concat("To use the monk:");
        if(!score.containsKey(0)){
            msg = msg.concat(" select it's character card");
        }else if(!score.containsKey(1)){
            msg = msg.concat(" select a student on the card.");
        }else if(!score.containsKey(2)){
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
        game.moveStudentFromCardToIsland(((Student)score.get(1)).getColor(), ((IslandTileSet)score.get(2)).getID());
        ((CharacterMonk)game.getActiveCharacterCard()).setWasUsed(true, game);
    }
}
