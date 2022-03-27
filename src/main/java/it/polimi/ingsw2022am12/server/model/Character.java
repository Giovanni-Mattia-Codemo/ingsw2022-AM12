package it.polimi.ingsw2022am12.server.model;

/**
 * Class Character defines the main properties of a Character card
 */
public abstract class Character implements Selectable {

    private final String name;
    private int cost;
    private final CoinCollection coin;

    public Character(String name, int cost){
        this.name = name;
        this.cost= cost;
        coin= new CoinCollection();
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
    public String getName(){
        return this.name;
    }

    /**
     * Method insertCoin takes a coin from the freeCoins and places it on the Character card and increases the
     * cost of the card by one
     *
     * @param coin to be inserted
     */
    public void insertCoin(Coin coin){
        this.coin.insertElement(coin);
        cost++;
    }

    public boolean wasPayedBefore(){
        return coin.size()==0;
    }


    public PossibleAction activate(){
        return null;
    }


    public String getSelectableType() {
        return "Character";
    }



}
