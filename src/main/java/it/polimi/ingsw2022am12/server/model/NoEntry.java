package it.polimi.ingsw2022am12.server.model;

/**
 * Class NoEntry defines the NoEntry card introduced in the hard game mode
 */
public class NoEntry extends PlaceableObject implements Selectable {

    private final NoEntryCollection characterNoEntryCollection;

    /**
     * Constructor method of NoEntry. Creates a reference to the character card which defines the no entry
     * card usage.
     * @param characterNoEntryCollection where the noEntries are stored on the char
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
