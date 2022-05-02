package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Hag Card
 */
public class HagAction extends PossibleAction {

    /**
     * Constructor method of HagAction class
     */
    public HagAction(){
        super(1);
    }

    /**
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>();
        for(DiskColor c: DiskColor.values()){
            result.add(new ColorSelection(c));
        }
        selectables.put(0, result);
    }

    @Override
    public String getUserSelectionsMessage() {
        return "To use the hag select a color";
    }

    /**
     * Method useAction uses the power of the Hag, and sets the Hag to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.removeStudentsFromRoomsByColor(((ColorSelection)score.get(0)).getColor());
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
