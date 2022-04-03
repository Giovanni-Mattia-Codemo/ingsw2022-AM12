package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.DiskColor;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.actions.MerchantAction;

public class CharacterMerchant extends CharacterCard {
    //choose color, no influence from that color

    private DiskColor color;

    public CharacterMerchant(){
        super(CharacterName.CHARACTER_MERCHANT, 3);
        color = null;
    }

    public void setColor(DiskColor color) {
        this.color = color;
    }

    public DiskColor getColor() {
        return color;
    }

    @Override
    public PossibleAction getPossibleAction() {
        return new MerchantAction();
    }

    @Override
    public void setWasUsed(boolean isUsed) {
        if(isUsed){
            super.setWasUsed(true);
        }else{
            setColor(null);
            super.setWasUsed(false);
        }
    }
}
