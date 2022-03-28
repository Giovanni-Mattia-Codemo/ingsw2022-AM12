package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.exceptions.NotPresent;

import it.polimi.ingsw2022am12.exceptions.NotValidSwap;
import it.polimi.ingsw2022am12.server.model.phases.SetupStrategy;

import java.util.*;

/**
 * Class Game is the instance of the game
 */
public class Game{

    private final static int coinsTOTAL = 20;
    private final ArrayList<String> playerNicks;
    private final int numOfPlayers;
    private int round;
    private int turn;
    private final CoinCollection freeCoins;
    private final ArrayList<SchoolBoard> turnOrder;
    private final IslandTileList islandList;
    private final StudentDiskCollection[] clouds;
    private final Bag bag;
    private final ArrayList<Team> teams;
    private final SchoolBoard[] professors;
    private PhaseStrategy currentStrategy;
    private final ArrayList<Mage> mages;
    private final static int maxNumOfIslands = 12;
    private final static int hagStudentsToRemove = 3;
    private final static int maxNumOfMages = 4;

    private int disksMovedThisTurn;
    private boolean hasMovedMotherNature;

    private ArrayList<Character> characters;
    private Character activeCharacter;

    /**
     * Constructor method of the class
     *
     * @param playerNicks of the specific game
     */
    public Game(ArrayList<String> playerNicks){

        this.mages = new ArrayList<>();
        for(int i=0; i<maxNumOfMages; i++){
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
        this.turnOrder= new ArrayList<>();
        this.islandList = new IslandTileList();
        this.bag = new Bag();
        this.clouds = new StudentDiskCollection[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            clouds[i]= new StudentDiskCollection();
        }
        this.teams = new ArrayList<>();

        this.professors = new SchoolBoard[5];
    }

    public void assignTeams(){
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
    }

    /**
     *Method setup is used to set all the initial values of the attributes of the game
     */
    public void setUp() throws Exception {

        assignTeams();

        final int numOfStudentsOfEachColorToPutInBagForSetup = 2;
        final int remainingNumOfStudentsOfEachColorToCompleteSetUp = 24;
        final int studentsInEntranceForThreePlayers = 9;
        final int studentsInEntranceForTwoOrFourPlayers = 7;

        for (DiskColor c: DiskColor.values()){
            for (int i = 0; i < numOfStudentsOfEachColorToPutInBagForSetup; i++) {
                Student temporaryStudent =new Student(c);
                bag.insertElement(temporaryStudent);
            }
        }

        fillIslands();

        for (DiskColor c: DiskColor.values()){
            for (int i = 0; i < remainingNumOfStudentsOfEachColorToCompleteSetUp; i++) {
                Student temporaryStudent = new Student(c);
                bag.insertElement(temporaryStudent);
            }
        }

        for(Team t: teams){
            t.getSchoolBoardWithTowers().fillTowers(numOfPlayers,t);
        }

        for(SchoolBoard s: turnOrder){
            Student student;
            int studentsInEntrance;
            if(numOfPlayers == 3){
                studentsInEntrance = studentsInEntranceForThreePlayers;
            }else {
                studentsInEntrance = studentsInEntranceForTwoOrFourPlayers;
            }
            for(int i=0; i<studentsInEntrance; i++){
                student = bag.draw();
                s.insertToEntrance(student);
            }

            s.setAssistants();
        }

        fillClouds();

    }

    /**
     * Method only used for testing
     *
     * @return bag
     */
    public Bag getBag(){
        return bag;
    }

    /**
     * Method only used for testing
     *
     * @param i index of the selected cloud
     * @return cloud
     */
    public StudentDiskCollection getCloud(int i){
        if(i<clouds.length)
            return clouds[i];
        else return null;
    }

    /**
     * Method checkIfIslandInRange checks if an island is reachable by a player
     *
     * @param island to check
     * @return true if the selected island is reachable, false otherwise
     */
    public boolean checkIfIslandInRange(IslandTileSet island){
        int range=getCurrentSchoolBoard().getLastPlayedAssistantRange();
        if(getActiveCharacterName().equals("Beggar") ){
            range+=2;
        }
        return islandList.distanceFromMotherNature(island) <= range;
    }


    /**
     * Method selectMage is used from a player to pick a mage
     *
     * @param mageId ID
     *
     */
    public void selectMage(Mage mageId){
        getCurrentSchoolBoard().setMage(mageId);
    }

    /**
     * Method fillIslands is used in the setup method to fill the islands following the setup rules of the game
     */
    public void fillIslands(){
        Student student;
        for (int i=0; i<maxNumOfIslands; i++){
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
        return new ArrayList<>(turnOrder.get(turn).getEntrance().getAllStudents());
    }

    /**
     * Method getSchoolBoardByNick is used to return the reference of the schoolBoard of a specific player given
     * them nickName
     *
     * @param nick of the player, which is a string
     * @return schoolBoard
     */
    public SchoolBoard getSchoolBoardByNick(String nick){
        for (SchoolBoard s: turnOrder){
            if (s.getNick().equals(nick)){
                return s;
            }
        }
        return null;
    }

    /**
     * Method getSelectableClouds returns the list of type Selectable from which a player can choose what cloud
     * they prefer taking; it checks all the clouds in the "clouds array", if the island is not empty already,
     * meaning that somebody already chose it, it can be selected
     *
     * @return pickable clouds
     */
    public ArrayList<Selectable> getSelectableClouds(){
        ArrayList<Selectable> arrayOfPickableClouds = new ArrayList<>();
        for(int i=0; i<numOfPlayers; i++){
            if(!clouds[i].getAllStudents().isEmpty()){
                arrayOfPickableClouds.add(clouds[i]);
            }
        }
        return arrayOfPickableClouds;
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
     * Method getIslandsInRange returns an ArrayList of type Selectable with the selectable islands, contained in the range
     * given by the assistant's card used
     *
     * @return pickable islands
     */
    public ArrayList<Selectable> getIslandsInRange(){
        int possibleRange = getCurrentSchoolBoard().getLastPlayedAssistantRange();
        return new ArrayList<>(islandList.getIslandsInRange(possibleRange));
    }

    /**
     * Method getActiveCharacterName returns a String with the name of the character that has been activated
     * (it can also return null, if no character has been activated yet)
     *
     * @return name of the active character in the form of a string
     */
    public String getActiveCharacterName(){
        if (activeCharacter==null){
            return null;
        } else return activeCharacter.getName();
    }

    /**
     * Method getAvailableMages returns the list of mages that haven't been picked already
     *
     * @return ArrayList of mages not picked
     */
    public ArrayList<Selectable> getAvailableMages() {
        return new ArrayList<>(mages);
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
        Team team;
        SchoolBoard temporarySchoolBoard;
        for (DiskColor c: DiskColor.values()) {
            temporarySchoolBoard = professors[c.getValue()];
            if(temporarySchoolBoard != null){
                for (Team t :teams ) {
                    if(t.getSchoolBoards().contains(temporarySchoolBoard)){
                        scores[teams.indexOf(t)]+= islandToConquer.getIslandsStudentsOfColor(c);
                    }
                }
            }
        }
        Team owner = islandToConquer.getOwningTeam();
        if(owner!= null&&!(getActiveCharacterName().equals("Centaur"))){
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
        if (!tie){
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
        Assistant assistantToPlay = (Assistant) assistant;
        getCurrentSchoolBoard().playAssistant(assistantToPlay);
    }

    /**
     *Method moveFromEntranceToIsland is used to move a student from the entrance to one specific island
     *
     * @param student to be moved
     * @param islandTileSet chosen
     */
    public void moveStudentFromEntranceToIsland(Selectable student, Selectable islandTileSet){
        Student selectedStudent = (Student)student;
        if(getCurrentSchoolBoard().getEntrance().contains(selectedStudent)){
            getCurrentSchoolBoard().getEntrance().removeElement(selectedStudent);
            ((IslandTileSet) islandTileSet).insertStudent(selectedStudent);
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
    public void moveStudentFromEntranceToRoom(Selectable selectableStudent) throws NotValidSwap{
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
            professors[student.getColor().getValue()]=owner;
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
        Student student;
        int studentsPerCloud;
        if (numOfPlayers == 3){
            studentsPerCloud = 4;

        }else studentsPerCloud = 3;

        for (StudentDiskCollection cloud : clouds) {
            for (int j = 0; j < studentsPerCloud; j++) {
                student = bag.draw();
                cloud.insertElement(student);
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
        final int disksToMoveForThreePlayers = 4;
        final int disksToMoveForTwoOrFourPlayers = 3;

        if(numOfPlayers==3){
            return disksMovedThisTurn==disksToMoveForThreePlayers;
        }return disksMovedThisTurn==disksToMoveForTwoOrFourPlayers;
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
    static class SortByPower implements Comparator<SchoolBoard> {
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
            turn=0;   //resets the turn count
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
        Coin toBeMoved;
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

    /**
     * Method payAndActivateCharacter is used to pay and activate the chosen character,
     * it also increases the cost of the selected character by one
     *
     * @param character selected
     */
    public void payAndActivateCharacter(Character character){
        if (characters.contains(character)){
            int cost =character.getCost();
            try {
                payCoins(cost);
            } catch (NotPresent e) {
                e.printStackTrace();
            }
            if(!character.wasPayedBefore()){
                Coin coinTmp = freeCoins.getFirstCoin();
                freeCoins.removeElement(coinTmp);
                character.insertCoin(coinTmp);
            }
            activeCharacter = character;

        }
    }

    /**
     * Method needed after the activation of the "Hag" card; the method must remove up to three students of the chosen
     * color from the DiningRoom of each player; the method also checks if there are less than three students available
     *
     * @param color chosen
     */
    public void removeStudentsFromRoomsByColor(DiskColor color){
        int availableOfColor;
        for (SchoolBoard s: turnOrder){
            availableOfColor = s.getStudentsInRoomByColor(color);
            for (int i=0; i< Math.min(availableOfColor, hagStudentsToRemove); i++){
                Student tmp = s.getFirstStudentOfColor(color);
                try {
                    s.removeFromDiningRoom(tmp);
                    bag.insertElement(tmp);
                } catch (NotPresent e) {
                    e.printStackTrace();
                }
            }
        }
    }


}