package it.polimi.ingsw2022am12.client.model;


import java.util.ArrayList;

/**
 * ClientSchoolBoard class represents a SchoolBoard from the client's side
 */
public class ClientSchoolBoard {

    private String nick;
    private ClientStudentCollection entrance;
    private ClientStudentCollection diningRooms;
    private int coins;
    private ArrayList<ClientAssistant> assistants;
    private int playedAssistant;
    private int towers;
    private int Mage;

    /**
     * Default constructor of the ClientSchoolBoard class
     */
    public ClientSchoolBoard(){}

    /**
     * Setter method for nick
     * @param nick of the owner of the SchoolBoard
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Setter method for mage
     * @param mage associated to the SchoolBoard
     */
    public void setMage(int mage) {
        Mage = mage;
    }

    /**
     * Setter method for towers
     * @param towers of the SchoolBoard
     */
    public void setTowers(int towers) {
        this.towers = towers;
    }

    /**
     * Setter method for assistants
     * @param assistants of the SchoolBoard
     */
    public void setAssistants(ArrayList<ClientAssistant> assistants) {
        this.assistants = assistants;
    }

    /**
     * Setter method for coins
     * @param coins of the SchoolBoard
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Setter method for diningRooms
     * @param diningRooms of the SchoolBoard
     */
    public void setDiningRooms(ClientStudentCollection diningRooms) {
        this.diningRooms = diningRooms;
    }

    /**
     * Setter method for entrance
     * @param entrance of the SchoolBoard
     */
    public void setEntrance(ClientStudentCollection entrance) {
        this.entrance = entrance;
    }

    /**
     * Setter method for playedAssistant
     * @param playedAssistant of the SchoolBoard
     */
    public void setPlayedAssistant(int playedAssistant) {
        this.playedAssistant = playedAssistant;
    }

    /**
     * Getter method of assistants
     * @return assistants of the schoolBoard
     */
    public ArrayList<ClientAssistant> getAssistants() {
        return assistants;
    }

    /**
     * Getter method of diningRooms
     * @return diningRooms of the schoolBoard
     */
    public ClientStudentCollection getDiningRooms() {
        return diningRooms;
    }

    /**
     * Getter method of entrance
     * @return entrance of the schoolBoard
     */
    public ClientStudentCollection getEntrance() {
        return entrance;
    }

    /**
     * Getter method of coins
     * @return coins of the schoolBoard
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Getter method for playedAssistant
     * @preturn int playedAssistant of the SchoolBoard
     */
    public int getPlayedAssistant() {
        return playedAssistant;
    }

    /**
     * Getter method for nick
     * @return nick of the owner of the SchoolBoard
     */
    public String getNick() {
        return nick;
    }

    /**
     * Getter method for mage
     * @return mage associated to the SchoolBoard
     */
    public int getMage() {
        return Mage;
    }

    /**
     * Getter method for towers
     * @return towers of the SchoolBoard
     */
    public int getTowers() {
        return towers;
    }

    /**
     * getAssistantsPowers turns the powers of the Assistants in a string
     *
     * @return string list of the powers
     */
    public String getAssistantsPowers(){
        String powers = "";
        for(ClientAssistant ca : assistants){
            powers = powers.concat(ca.getTurnPower() + "\t");
        }
        return powers;
    }
}
