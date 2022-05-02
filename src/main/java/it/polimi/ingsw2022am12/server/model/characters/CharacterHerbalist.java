package it.polimi.ingsw2022am12.server.model.characters;


import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.HerbalistAction;

/**
 * Class Character defines the main properties of a Herbalist card
 */
public class CharacterHerbalist extends CharacterCard {

    //at start place four no entries on it, during your turn you can place one noEntry on an island

    private static final int numOfNoEntriesCard = 4;
    private final NoEntryCollection noEntryCollection;

    /**
     * Constructor method of the CharacterHerbalist class
     *
     */
    public CharacterHerbalist(){
        super(CharacterName.CHARACTER_HERBALIST, 2);
        this.noEntryCollection= new NoEntryCollection();
    }

    /**
     * getNoEntryCollection returns the collection of NoEntries placed on the Herbalist card
     *
     * @return the collection of NoEntries placed on the card
     */
    public NoEntryCollection getNoEntryCollection(){
        return noEntryCollection;
    }

    /**
     * Method init Character initializes the four NoEntries on this card
     *
     * @param game current instance of the game
     */
    @Override
    public void initCharacter(Game game) {
        for (int i = 0; i < numOfNoEntriesCard; i++) {
            NoEntry tmp = new NoEntry(noEntryCollection);
            noEntryCollection.insertElement(tmp);
        }
    }

    /**
     * Method PossibleAction allows me to know the Herbalist power (NoEntries)
     *
     * @return PossibleAction the Herbalist action I can use
     */
    @Override
    public PossibleAction getPossibleAction() {
        if(noEntryCollection.noEntriesSize()>0)return new HerbalistAction();
        return null;
    }
}
