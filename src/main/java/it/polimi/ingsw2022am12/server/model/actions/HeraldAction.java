package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

public class HeraldAction extends PossibleAction {
    private int islandID;


    public HeraldAction(){
        super(1);
    }

    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if(input.size()==1){
            if(input.get(0) instanceof IslandTileSet){
                if((game.getIslandList().getByIndex(((IslandTileSet) input.get(0)).getiD()))!=null){
                    islandID=((IslandTileSet)input.get(0)).getiD();
                    return ActionStep.OK;
                }
            }
        }return ActionStep.NOTOK;
    }


    @Override
    public void useAction(Game game) {
        game.conquerIsland(islandID);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
