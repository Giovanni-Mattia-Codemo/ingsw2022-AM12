package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

public class PlayAssistant extends PossibleAction {
    /**
     * "Constructor" Method of PossibleAction class
     */
    public PlayAssistant() {
        super(1);
    }

    private int assistantTurnPower;


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

    @Override
    public void useAction(Game game){
        game.playAssistant(assistantTurnPower);
        game.endTurn();
    }
}
