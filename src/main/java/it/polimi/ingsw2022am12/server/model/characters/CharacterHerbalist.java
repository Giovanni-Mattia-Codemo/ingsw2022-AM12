package it.polimi.ingsw2022am12.server.model.characters;


import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.HerbalistAction;

public class CharacterHerbalist extends CharacterCard {
    //at start 4 no entries, during 1 noEntry on island

    private static final int numOfNoEntriesCard = 4;
    private final NoEntryCollection noEntryCollection;


    public CharacterHerbalist(){
        super("Herbalist", 2);
    }
}
