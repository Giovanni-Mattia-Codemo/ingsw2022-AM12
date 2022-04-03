package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.Mage;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

public class SelectMage extends PossibleAction {

    /**
     * "Constructor" Method of PossibleAction class
     */
    public SelectMage() {
        super(1);
    }

    private int mageID;

    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if(input.size()==1){
            if(input.get(0) instanceof Mage) {
                ArrayList<Mage> tmp = game.getAvailableMages();
                for(Mage m: tmp){
                    if(m.getID()==((Mage) input.get(0)).getID()){
                        mageID=((Mage) input.get(0)).getID();
                        return ActionStep.OK;
                    }
                }
            }
        }return ActionStep.NOTOK;
    }

    @Override
    public void useAction(Game game) {
        game.selectMage(mageID);
        game.endTurn();
    }
}
