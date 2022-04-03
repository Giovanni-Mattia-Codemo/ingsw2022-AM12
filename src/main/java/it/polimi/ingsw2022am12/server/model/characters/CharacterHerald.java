package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.actions.HeraldAction;

/**
 * Class Character defines the main properties of a Herald card
 */
public class CharacterHerald extends CharacterCard {

    //choose an islandTile, then calculate its influence as if motherNature had stopped there

    /**
     * Constructor method of the CharacterBard class
     *
     */
    public CharacterHerald(){
        super(CharacterName.CHARACTER_HERALD, 3);
    }

    /**
     * Method PossibleAction allows me to know the Herald power (calculating influence of a chosen island)
     *
     * @return PossibleAction the Herald action I can use
     */
    @Override
    public PossibleAction getPossibleAction() {
        return new HeraldAction();
    }
}
