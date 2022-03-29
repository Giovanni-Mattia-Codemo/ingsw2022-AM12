package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;

import java.util.ArrayList;

public class HerbalistAction extends PossibleAction {

    public HerbalistAction(){
        super(2);
    }

    NoEntry effectiveNoEntry;
    IslandTileSet effectiveIslandTileSet;

    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {

        NoEntry noEntry= null;
        IslandTileSet island = null;

        for(Selectable s: input){
            if(s.getSelectableType().equals("IslandTileSet")){
                if(island==null){
                    island=(IslandTileSet) s;
                }else{
                    return ActionStep.NOTOK;
                }
            }else if(s.getSelectableType().equals("NoEntry")){
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


    @Override
    public void useAction(Game game) {
        game.insertNoEntry(effectiveIslandTileSet, effectiveNoEntry);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}