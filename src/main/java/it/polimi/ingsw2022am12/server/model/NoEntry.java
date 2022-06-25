package it.polimi.ingsw2022am12.server.model;

/**
 * Class NoEntry defines the NoEntry tile introduced in the hard game mode
 */
public class NoEntry extends PlaceableObject{

    private NoEntryCollection characterNoEntryCollection;

    /**
     * Constructor method of NoEntry. Creates a reference to the character card which defines the no entry
     * card usage.
     * @param characterNoEntryCollection where the noEntries are stored on the char
     */
    public NoEntry(NoEntryCollection characterNoEntryCollection){
        this.characterNoEntryCollection = characterNoEntryCollection;
    }

    /**
     * Default constructor of NoEntry
     */
    public NoEntry(){
    }

    /**
     * Method getCharacterNoEntryCollection is the getter method for characterNoEntryCollection
     *
     * @return NoEntryCollection the collection of noEntries in the character
     */
    public NoEntryCollection getCharacterNoEntryCollection(){
        return characterNoEntryCollection;
    }

    /**
     * Setter method for characterNoEntryCollection
     * @param characterNoEntryCollection the noEntries placed on the card
     */
    public void setCharacterNoEntryCollection(NoEntryCollection characterNoEntryCollection){
        this.characterNoEntryCollection = characterNoEntryCollection;
    }
}
