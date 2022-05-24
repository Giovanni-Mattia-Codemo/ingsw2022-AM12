package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

/**
 * Class that defines the process of playing an Assistant
 */
public class PlayAssistant extends PossibleAction {

    /**
     * Constructor method of PlayAssistant class
     */
    public PlayAssistant(){
        super(1);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {
        ArrayList<Selectable> result = new ArrayList<>(game.getPlayableAssistants());
        selectables.put(0, result );
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     *
     * @return string message
     */
    @Override
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> res= new ArrayList<>();
        res.add(ControlMessages.PLAYASSISTANT);
        return res;
    }

    /**
     * Method useAction plays the Assistant, and then ends the turn
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){
        game.playAssistant(((Assistant)score.get(0)).getTurnPower());
        game.endTurn();
    }
    
}
