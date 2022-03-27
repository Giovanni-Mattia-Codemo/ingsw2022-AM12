package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

public class HagAction extends PossibleAction {
    private DiskColor selection;
    public HagAction(int cost){
        super(cost);
    }

    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input , Game game){

        for (int i = 0; i < Math.min(getRequiredInputs(),input.size()) ; i++) {
            if(input.get(i).getSelectableType().equals("Color") ){
                selection = ((ColorSelection) input.get(i)).getColor();
               return ActionStep.OK;
            }
        } return ActionStep.NOTOK;
    }


    @Override
    public void useAction(Game game) {
        game.removeStudentsFromRoomsByColor(selection);
    }
}
