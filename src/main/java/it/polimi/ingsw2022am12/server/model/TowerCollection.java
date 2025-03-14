package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;

/**
 * Class TowerCollection defines a collection of type Tower
 */
public class TowerCollection implements Position {

    private final ArrayList<Tower> towers;

    /**
     * Constructor method creates a new list of type Tower
     */
    public TowerCollection(){
        towers = new ArrayList<>();
    }

    /**
     *Method size returns the size of the ArrayList towers (A.K.A. the number of towers)
     *
     * @return int size
     */
    public int size(){
        return towers.size();
    }

    /**
     * Method getTowers returns a copy of the towers in the collection
     *
     * @return ArrayList towers
     */
    public ArrayList<Tower> getTowers(){
        return new ArrayList<>(towers);
    }

    /**
     * Method getFirstTower returns the reference to the first tower in the ArrayList towers
     *
     * @return Tower first tower
     */
    public Tower getFirstTower(){
        if (towers.isEmpty())return null;
        return towers.get(0);
    }

    /**
     * Method removeElement takes a PlaceableObject o (casting it as a Tower) and removes it from the list of towers
     *
     * @param o PlaceableObject to be removed
     */
    @Override
    public void removeElement(PlaceableObject o) {
        towers.remove((Tower) o);
    }

    /**
     * Method insertElement takes a PlaceableObject o (casting it as a Tower) and adds it to the list of towers
     *
     * @param o PlaceableObject to be inserted
     */
    @Override
    public void insertElement(PlaceableObject o) {
        towers.add((Tower) o);
        o.setPosition(this);
    }

    /**
     * Method contains check if a specific tower is present in the collection
     *
     * @param o object to be checked
     * @return true if present, false otherwise
     */
    @Override
    public boolean contains(PlaceableObject o){
        return towers.contains(o);
    }

}
