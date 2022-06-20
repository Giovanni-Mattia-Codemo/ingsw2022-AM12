package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.CharacterName;

/**
 * Class Character defines the main properties of a Character card
 */
public abstract class CharacterCard implements Selectable {

    private final CharacterName name;
    private int cost;
    private final CoinCollection additionalCoins;
    private boolean wasUsed;

    /**
     * Constructor method of the Character's class. Assigns the name to the CharacterCard type, assigns the first
     * cost of the character and creates a new CoinCollection
     *
     * @param name of the CharacterCard
     * @param cost of the character (initial value)
     */
    public CharacterCard(CharacterName name, int cost){
        this.name = name;
        this.cost= cost;
        additionalCoins = new CoinCollection();
    }

    /**
     * Setter method for cost
     * @param cost the cost of the card
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * setAdditionalCoins adds a new coin to card
     */
    public void setAdditionalCoins(){
        this.additionalCoins.insertElement(new Coin());
    }

    /**
     * method getCost returns the cost of the specific character
     *
     * @return int cost
     */
    public int getCost(){
        return cost;
    }

    /**
     * Method getName returns the name of the selected character
     *
     * @return String name
     */
    public CharacterName getName(){
        return this.name;
    }

    /**
     * Method insertCoin takes a coin from the freeCoins and places it on the Character card and increases the
     * cost of the card by one
     *
     * @param coin to be inserted
     */
    public void insertCoin(Coin coin){
        this.additionalCoins.insertElement(coin);
        cost++;
    }

    /**
     * Method getWasUsed returns if character was already used
     *
     * @return boolean wasUsed
     */
    public boolean getWasUsed(){
        return wasUsed;
    }

    /**
     * Method setWasUsed tells if the character was already used through a boolean flag
     *
     * @param isUsed is true if the character has been used, else false
     */
    public void setWasUsed(boolean isUsed){
        wasUsed = isUsed;
    }

    /**
     * Method PossibleAction allows me to know the possible actions for a Character
     *
     * @return PossibleAction the move I can make thanks to the character
     */
    public PossibleAction getPossibleAction(){
        return null;
    }

    /**
     * Method init Character initializes Characters who possess a private attribute
     *
     * @param game the instance of my game
     */
    public void initCharacter(Game game){}

    /**
     * Method isEqual checks if two objects have the same values in their fields
     *
     * @param toCompare the Selectable object to compare
     */
    @Override
    public boolean isEqual(Selectable toCompare) {
        if(toCompare instanceof CharacterCard){
            return ((CharacterCard) toCompare).name.getValue() == this.name.getValue();
        }
        return false;
    }

    /**
     *
     * @return true if there are additional coins, else false
     */
    public boolean getCoin(){
        return additionalCoins.size() > 0;
    }

    /**
     * method that sets the wasUsed value of a card to true/false
     *
     * @param usage boolean value that determines if the card was used or not
     */
    public void setUsage( boolean usage){
        wasUsed= usage;
    }
}
