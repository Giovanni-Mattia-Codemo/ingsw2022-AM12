package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of the Herbalist Card
 */
public class HerbalistAction extends PossibleAction {

    /**
     * "Constructor" Method of HerbalistAction class
     *
     */
    public HerbalistAction(){
        super(2);
    }

    NoEntry effectiveNoEntry;
    IslandTileSet effectiveIslandTileSet;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {

        NoEntry noEntry= null;
        IslandTileSet island = null;

        for(Selectable s: input){
            if(s instanceof IslandTileSet){
                if(island==null){
                    island=(IslandTileSet) s;
                }else{
                    return ActionStep.NOTOK;
                }
            }else if(s instanceof NoEntry){
                if(noEntry==null){
                    noEntry= (NoEntry) s;
                }else{
                    return ActionStep.NOTOK;
                }
            }else return ActionStep.NOTOK;
        }

        if(noEntry!=null&&island!=null){
            effectiveIslandTileSet = island;
            effectiveNoEntry = noEntry;
            return ActionStep.OK;
        }else if(noEntry!=null||island!=null){
            return ActionStep.HALFOK;
        }
        return ActionStep.NOTOK;

    }

    /**
     * Method useAction uses the power of the Herbalist, and sets the Herbalist to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.insertNoEntry(effectiveIslandTileSet, effectiveNoEntry);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}