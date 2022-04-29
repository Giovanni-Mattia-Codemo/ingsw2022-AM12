package it.polimi.ingsw2022am12.server.model.actions;

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
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>(((CharacterHerbalist) game.getActiveCharacterCard()).getNoEntryCollection().getAllNoEntries());
        selectables.put(0, result);
        selectables.put(1, game.getIslandList().getIslandsAsSelectable());
    }

    /**
     * Method useAction uses the power of the Herbalist, and sets the Herbalist to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.insertNoEntry(effectiveIsland);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}