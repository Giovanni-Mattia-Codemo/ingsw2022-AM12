package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.DiskColor;

import java.util.ArrayList;

/**
 * Class SchoolBoard defines the schoolBoard of a player
 */
public class SchoolBoard{

    private final CoinCollection coins;
    private final TowerCollection towers;
    private final String nick;
    private Mage mage;
    private final StudentDiskCollection entrance;
    private final StudentDiskCollection diningRoom;
    private final ArrayList<Assistant> assistants;
    private Assistant lastPlayedAssistant;
    private int savedTowers;

    /**
     * Constructor method of SchoolBoard class
     * @param name name of the player
     */
    public SchoolBoard(String name){

        coins = new CoinCollection();
        towers = new TowerCollection();
        entrance = new StudentDiskCollection();
        diningRoom = new StudentDiskCollection();
        this.nick = name;
        this.assistants = new  ArrayList<>();


    }

    /**
     * Constructor method of SchoolBoard class
     * @param name of the player
     * @param mage of the player
     * @param coins owned by the player
     * @param assistants placed on the SchoolBoard
     * @param entrance of the SchoolBoard
     * @param diningRoom of the SchoolBoard
     * @param towers left in the SchoolBoard
     * @param lastPlayedAssistant the last assistant played
     */
    public SchoolBoard(String name , int mage, int coins, ArrayList<Assistant> assistants, StudentDiskCollection entrance, StudentDiskCollection diningRoom, int towers, int lastPlayedAssistant){
        this.nick = name;
        this.towers = new TowerCollection();
        this.mage = new Mage(mage);
        this.coins = new CoinCollection();
        for(int i=0;i<coins;i++) {
            this.coins.insertElement(new Coin());
        }
        this.assistants = new ArrayList<>(assistants);
        this.entrance = entrance;
        this.diningRoom = diningRoom;
        this.savedTowers = towers;
        this.lastPlayedAssistant = AssistantCreator.createAssistant(lastPlayedAssistant);
    }



    /**
     *Method setAssistants fills the schoolBoard's deck of assistants
     */
    public void setAssistants() {

        final int maxNumOfAssistantsInDeck = 10;
        for (int i = 1; i<= maxNumOfAssistantsInDeck; i++){
            Assistant tmp;
            tmp = AssistantCreator.createAssistant(i);
            assistants.add(tmp);
        }
    }

    /**
     * Method setMage is used to associate the selected mage to my schoolBoard
     *
     * @param mage to be set
     */
    public void setMage(Mage mage){
        this.mage = mage;
    }

    /**
     * Method getNick is the getter method for String nick
     *
     * @return String nickname
     */
    public String getNick(){
        return nick;
    }


    /**
     * Method getMage returns the mage of the player
     *
     * @return Mage
     */
    public Mage getMage(){
        return mage;
    }

    /**
     * Method getTowersNumber returns the number of towers present on the selected SchoolBoard
     *
     * @return int number of towers
     */
    public int getTowersNumber() {
        return towers.size();
    }

    /**
     * Method insertTower adds a Tower to the collection of towers
     *
     * @param tower tower to be added
     */
    public void insertTower(Tower tower){
        towers.insertElement(tower);
    }

    /**
     * Method removeTower removes a tower from the TowerCollection towers
     *
     * @param tower to be removed
     */
    public void removeTower(Tower tower){
        if(towers.contains(tower))
            towers.removeElement(tower);
    }

    /**
     * Method getFirstTower returns the reference to the first tower in the TowerCollection towers
     *
     * @return tower
     */
    public Tower getFirstTower(){
        return towers.getFirstTower();
    }

    /**
     * Method fillTowers fills the school board with the right number of towers depending on how many players the game has
     *
     * @param numOfPlayers in the game
     * @param team of the player
     */
    public void fillTowers(int numOfPlayers, Team team){

        final int maxNumOfTowersForTwoOrFourPlayers = 8;
        final int maxNumOfTowersForThreePlayers = 6;

        if(numOfPlayers ==2 || numOfPlayers == 4){
            if(team.getSchoolBoardWithTowers()==this){
                for(int i=0; i<maxNumOfTowersForTwoOrFourPlayers; i++){
                    towers.insertElement(new Tower(team));
                }
            }
        }else if (numOfPlayers == 3){
            for(int i=0; i<maxNumOfTowersForThreePlayers; i++){
                towers.insertElement(new Tower(team));
            }
        }
    }

    /**
     *
     *
     * @param team that owns the towers in the schoolBoard
     */
    public void setSavedTowers(Team team){
        for(int i=0; i<savedTowers;i++){
            towers.insertElement(new Tower(team));
        }
    }

    /**
     * Method getCoins returns the amount of coins available to this SchoolBoard
     * @return int amount of coins available
     */
    public int  getNumOfCoins(){
        return coins.size();
    }

    /**
     * Method getFirstCoin returns the reference to the first coin in the CoinCollection coins
     *
     * @return coin
     */
    public Coin getFirstCoin(){
        return coins.getFirstCoin();
    }

    /**
     * Method insertCoin adds a Coin to the collection of coins
     *
     * @param coin to be added
     */
    public void insertCoin(Coin coin){
        coins.insertElement(coin);
    }

    /**
     * Method removeCoin removes a coin from the CoinCollection coins
     *
     * @param coin to be removed
     *
     */
    public void removeCoin(Coin coin){
        if(coins.contains(coin))
            coins.removeElement(coin);
    }

    /**
     * Method insertToDiningRoom adds a Student to the diningRoom collection
     * @param student student to be added
     */
    public void insertToDiningRoom(Student student){
        diningRoom.insertElement(student);
    }

    /**
     * Method removeFromDiningRoom removes a Student from the dining room
     *
     * @param student to be removed
     */
    public void removeFromDiningRoom(Student student){
        if(diningRoom.contains(student))
            diningRoom.removeElement(student);

    }

    /**
     * Method getEntrance returns StudentDiskCollection entrance
     */
    public StudentDiskCollection getEntrance(){
        return entrance;
    }

    /**
     * Method getDiningRoom returns StudentDiskCollection diningRoom
     *
     * @return diningRoom
     */
    public StudentDiskCollection getDiningRoom(){
        return diningRoom;
    }

    /**
     * Method insertToEntrance adds a student to the entrance
     *
     * @param student to be added
     */
    public void insertToEntrance(Student student){
        entrance.insertElement(student);
    }

    /**
     * Method moveStudentFromEntranceToRoom moves a student from the entrance collection to the dining room collection
     *
     * @param student to be moved
     */
    public void moveStudentFromEntranceToRoom (Student student){
        entrance.removeElement(student);
        diningRoom.insertElement(student);
    }

    /**
     * Method getStudentsInRoomByColor checks how many entries the dining room has of a specific color
     *
     * @param color DiskColor color to be checked
     * @return int number of entries of that color
     */
    public int getStudentsInRoomByColor(DiskColor color){
        return diningRoom.getByColor(color);
    }

    /**
     * Method isDiningRoomFull checks if a certain table of the dining room is full
     *
     * @param color Color to be checked
     * @return boolean true if the dining room is full, false otherwise
     */
    public boolean isDiningRoomFull(DiskColor color){
        return diningRoom.getByColor(color) == 10;
    }

    /**
     * Method swapStudents takes a student from the entrance and moves it to the dining room, or vice versa.
     * Called by the special card "Bard"
     *
     * @param student0 First Student to swap
     * @param student1 Second student to swap
     */
    public void swapStudents(Student student0, Student student1){
        diningRoom.removeElement(student0);
        entrance.removeElement(student1);
        diningRoom.insertElement(student1);
        entrance.insertElement(student0);
    }

    /**
     * Method playAssistant takes an assistant as input and sets it as the lastPlayedAssistant of the SchoolBoard,
     *         (NOTE! removing it from the available assistants)
     *
     * @param assistant the assistant you wish to play, must be an available assistant
     *
     */
    public void playAssistant(int assistant) {
        Assistant tmp = null;
        for(Assistant a: assistants){
            if(a.getTurnPower()==assistant){
                lastPlayedAssistant = a;
                tmp=a;
            }
        }
        assistants.remove(tmp);
    }

    /**
     * Method getFirstStudentOfColor returns the first Student of a specific color contained in the list of students
     *
     * @param color wanted
     * @return Student of a specific color
     */
    public Student getFirstStudentInRoomOfColor(DiskColor color){
        return diningRoom.getFirstStudentOfColor(color).isPresent()?diningRoom.getFirstStudentOfColor(color).get():null;
    }

    /**
     * Method  getPlayableAssistants returns a list of the assistants present in the SchoolBoard
     * @return ArrayList<Assistant> containing the same assistants as the SchoolBoard's assistants
     */
    public ArrayList<Assistant> getRemainingAssistants(){
        return new ArrayList<>(assistants);
    }

    /**
     * Method getLastPlayedAssistantPower returns an int containing the turnPower of the lastPlayedAssistant
     * @return int turnPower of lastPlayedAssistant
     */
    public int getLastPlayedAssistantPower(){
        if(lastPlayedAssistant!=null){
            return lastPlayedAssistant.getTurnPower();
        }
        return -1;
    }

    /**
     * Method getLastPlayedAssistantRange returns an int containing the motherNature range of the last played assistant
     * @return int motherNatureRange
     */
    public int getLastPlayedAssistantRange(){
        return lastPlayedAssistant.getMotherNatureRange();
    }

}