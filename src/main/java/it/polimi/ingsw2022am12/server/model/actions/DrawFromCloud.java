package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of drawing from a generic cloud
 */
public class DrawFromCloud extends PossibleAction {

    /**
     * "Constructor" Method of DrawFromCloud class
     *
     */
    public DrawFromCloud() {
        super(1);
    }

    int usableID=0;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
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

    /**
     * Method useAction draws from a usable cloud, and then ends the turn
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.drawFromCloud(usableID);
        game.endTurn();
    }
}
