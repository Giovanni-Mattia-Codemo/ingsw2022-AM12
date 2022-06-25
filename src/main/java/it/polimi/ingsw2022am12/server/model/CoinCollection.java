package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;

/**
 * Class CoinCollection defines an ArrayList of type Coin, with a series of operations
 */
public class CoinCollection implements Position {

    private final ArrayList<Coin> coins;

    /**
     * Constructor of CoinCollection. Creates new ArrayList of type Coin.
     */
    public CoinCollection(){
        coins = new ArrayList<>();
    }

    /**
     * Method getFirstCoin returns the reference to the first Coin in the collection
     *
     * @return Coin the first coin of the collection
     */
    public Coin getFirstCoin(){
        if(coins.isEmpty())return null;
        return coins.get(0);
    }

    /**
     * Method getAllCoins returns the copy of the ArrayList of type Coin contained in the collection
     *
     * @return ArrayList of all Coins
     */
    public ArrayList<Coin> getAllCoins(){
        return new ArrayList<>(coins);
    }

    /**
     * Method size returns the size of the list coins contained in the class (the number of coins)
     *
     * @return int size
     */
    public int size(){
        return coins.size();
    }

    /**
     * Method removeElement takes a PlaceableObject and removes it from the list coins.
     * The object removed must be of type Coin, so I cast the type Coin on the generic PlaceableObject o
     *
     * @param o PlaceableObject to be removed
     */
    @Override
    public void removeElement(PlaceableObject o) {
        coins.remove((Coin) o);
    }

    /**
     * Method insertElement takes a PlaceableObject and adds it to the list coins.
     * The object inserted must be of type Coin, so I cast the type Coin on the generic PlaceableObject o.
     *
     * @param o PlaceableObject to be inserted
     */
    @Override
    public void insertElement(PlaceableObject o) {
        coins.add((Coin) o);
        o.setPosition(this);
    }

    /**
     * Method contains takes a PlaceableObject and checks if it's contained in the list of coins
     *
     * @param o object to be checked
     * @return true if present, false otherwise
     */
    @Override
    public boolean contains(PlaceableObject o){
        return coins.contains(o);
    }
}

