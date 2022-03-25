package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.exceptions.NotPresent;
import it.polimi.ingsw2022am12.exceptions.NotValidSwap;

import java.util.*;

/**
 * Class Game is the instance of the game
 */
public class Game{

    private final int coinsTOTAL = 20;
    private final ArrayList<String> playerNicks;
    private final int numOfPlayers;
    private int round;
    private int turn;
    private CoinCollection freeCoins;
    private ArrayList<SchoolBoard> turnOrder;
    private IslandTileList islandList;
    private StudentDiskCollection[] clouds;
    private Bag bag;
    private ArrayList<Team> teams;
    private SchoolBoard[] professors;
    private PhaseStrategy currentStrategy;
    private ArrayList<Mage> mages;

    private int disksMovedThisTurn;
    private boolean hasMovedMotherNature;

    // private ArrayList<Character> characters;

    /**
     * Constructor method of the class
     *
     * @param playerNicks of the specific game
     */
    public Game(ArrayList<String> playerNicks){

        this.mages = new ArrayList<Mage>();
        for(int i=0; i<4; i++){
            this.mages.add(new Mage(i));
        }

        this.currentStrategy = new SetupStrategy();
        this.playerNicks = playerNicks;
        this.numOfPlayers = playerNicks.size();
        this.freeCoins = new CoinCollection();
        for (int i=0; i<coinsTOTAL; i++){
            Coin tmp= new Coin();
            freeCoins.insertElement(tmp);
        }
        this.turnOrder= new ArrayList<SchoolBoard>();
        this.islandList = new IslandTileList();
        this.bag = new Bag();
        this.clouds = new StudentDiskCollection[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            clouds[i]= new StudentDiskCollection();
        }
        this.teams = new ArrayList<>();
        if(numOfPlayers==2){
            for(int i = 0; i<numOfPlayers; i++){
                String s = playerNicks.get(i);
                teams.add(new Team());

                turnOrder.add(new SchoolBoard(s));
                teams.get(i).addSchoolBoard(turnOrder.get(i));

           }
        }else if(numOfPlayers==3){
            for(String s: playerNicks){
                Team tmp = new Team();
                SchoolBoard temp = new SchoolBoard(s);
                turnOrder.add(temp);
                tmp.addSchoolBoard(temp);
                teams.add(tmp);
            }
        }else if(numOfPlayers==4){
            int num=0;
            teams.add(new Team());
            teams.add(new Team());
            for(String s: playerNicks){
                Team tmp = teams.get(num%2);
                SchoolBoard temp = new SchoolBoard(s);
                turnOrder.add(temp);
                tmp.addSchoolBoard(temp);
                num++;
            }
        }
        this.professors = new SchoolBoard[5];
    }

    /**
     *Method setup is used to set all the initial values of the attributes of the game
     */
    public void setUp(){

        for (DiskColor c: DiskColor.values()){
            for (int i = 0; i < 2; i++) {
                Student tmp =new Student(c);
                bag.insertElement(tmp);
            }
        }

        fillIslands();

        for (DiskColor c: DiskColor.values()){
            for (int i = 0; i < 24; i++) {
                Student tmp =new Student(c);
                bag.insertElement(tmp);
            }
        }

        for(Team t: teams){
            t.getSchoolBoardWithTowers().fillTowers(numOfPlayers,t);
        }

        for(SchoolBoard s: turnOrder){
            Student student = null;
            int studentsInEntrance = 0;
            if(numOfPlayers == 3){
                studentsInEntrance =9;
            }else {
                studentsInEntrance = 7;
            }
            for(int i=0; i<studentsInEntrance; i++){
                student = bag.draw();
                s.insertToEntrance(student);
            }
        }

        fillClouds();

    }

    /**
     * Method selectMage is used from a player to pick a mage
     *
     * @param mageId ID
     * @throws Exception Mage already picked
     */
    public void selectMage(Mage mageId) throws Exception{
        getCurrentSchoolBoard().setMage(mageId);
    }

    /**
     * Method fillIslands is used in the setup method to fill the islands following the setup rules of the game
     */
    public void fillIslands(){
        Student student = null;
        for (int i=0; i<12; i++){
            if (i!= (islandList.getMotherNatureIndex()+6)%12){
                student = bag.draw();
                if(student != null){
                    islandList.getByIndex(i).insertStudent(student);
                }
            }
        }
    }

    /**
     * Method getStudentsInEntranceOfCurrentTurn returns a list of type Selectable with the students the current player
     * can move from them entrance in his turn
     *
     * @return movable students
     */
    public ArrayList<Selectable> getStudentsInEntranceOfCurrentTurn(){
        ArrayList<Selectable> tmp = new ArrayList<>();
        tmp.addAll(turnOrder.get(turn).getEntrance().getAllStudents());
        return tmp;
    }

    /**
     * Method getSchoolBoardByNick is used to return the reference of the schoolBoard of a specific player given
     * them nickName
     *
     * @param nick of the player
     * @return schoolBoard
     */
    public SchoolBoard getSchoolBoardByNick(String nick){
        for (SchoolBoard s: turnOrder){
            if (s.getNick() == nick){
                return s;
            }
        }
        return null;
    }

    /**
     * Method getSelectableClouds returns the list of type Selectable from which a player can choose what cloud
     * they prefer taking
     *
     * @return pickable clouds
     */
    public ArrayList<Selectable> getSelectableClouds(){
        ArrayList<Selectable> tmp = new ArrayList<>();
        for(int i=0; i<numOfPlayers; i++){
            if(!clouds[i].getAllStudents().isEmpty()){
                tmp.add(clouds[i]);
            }
        }
        return tmp;
    }

    /**
     * Method getCurrentSchoolBoard returns the reference to the schoolBoard of the current turn player
     *
     * @return SchoolBoard
     */
    public SchoolBoard getCurrentSchoolBoard(){
        return turnOrder.get(turn);
    }

    /**
     * Method getIslandsInRange returns an ArrayList of type Selectable with the selectable islands given the
     * assistant's card used
     *
     * @return pickable islands
     */
    public ArrayList<Selectable> getIslandsInRange(){
        ArrayList<Selectable> tmp = new ArrayList<>();
        tmp.addAll(islandList.getIslandsInRange(getCurrentSchoolBoard().getLastPlayedAssistantRange()));
        return tmp;
    }

    /**
     * Method getAvailableMages returns the list of mages that weren't already picked
     *
     * @return ArrayList of mages not picked
     */
    public ArrayList<Selectable> getAvailableMages() {
        ArrayList<Selectable> results = new ArrayList<>();
        results.addAll(mages);
        return results;
    }

    /**
     * Method conquerIsland is called when motherNature stops on an island to check if it can be conquered.
     * It is also used when a character card is called upon a specific island.
     *
     * @param index of the island to check
     */
    public void conquerIsland(int index){
        IslandTileSet islandToConquer = islandList.getByIndex(index);
        int[] scores= new int[teams.size()];
        Team team = null;
        SchoolBoard tmp = null;
        for (DiskColor c: DiskColor.values()) {
            tmp = professors[c.getValue()];
            if(tmp!= null){
                for (Team t :teams ) {
                    if(t.getSchoolBoards().contains(tmp)){
                        scores[teams.indexOf(t)]+= islandToConquer.getIslandsStudentsOfColor(c);
                    }
                }
            }
        }
        Team owner = islandToConquer.getOwningTeam();
        if(owner!= null){
            scores[teams.indexOf(owner)]+=islandToConquer.getNumOfIslands();
        }
        int winnerId = 0;
        boolean tie = false;
        for(int i=1; i<scores.length; i++){
            if (scores[i]>scores[winnerId]){
                winnerId = i;
                tie= false;
            }else if(scores[i]==scores[winnerId]){
                tie= true;
            }
        }
        if (tie == false){
            team = teams.get(winnerId);
            if(team!=islandToConquer.getOwningTeam()){
                ArrayList<Tower> towersToMove = islandToConquer.getTowers();
                islandToConquer.removeAllTowers();
                for(Tower t : towersToMove){
                    t.getTeam().getSchoolBoardWithTowers().insertTower(t);
                }

                for(int i=0; i<islandToConquer.getNumOfIslands(); i++){
                    Tower towerToMove = team.getSchoolBoardWithTowers().getFirstTower();
                    islandToConquer.insertTower(towerToMove);
                    try{
                        team.getSchoolBoardWithTowers().removeTower(towerToMove);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                islandList.checkAndMerge(index);
            }
        }
    }

    /**
     * Method playAssistant is used in the planning phase when the player has to select an assistant from them deck
     *
     * @param assistant selected
     * @throws Exception not pickable
     */
    public void playAssistant(Selectable assistant)throws Exception{
        try {
            getCurrentSchoolBoard().playAssistant((Assistant) assistant);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     *Method moveFromEntranceToIsland is used to move a student from the entrance to one specific island
     *
     * @param student to be moved
     * @param islandTileSet chosen
     */
    public void moveStudentFromEntranceToIsland(Selectable student, Selectable islandTileSet){
        if(getCurrentSchoolBoard().getEntrance().contains((Student)student)){
            getCurrentSchoolBoard().getEntrance().removeElement((Student) student);
            ((IslandTileSet) islandTileSet).insertStudent((Student) student);
        }
    }

    /**
     * Method moveMotherNature is used to move mother nature
     *
     * @param steps mother nature has to do
     */
    public void moveMotherNature(int steps){
        islandList.moveMotherNature(steps);
        conquerIsland(islandList.getMotherNatureIndex());
    }

    /**
     * Method moveFromEntranceToRoom moves a students from entrance to room and checks if the player wins the
     * professor of the specific color of the student they moved
     *
     * @param selectableStudent to be moved
     */
    public void moveStudentFromEntranceToRoom(Selectable selectableStudent)throws NotValidSwap{
        Student student = (Student) selectableStudent;
        SchoolBoard s = getCurrentSchoolBoard();
        if(s.checkMoveStudentFromEntranceToRoom(student)){
            s.moveStudentFromEntranceToRoom(student);

            SchoolBoard owner = professors[student.getColor().getValue()];
            if(owner!= null) {
                if (owner.getStudentsInRoomByColor(student.getColor()) < s.getStudentsInRoomByColor(student.getColor())) {
                    owner = s;
                }
            }else owner = s;

        }else throw new NotValidSwap(); //Notify the player
    }

    /**
     * Method drawFromClouds is called to move the students from the selected cloud to the player's entrance
     *
     * @param cloud picked
     */
    public void drawFromCloud(Selectable cloud){
        int size = ((StudentDiskCollection)cloud).amount();
        for(int i = 0; i<size; i++){
            getCurrentSchoolBoard().insertToEntrance(((StudentDiskCollection)cloud).getByIndex(i));
        }
    }

    /**
     *Method fillClouds is used to fill the clouds before the planning phase
     */
    public void fillClouds(){
        Student student = null;
        int studentsPerCloud = 0;
        if (numOfPlayers == 3){
            studentsPerCloud = 4;

        }else studentsPerCloud = 3;

        for (int i=0; i<clouds.length; i++){
            for (int j = 0; j < studentsPerCloud; j++) {
                student = bag.draw();
                clouds[i].insertElement(student);
            }
        }
    }

    /**
     * Method isLastTurn is used to check if it is the last turn before a change of phase
     *
     * @return true if it's the last player's turn, false otherwise
     */
    public boolean isLastTurn(){
        return turn == turnOrder.size() - 1;
    }

    /**
     * Method movedAllDisksThisTurn returns if the player is done moving the students during them action phase
     *
     * @return true if all the students were moved, false otherwise
     */
    public boolean movedAllDisksThisTurn(){
        if(numOfPlayers==3){
            return disksMovedThisTurn==4;
        }return disksMovedThisTurn==3;
    }

    /**
     * Method hasMovedMotherNature id used to know if mother nature was already moved in the current turn
     *
     * @return true if mother nature was already moved, false otherwise
     */
    public boolean hasMovedMotherNature(){
        return hasMovedMotherNature;
    }

    /**
     * Method correctOrder sorts the turnOrder with the correct order of players for the next action phase
     */
    public void correctOrder(){
        Collections.sort(turnOrder, new SortByPower());
    }

    /**
     * Class SortByPower defines a comparator class for ordering the turnOrder ArrayList
     */
    class SortByPower implements Comparator<SchoolBoard> {
        // Sorting in ascending order of turn power number
        public int compare(SchoolBoard a, SchoolBoard b) {
            return a.getLastPlayedAssistantPower() - b.getLastPlayedAssistantPower();
        }
    }

    /**
     * Method printTurnOrder prints to the terminal the turn order
     */
    public void printTurnOrder(){
        for (SchoolBoard s: turnOrder) {
            System.out.println(s.getNick()+ " at index" + turnOrder.indexOf(s));
        }
    }

    /**
     * Method notifyPlayer sends a feedbackk to the player
     *
     * @param nick of the player
     */
    public void notifyPlayer(String nick){
        //To be implemented
    }

    /**
     * Method changePhase id used to change the strategy of the game in order to switch phases
     *
     * @param nextStrategy to be applied
     */
    public void changePhase(PhaseStrategy nextStrategy){
        currentStrategy = nextStrategy;
    }

    /**
     * Method nextTurn is used to iterate on the turnOrder and keep track of the players' turn
     */
    public void nextTurn(){
        if(isLastTurn()){
            turn=0;
        }else turn++;
    }

    /**
     * Method endTurn is used to end a turn
     */
    public void endTurn(){
        currentStrategy.endTurn(this);
    }

    /**
     * Method endRound is used to end a round
     */
    public void endRound(){
        currentStrategy.endRound(this);
    }

    /**
     * Method payCoins is used when a call to a character is made
     *
     * @param coinsUsed cost of character usage
     * @throws NotPresent if the player hasn't enough coins to activate the character
     */
    public void payCoins(int coinsUsed) throws NotPresent{
        int playerCoins = getCurrentSchoolBoard().getNumOfCoins();
        Coin toBeMoved = null;
        if(coinsUsed <= playerCoins){
            for(int i=0; i<coinsUsed; i++){
                toBeMoved = getCurrentSchoolBoard().getFirstCoin();
                getCurrentSchoolBoard().removeCoin(toBeMoved);
                freeCoins.insertElement(toBeMoved);
            }
        }else throw new NotPresent(); //Notify player they have not enough coins
    }

    /**
     * Method collectCoin moves a coin from freeCoins to a player's schoolBoard
     */
    public void collectCoin()throws NotPresent{
        Coin toBeMoved = freeCoins.getFirstCoin();
        if(toBeMoved!=null){
            freeCoins.removeElement(toBeMoved);
            getCurrentSchoolBoard().insertCoin(toBeMoved);
        }else throw new NotPresent(); //No more coins available (notify player)
    }
    /**
     * Method getValidSelection returns all the possible selection a player can make during his turun
     *
     * @return ArrayList of type Selectable
     */
    public ArrayList<Selectable> getValidSelections(){
        return currentStrategy.getValidSelections(this);
    }

    /**
     * Method checkIfIsCurrentPlayer is used to check if it's the turn of a selected player
     *
     * @param nick selected
     * @return true if it is, false otherwise
     */
    public boolean checkIfIsCurrentPlayer(String nick){
        return getSchoolBoardByNick(nick)==getCurrentSchoolBoard();
    }

}