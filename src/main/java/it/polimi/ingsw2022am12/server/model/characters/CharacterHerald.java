package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.actions.HeraldAction;

public class CharacterHerald extends CharacterCard {
    //scegli tessera qualsiasi e calcolaci influenza come se madre natura ci avesse finito il movimento

    public CharacterHerald(){
        super("Herald", 3);
    }

    @Override
    public PossibleAction getPossibleAction() {
        return new HeraldAction();
    }
}
