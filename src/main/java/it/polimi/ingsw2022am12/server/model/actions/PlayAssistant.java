package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

/**
 * Class that defines the process of playing an Assistant
 */
public class PlayAssistant extends PossibleAction {

    private int assistantTurnPower;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if(input.size()==1){
            if(input.get(0) instanceof Assistant){
                ArrayList<Assistant> tmp = game.getCurrentSchoolBoard().getPlayableAssistants();
                for(Assistant a: tmp){
                    if(a.getTurnPower()==((Assistant) input.get(0)).getTurnPower()){
                        assistantTurnPower=a.getTurnPower();
                        return ActionStep.OK;
                    }
                }
            }
        }return ActionStep.NOTOK;
    }

    /**
     * Method useAction plays the Assistant, and then ends the turn
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){
        game.playAssistant(assistantTurnPower);
        game.endTurn();
    }
}
