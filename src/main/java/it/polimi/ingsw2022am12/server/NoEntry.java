package it.polimi.ingsw2022am12.server;

public class NoEntry extends PlaceableObject{

    private final NoEntryCollection characterNoEntryCollection;

    /**
     * Constructor method of NoEntry. Creates a reference to the character card which defines the no entry
     * card usage.
     * @param characterNoEntryCollection
     */
    public NoEntry(NoEntryCollection characterNoEntryCollection){
        this.characterNoEntryCollection = characterNoEntryCollection;
    }

    /**
     * Method getCharacterNoEntryCollection is the getter method for characterNoEntryCollection
     *
     * @return NoEntryCollection the collection of noEntries in the character
     */
    public NoEntryCollection getCharacterNoEntryCollection(){
        return characterNoEntryCollection;
    }
}
