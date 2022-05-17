package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.actions.MerchantAction;

/**
 * Class Character defines the main properties of a Merchant card
 */
public class CharacterMerchant extends CharacterCard {

    //choose a color, no influence can come from that color in your turn

    private DiskColor color;

    /**
     * Constructor method of the CharacterMerchant class
     *
     */
    public CharacterMerchant(){
        super(CharacterName.CHARACTER_MERCHANT, 3);
        color = null;
    }

    /**
     * Setter method of the CharacterMerchant class
     *
     * @param color DiskColor I want to inhibit
     */
    public void setColor(DiskColor color) {
        this.color = color;
    }

    /**
     * Getter method of the CharacterMerchant class
     *
     * @return DiskColor
     */
    public DiskColor getColor() {
        return color;
    }

    /**
     * Method PossibleAction allows me to know the Merchant power (nullifying influence of a certain color)
     *
     * @return PossibleAction the Merchant action I can use
     */
    @Override
    public PossibleAction getPossibleAction() {
        return new MerchantAction();
    }

    /**
     * Method setWasUsed tells if the character was already used through a boolean flag
     *
     * @param isUsed true if the card was used, else false
     */
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
