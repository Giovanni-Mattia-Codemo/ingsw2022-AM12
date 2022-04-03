package it.polimi.ingsw2022am12.server.model.characters;


import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.HerbalistAction;

public class CharacterHerbalist extends CharacterCard {
    //at start 4 no entries, during 1 noEntry on island

    private static final int numOfNoEntriesCard = 4;
    private final NoEntryCollection noEntryCollection;


    public CharacterHerbalist(){
        super(CharacterName.CHARACTER_HERBALIST, 2);
        this.noEntryCollection= new NoEntryCollection();
    }

    @Override
    public void InitCharacter(Game game) {
        for (int i = 0; i < numOfNoEntriesCard; i++) {
            NoEntry tmp = new NoEntry(noEntryCollection);
            noEntryCollection.insertElement(tmp);
        }
    }

    @Override
    public PossibleAction getPossibleAction() {
        if(noEntryCollection.noEntriesSize()>0)return new HerbalistAction();
        return null;
    }
}
