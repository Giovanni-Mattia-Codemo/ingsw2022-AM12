package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;

import java.util.ArrayList;

public class DrawFromCloud extends PossibleAction {
    /**
     * "Constructor" Method of PossibleAction class
     *
     */
    public DrawFromCloud() {
        super(1);
    }

    int usableID=0;

    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        int id;
        if (input.size()==1){
            if(input.get(0) instanceof StudentDiskCollection){
                id =((StudentDiskCollection) input.get(0)).getID();
                if(game.checkIfCloudDrawableByID(id)){
                    usableID = id;
                    return ActionStep.OK;
                }
            }
        }return ActionStep.NOTOK;


    }

    @Override
    public void useAction(Game game) {
        game.drawFromCloud(usableID);
    }
}
