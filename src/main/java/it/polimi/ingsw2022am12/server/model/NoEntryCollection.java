package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;

public class NoEntryCollection implements Position {

    /**
     * Constructor of NoEntry. Creates new ArrayList of type NoEntry.
     */
    private final ArrayList<NoEntry> noEntries;

    /**
     * Constructor method creates the ArrayList of type NoEntry noEntries
     */
    public NoEntryCollection() {
        noEntries = new ArrayList<>();
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
        return new ArrayList<>(noEntries);
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
        try{noEntries.remove((NoEntry) o);
        }catch (ClassCastException e) {
            System.out.println("ClassCastException in NoEntryCollection.removeElement()");
        }

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