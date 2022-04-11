package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterHerbalist;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Herbalist Card
 */
public class HerbalistAction extends PossibleAction {

    private int effectiveIsland;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my HerbalistAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {

        boolean isNoEntry = false;
        boolean isValidIsland = false;
        int island = 0;

        for(Selectable s: input){
            if(s instanceof IslandTileSet){
                if(game.getIslandList().getByIndex(((IslandTileSet) s).getID())!=null){
                    isValidIsland = true;
                    island = ((IslandTileSet) s).getID();
                }else{
                    return ActionStep.NOTOK;
                }
            }else if(s instanceof NoEntry){
                isNoEntry= true;
            }else return ActionStep.NOTOK;
        }

        if(input.size()==1){
            return ActionStep.HALFOK;
        }else if(input.size()==2){
            if(isNoEntry&&isValidIsland){
                effectiveIsland = island;
                if(((CharacterHerbalist)game.getActiveCharacterCard()).getNoEntryCollection().noEntriesSize()!=0){
                    return ActionStep.OK;
                }
            }
            return ActionStep.NOTOK;
        }else return ActionStep.NOTOK;

    }

    /**
     * Method useAction uses the power of the Herbalist, and sets the Herbalist to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.insertNoEntry(effectiveIsland);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}