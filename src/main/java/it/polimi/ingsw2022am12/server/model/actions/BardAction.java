package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.PossibleAction;

public class BardAction extends PossibleAction {

    public BardAction() {
        super(2);
    }

    public BardAction(int cost) {
        super(cost);
    }
/*
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {

        for (int i = 0; i < Math.min(getRequiredInputs(),input.size()); i++){
            Selectable tmp = input.get(i);
            if(tmp.getSelectableType()=="Student"){
                if(((Student)tmp).getPosition()==game.getCurrentSchoolBoard().getEntrance()){

                }
            }
        }
    }

 */
}
