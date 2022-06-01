package it.polimi.ingsw2022am12.server.model;

/**
 * Class NoEntry defines the NoEntry tile introduced in the hard game mode
 */
public class NoEntry extends PlaceableObject implements Selectable {

    private NoEntryCollection characterNoEntryCollection;

    /**
     * Constructor method of NoEntry. Creates a reference to the character card which defines the no entry
     * card usage.
     * @param characterNoEntryCollection where the noEntries are stored on the char
     */
    public NoEntry(NoEntryCollection characterNoEntryCollection){
        this.characterNoEntryCollection = characterNoEntryCollection;
    }

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
     * Method isEqual compares two objects, and checks if they are in the same state
     *
     * @param toCompare the Selectable object to compare
     * @return boolean true if the objects have the same values
     */
    @Override
    public boolean isEqual(Selectable toCompare) {
        return toCompare instanceof NoEntry;
    }

    public void setCharacterNoEntryCollection(NoEntryCollection characterNoEntryCollection){
        this.characterNoEntryCollection = characterNoEntryCollection;
    }
}
