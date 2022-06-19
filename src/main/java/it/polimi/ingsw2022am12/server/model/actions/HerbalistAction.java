package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterHerbalist;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Herbalist Card
 */
public class HerbalistAction extends PossibleAction {



    /**
     * Constructor method of HerbalistAction class
     */
    public HerbalistAction(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> character = new ArrayList<>();
        character.add(game.getActiveCharacterCard());
        selectables.put(0, character);
        selectables.put(1, game.getIslandList().getIslandsAsSelectable());
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> messages = new ArrayList<>();
        messages.add(ControlMessages.HERBALISTACTION);
        if (!score.containsKey(0)) {
            messages.add(ControlMessages.CHARACTERCARD);
        }else if(!score.containsKey(1)){
            messages.add(ControlMessages.HERBALISTACTION1);
        }
        return messages;
    }

    /**
     * Method useAction uses the power of the Herbalist, and sets the Herbalist to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.insertNoEntry(((IslandTileSet)score.get(1)).getID());
        game.getActiveCharacterCard().setWasUsed(true);
    }
}