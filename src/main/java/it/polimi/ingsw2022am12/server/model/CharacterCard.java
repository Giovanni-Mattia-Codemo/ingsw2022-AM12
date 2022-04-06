package it.polimi.ingsw2022am12.server.model;

/**
 * Class Character defines the main properties of a Character card
 */
public abstract class CharacterCard implements Selectable {

    private final CharacterName name;
    private int cost;
    private final CoinCollection additionalCoins;
    private boolean wasUsed;

    /**
     * Constructor method of the Character's class. Assigns the name to the Selectable type, assigns the first
     * cost of the character and creates a new CoinCollection
     *
     * @param name of the Selectable type
     * @param cost of the character (initial value)
     */
    public CharacterCard(CharacterName name, int cost){
        this.name = name;
        this.cost= cost;
        additionalCoins = new CoinCollection();
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
     * Method wasPlayedBefore tells if the character was already used
     *
     * @return true if the character was already used, false otherwise
     */
    public boolean wasPayedBefore(){
        return additionalCoins.size()==1;
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
     */
    public void initCharacter(Game game){}



}
