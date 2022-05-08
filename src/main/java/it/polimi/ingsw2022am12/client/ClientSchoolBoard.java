package it.polimi.ingsw2022am12.client;


import java.util.ArrayList;

public class ClientSchoolBoard {

    private String nick;
    private ClientStudentCollection entrance;
    private ClientStudentCollection diningRooms;
    private int coins;
    private ArrayList<ClientAssistant> assistants;
    private int playedAssistant;
    private int towers;
    private int Mage;

    public ClientSchoolBoard(){}

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setMage(int mage) {
        Mage = mage;
    }

    public void setTowers(int towers) {
        this.towers = towers;
    }

    public void setAssistants(ArrayList<ClientAssistant> assistants) {
        this.assistants = assistants;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setDiningRooms(ClientStudentCollection diningRooms) {
        this.diningRooms = diningRooms;
    }

    public void setEntrance(ClientStudentCollection entrance) {
        this.entrance = entrance;
    }

    public void setPlayedAssistant(int playedAssistant) {
        this.playedAssistant = playedAssistant;
    }

    public ArrayList<ClientAssistant> getAssistants() {
        return assistants;
    }

    public ClientStudentCollection getDiningRooms() {
        return diningRooms;
    }

    public ClientStudentCollection getEntrance() {
        return entrance;
    }

    public int getCoins() {
        return coins;
    }

    public int getPlayedAssistant() {
        return playedAssistant;
    }

    public String getNick() {
        return nick;
    }

    public int getMage() {
        return Mage;
    }

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
