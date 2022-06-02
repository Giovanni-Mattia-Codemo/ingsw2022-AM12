package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;

/**
 * Class NoEntry defines a collection of NoEntries
 */
public class NoEntryCollection implements Position {

    private static int numOfNoEntryCollections;
    private final int myId;
    private final ArrayList<NoEntry> noEntries;

    /**
     * Constructor method creates the ArrayList of type NoEntry noEntries
     */
    public NoEntryCollection() {
        noEntries = new ArrayList<>();
        myId = numOfNoEntryCollections;
        numOfNoEntryCollections++;
    }

    /**
     * Constructor method creates the ArrayList of type NoEntry noEntries, which uses an input as the ID
     */
    public  NoEntryCollection(int id){
        myId = id;
        noEntries = new ArrayList<>();
    }

    /**
     * Getter method for myId
     * @return int value of myId
     */
    public int getMyId() {
        return myId;
    }

    /**
     * Method noEntriesSize returns the size of noEntries
     *
     * @return noEntries size
     */
    public int noEntriesSize(){
        return noEntries.size();
    }

    /**
     * Method getAllNoEntries returns a copy of noEntries
     *
     * @return noEntries copy
     */
    public ArrayList<NoEntry> getAllNoEntries(){
        return noEntries;
    }

    /**
     * Method getFirstNoEntry returns the reference to the first noEntry in the ArrayList noEntries
     *
     * @return NoEntry first NoEntry
     */
    public NoEntry getFirstNoEntry(){
        return noEntries.get(0);
    }

    /**
     * Method removeElement takes a PlaceableObject and removes it from the list noEntries.
     * The object removed must be of type NoEntry, so I cast the type NoEntry on the generic PlaceableObject o
     *
     * @param o PlaceableObject to be removed
     */
    @Override
    public void removeElement(PlaceableObject o) {
        noEntries.remove((NoEntry) o);
    }

    /**
     * Method insertElement takes a PlaceableObject and adds it to the list noEntries.
     * The object inserted must be of type NoEntry, so I cast the type NoEntry on the generic PlaceableObject o.
     *
     * @param o PlaceableObject to be inserted
     */
    @Override
    public void insertElement(PlaceableObject o) {
        try{noEntries.add((NoEntry) o);
            o.setPosition(this);
        }catch (ClassCastException e) {
            System.out.println("ClassCastException in NoEntryCollection.insertElement()");
        }
    }

    /**
     * Method contains checks if the ArrayList noEntries contains the wanted object
     *
     * @param o object passed to the method
     * @return true if present, false otherwise
     */
    @Override
    public boolean contains(PlaceableObject o){
        return noEntries.contains(o);
    }

    
}