package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;
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
        ArrayList<Selectable> result = new ArrayList<>();
        for(DiskColor c: DiskColor.values()){
            result.add(new ColorSelection(c));
        }
        selectables.put(1, result);

    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> messages = new ArrayList<>();
        messages.add(ControlMessages.HAGACTION);
        if(!score.containsKey(0)){
            messages.add(ControlMessages.CHARACTERCARD);
        }else if(!score.containsKey(1)) {
            messages.add(ControlMessages.COLORSELECTION);
        }
        return messages;
    }

    /**
     * Method useAction uses the power of the Hag, and sets the Hag to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.removeStudentsFromRoomsByColor(((ColorSelection)score.get(1)).getColor());
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
