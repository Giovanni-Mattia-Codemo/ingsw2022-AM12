package it.polimi.ingsw2022am12.server.model;

import com.google.gson.JsonElement;
import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.characters.*;
import it.polimi.ingsw2022am12.server.model.phases.SetupStrategy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class Game is the instance of the game
 */
public class Game{

    private final static int coinsTOTAL = 20;
    private final ArrayList<String> playerNicks;
    private final int numOfPlayers;
    private int round = 1;
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
    private final static int numOfCharacters = 3;
    private boolean isLastRoundFlag;
    private final boolean isExpertMode;

    private int disksMovedThisTurn;
    private boolean hasMovedMotherNature;

    private final ArrayList<CharacterCard> characterCards;
    private CharacterCard activeCharacterCard;

    /**
     * Constructor method of the class
     *
     * @param playerNicks of the specific game
     * @param characterMode boolean if the Expert Mode is active
     */
    public Game(ArrayList<String> playerNicks, boolean characterMode){

        this.isExpertMode = characterMode;
        this.mages = new ArrayList<>();
        this.currentStrategy = new SetupStrategy();
        this.playerNicks = playerNicks;
        this.numOfPlayers = playerNicks.size();
        this.freeCoins = new CoinCollection();
        this.turnOrder= new ArrayList<>();
        this.islandList = new IslandTileList();
        this.bag = new Bag();
        this.clouds = new StudentDiskCollection[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            clouds[i]= new StudentDiskCollection();
        }
        this.teams = new ArrayList<>();
        this.professors = new SchoolBoard[5];
        this.characterCards = new ArrayList<>();
    }

    /**
     * Method assignTeams assigns to each schoolBoard in turnOrder a team
     */
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
    public void setUp() {

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

        for(int i=0; i<maxNumOfMages; i++){
            this.mages.add(new Mage(i));
        }

        fillClouds();

        if(isExpertMode){
            setUpForExpertMode();
        }

    }

    /**
     * Method setUpForExpertMode is used to set all the initial values of the attributes of the game, when the expertMode
     * is selected
     */
    private void setUpForExpertMode(){
        for (int i=0; i<coinsTOTAL; i++){
            Coin tmp = new Coin();
            freeCoins.insertElement(tmp);
        }
        for(SchoolBoard s : turnOrder){
            Coin tmp = freeCoins.getFirstCoin();
            freeCoins.removeElement(tmp);
            s.insertCoin(tmp);
        }
        for(int i=0; i<numOfCharacters; i++){
            Random rnd= new Random();
            boolean taken;
            int x;
            do {
                taken=false;
                x = rnd.nextInt(12);
                for(CharacterCard c: characterCards){
                    if (x == c.getName().getValue()) {
                        taken = true;
                        break;
                    }
                }
            }while(taken);
            characterCards.add(CharacterCreator.createCharacter(x));
        }
        for (CharacterCard c:characterCards) {
            c.initCharacter(this);
        }
    }

    /**
     * getCurrentStrategy returns the current phase strategy
     *
     * @return the current strategy used in the game
     */
    public PhaseStrategy getCurrentStrategy(){
        return  currentStrategy;
    }

    /**
     * Method only used for testing
     *
     * @return CoinCollection
     */
    public CoinCollection getFreeCoins(){
        return freeCoins;
    }

    /**
     * Method only used for testing
     *
     * @param characterNum of the character
     */
    public void addCharacter(int characterNum){
        CharacterCard character = CharacterCreator.createCharacter(characterNum);
        character.initCharacter(this);
        if(!this.getAvailableCharacters().stream()
                .map(CharacterCard::getName)
                .collect(Collectors.toList())
                .contains(character.getName())){
            characterCards.add(character);
        }
    }

    /**
     * Method used only for testing
     *
     * @return teams
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Method only used for testing
     *
     * @param characterName to be set active
     */
    public void forceSetActiveCharacter(CharacterCard characterName){
        activeCharacterCard = characterName;
    }

    /**
     * Method only used for testing
     *
     * @return IslandTileList
     */
    public IslandTileList getIslandList(){
        return islandList;
    }

    /**
     * Method only used for testing
     *
     * @return ArrayList turnOrder
     */
    public ArrayList<SchoolBoard> getTurnOrder(){
        return turnOrder;
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
     * method getClouds returns all the clouds
     *
     * @return StudentDiskCollection
     */
    public StudentDiskCollection[] getClouds() {
        return clouds;
    }

    /**
     * method isExpertMode returns a boolean value
     *
     * @return boolean true if is Expert mMde
     */
    public boolean isExpertMode() {
        return isExpertMode;
    }

    /**
     * Getter method for isLastRoundFlag
     *
     * @return isLastRoundFlag
     */
    public boolean getIsLastRoundFlag(){
        return isLastRoundFlag;
    }

    /**
     * Getter method for activeCharacterCard
     *
     * @return activeCharacterCard
     */
    public CharacterCard getActiveCharacterCard(){
        return activeCharacterCard;
    }

    /**
     * Getter method for ValidActions
     *
     * @return ArrayList<PossibleAction> the list of valid actions
     */
    public ArrayList<PossibleAction> getValidActions(){
        return currentStrategy.getValidActions(this);
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
     * Getter method for professors
     *
     * @return SchoolBoard[] array of professors
     */
    public SchoolBoard[] getProfessors() {
        return professors;
    }

    /**
     * checkIfIslandDrawableByID returns true if the cloud is drawable
     * @param ID of the cloud
     * @return boolaen value
     */
    public boolean checkIfCloudDrawableByID(int ID){
        for (int i= 0; i<numOfPlayers; i++){
            if(clouds[i].getID()==ID){
                return !clouds[i].getAllStudents().isEmpty();
            }
        }
        return false;
    }

    /**
     * Getter method for round
     *
     * @return int number of round
     */
    public int getRound() {
        return round;
    }

    /**
     * Getter method for turn
     *
     * @return int number of turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Method getSelectableClouds returns a boolean that says if a selected cloud is empty or not;
     * it checks all the clouds in the "clouds array", if the island is not empty already,
     * meaning that somebody already chose it, it can be selected
     *
     * @return true if the cloud is not empty, false otherwise
     */
    public ArrayList<Selectable> getDrawableClouds(){

        ArrayList<Selectable> result = new ArrayList<>();
        for(int i=0; i<numOfPlayers; i++){
            if(!clouds[i].getAllStudents().isEmpty()){
                result.add(clouds[i]);
            }
        }
        return result;
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
     * Method getActiveCharacterName returns a String with the name of the character that has been activated
     * (it can also return null, if no character has been activated yet)
     *
     * @return name of the active character in the form of a string
     */
    public CharacterName getActiveCharacterName(){
        if (activeCharacterCard ==null){
            return null;
        } else return activeCharacterCard.getName();
    }

    /**
     * Method getAvailableMages returns the list of mages that haven't been picked already
     *
     * @return ArrayList of mages not picked
     */
    public ArrayList<Mage> getAvailableMages() {
        return new ArrayList<>(mages);
    }

    /**
     *
     * @return a list of available CharacterCards
     */
    public ArrayList<CharacterCard> getAvailableCharacters(){
        return characterCards;
    }


    /**
     * Method selectMage is used from a player to pick a mage
     *
     * @param mageId ID
     *
     */
    public void selectMage(int mageId){
        Mage tmp = null;
        for(Mage m: mages) {
            if (m.getID()==mageId){
                getCurrentSchoolBoard().setMage(m);
                tmp=m;
            }
        }
        mages.remove(tmp);
    }

    /**
     * Method moveStudentFromCardToRoom defines how I can move a student from a card to an island
     *
     * @param color of the student
     * @param islandID of the island where I want to put the student
     */
    public void moveStudentFromCardToIsland(DiskColor color, int islandID){
        StudentDiskCollection monkStudents = ((CharacterMonk)getActiveCharacterCard()).getStudents();
        if(monkStudents.getFirstStudentOfColor(color).isPresent()){
            Student tmp = monkStudents.getFirstStudentOfColor(color).get();
            monkStudents.removeElement(tmp);
            islandList.getByIndex(islandID).insertStudent(tmp);
        }
    }

    /**
     * Method moveStudentFromCardToRoom defines how I can move a student from a card to the Dining Room
     *
     * @param color of the student
     */
    public void moveStudentFromCardToRoom(DiskColor color){

        StudentDiskCollection tmp = ((CharacterPrincess)getActiveCharacterCard()).getStudents();
        if(tmp.getFirstStudentOfColor(color).isPresent()){
            Student temp = tmp.getFirstStudentOfColor(color).get();
            tmp.removeElement(temp);
            getCurrentSchoolBoard().insertToDiningRoom(temp);

            if((getCurrentSchoolBoard().getStudentsInRoomByColor(temp.getColor())%3)==0){
                collectCoin();
            }
        }
    }

    /**
     * Method conquerIsland is called when motherNature stops on an island to check if it can be conquered.
     * It is also used when a character card is called upon a specific island.
     *
     * @param index of the island to check
     */
    public void conquerIsland(int index){
        IslandTileSet islandToConquer = islandList.getByIndex(index);

        if(islandToConquer.getNoEntries().size()!=0){
                islandToConquer.giveBackNoEntry();
        }else {

            int[] scores= new int[teams.size()];
            Team team;
            SchoolBoard temporarySchoolBoard;
            for (DiskColor c: DiskColor.values()) {
                temporarySchoolBoard = professors[c.getValue()];
                if(getActiveCharacterCard()!=null&&getActiveCharacterName()==CharacterName.CHARACTER_MERCHANT&&c==((CharacterMerchant)getActiveCharacterCard()).getColor()){
                    continue;
                }
                if(temporarySchoolBoard != null){
                    for (Team t :teams ) {
                        if(t.getSchoolBoards().contains(temporarySchoolBoard)){
                            scores[teams.indexOf(t)]+= islandToConquer.getIslandsStudentsOfColor(c);
                        }
                    }
                }
            }
            Team owner = islandToConquer.getOwningTeam();
            if(owner!= null&&!(getActiveCharacterCard()!=null&&getActiveCharacterName()==CharacterName.CHARACTER_CENTAUR)){
                scores[teams.indexOf(owner)]+=islandToConquer.getNumOfIslandsInThisSet();
            }
            if(getActiveCharacterCard()!=null&&getActiveCharacterName()==CharacterName.CHARACTER_KNIGHT){
                for(Team t: teams){
                    if (t.getSchoolBoards().contains(getCurrentSchoolBoard())){
                        scores[teams.indexOf(t)]+=2;
                    }
                }

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
            if (!tie) {
                team = teams.get(winnerId);
                if (team != islandToConquer.getOwningTeam()) {
                    ArrayList<Tower> towersToMove = islandToConquer.getTowers();
                    islandToConquer.removeAllTowers();
                    for (Tower t : towersToMove) {
                        t.getTeam().getSchoolBoardWithTowers().insertTower(t);
                    }

                    for (int i = 0; i < islandToConquer.getNumOfIslandsInThisSet(); i++) {
                        Tower towerToMove = team.getSchoolBoardWithTowers().getFirstTower();
                        if (towerToMove != null) {
                            islandToConquer.insertTower(towerToMove);
                            try {
                                team.getSchoolBoardWithTowers().removeTower(towerToMove);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else endGame();


                    }
                    islandList.checkAndMerge(index);
                    if(islandList.numOfIslandSets()<=3){
                        endGame();
                    }
                }
            }

        }
    }

    /**
     * Method drawStudentFromBag is used to fill a StudentDiskCollection
     *
     * @return random student
     */
    public Student drawStudentFromBag(){
        Student tmp =bag.draw();
        if (tmp==null){
            isLastRoundFlag=true;
        }
        return tmp;
    }


    /**
     * Method playAssistant is used in the planning phase when the player has to select an assistant from them deck
     *
     * @param assistantPower selected
     */
    public void playAssistant(int assistantPower){
        getCurrentSchoolBoard().playAssistant(assistantPower);
    }

    /**
     *Method moveFromEntranceToIsland is used to move a student from the entrance to one specific island
     *
     * @param studentColor of student to be moved
     * @param islandID of islandTileSet chosen
     */
    public void moveStudentFromEntranceToIsland(DiskColor studentColor, int islandID){
        if(getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(studentColor).isPresent()){
            Student selectedStudent = getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(studentColor).get();
            IslandTileSet islandTileSet = islandList.getByIndex(islandID);

            getCurrentSchoolBoard().getEntrance().removeElement(selectedStudent);
            islandTileSet.insertStudent(selectedStudent);
            disksMovedThisTurn++;
        }
    }

    /**
     * Method moveMotherNature is used to move mother nature
     *
     * @param index of island to move mother nature to
     */
    public void moveMotherNature(int index){
        hasMovedMotherNature = true;
        IslandTileSet tmp = islandList.getByIndex(index);
        islandList.moveMotherNature(tmp);
        conquerIsland(islandList.getMotherNatureIndex());
    }

    /**
     * Method moveFromEntranceToRoom moves a students from entrance to room and checks if the player wins the
     * professor of the specific color of the student they moved
     *
     * @param colorInEntrance color of student to move
     */
    public void moveStudentFromEntranceToRoom(DiskColor colorInEntrance){

        SchoolBoard s = getCurrentSchoolBoard();
        if(s.getEntrance().getFirstStudentOfColor(colorInEntrance).isPresent()){
            Student student = s.getEntrance().getFirstStudentOfColor(colorInEntrance).get();
            s.moveStudentFromEntranceToRoom(student);

            if((s.getStudentsInRoomByColor(colorInEntrance)%3)==0){
                collectCoin();
            }

            SchoolBoard owner = professors[student.getColor().getValue()];
            if(owner!= null&&getActiveCharacterName()==CharacterName.CHARACTER_HOST) {
                if (owner.getStudentsInRoomByColor(student.getColor()) <= s.getStudentsInRoomByColor(student.getColor())) {
                    owner = s;
                }
            }else if(owner!= null) {
                if (owner.getStudentsInRoomByColor(student.getColor()) < s.getStudentsInRoomByColor(student.getColor())) {
                    owner = s;
                }
            }else owner = s;
            professors[student.getColor().getValue()]=owner;
            disksMovedThisTurn++;
        }
    }

    /**
     * Method insertNoEntry places a noEntry on a selected island
     *
     * @param destination index of my selected island
     */
    public void insertNoEntry(int destination){
        IslandTileSet destinationIsland = islandList.getByIndex(destination);
        NoEntry noEntry = ((CharacterHerbalist)getActiveCharacterCard()).getNoEntryCollection().getFirstNoEntry();
        noEntry.getCharacterNoEntryCollection().removeElement(noEntry);
        destinationIsland.insertNoEntries(noEntry);
    }

    /**
     * Method jesterSwap defines the functioning of a swap done by the JesterCar5d
     *
     * @param colorOfCharStudent color of the Student taken from the JesterCard
     * @param colorOfEntranceStudent color of the Student from the Entrance
     */
    public void jesterSwap(DiskColor colorOfCharStudent, DiskColor colorOfEntranceStudent){
        CharacterJester jester =((CharacterJester)getActiveCharacterCard());

        if(jester.getStudents().getFirstStudentOfColor(colorOfCharStudent).isPresent()){
            Student st0 = jester.getStudents().getFirstStudentOfColor(colorOfCharStudent).get();
            if(getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(colorOfEntranceStudent).isPresent()){
                Student st1 = getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(colorOfEntranceStudent).get();
                jester.getStudents().removeElement(st0);
                getCurrentSchoolBoard().getEntrance().removeElement(st1);
                jester.getStudents().insertElement(st1);
                getCurrentSchoolBoard().getEntrance().insertElement(st0);
            }
        }
    }

    /**
     * Method swapStudents calls the method swapStudents in the current player schoolBoard
     *
     * @param colorOfEntranceStudent to swap
     * @param colorOfRoomStudent to swap
     */
    public void swapStudents(DiskColor colorOfEntranceStudent, DiskColor colorOfRoomStudent){

        Student s0 = getCurrentSchoolBoard().getFirstStudentInRoomOfColor(colorOfRoomStudent);

        if(getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(colorOfEntranceStudent).isPresent()){
            Student s1 = getCurrentSchoolBoard().getEntrance().getFirstStudentOfColor(colorOfEntranceStudent).get();

            getCurrentSchoolBoard().swapStudents(s0, s1);

            if((getCurrentSchoolBoard().getStudentsInRoomByColor(s1.getColor())%3)==0){
                collectCoin();
            }
        }
    }

    /**
     * Method drawFromClouds is called to move the students from the selected cloud to the player's entrance
     *
     * @param id picked
     */
    public void drawFromCloud(int id){
        StudentDiskCollection cloud = null;
        for(int i=0; i<numOfPlayers; i++){
            if(clouds[i].getID()==id){
                cloud = clouds[i];
            }
        }
        int size = cloud.amount();
        Student tmp;
        for(int i = 0; i<size; i++){
            tmp = cloud.getByIndex(0);
            cloud.removeElement(tmp);
            getCurrentSchoolBoard().insertToEntrance(tmp);
        }
    }

    /**
     * Method fillIslands is used in the setup method to fill the islands following the setup rules of the game
     */
    public void fillIslands(){
        Student student;
        for (int i=0; i<maxNumOfIslands; i++){
            if (i!=(islandList.getMotherNatureIndex()+6)%12&&i!=islandList.getMotherNatureIndex()){
                student = drawStudentFromBag();
                if(student != null){
                    islandList.getByIndex(i).insertStudent(student);
                }
            }
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
                student = drawStudentFromBag();
                cloud.insertElement(student);
            }
        }
    }

    /**
     * Method checkIfIslandInRange checks if an island is reachable by a player
     *
     * @param island to check
     * @return true if the selected island is reachable, false otherwise
     */
    public boolean checkIfIslandInRange(IslandTileSet island){
        int range=getCurrentSchoolBoard().getLastPlayedAssistantRange();
        if(getActiveCharacterName()==CharacterName.CHARACTER_BEGGAR){
            range+=2;
        }
        return islandList.distanceFromMotherNature(island) <= range && islandList.distanceFromMotherNature(island) > 0;
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

    public ArrayList<Assistant> getPlayableAssistants(){
        ArrayList<Assistant> playable = new ArrayList<>(getCurrentSchoolBoard().getRemainingAssistants());
        ArrayList<Assistant> toRemove = new ArrayList<>();
        for(int i = 0; i<getCurrentSchoolBoard().getRemainingAssistants().size(); i++){
            Assistant tmp = getCurrentSchoolBoard().getRemainingAssistants().get(i);
            if(!isAssistantPlayable(tmp.getTurnPower())){
                toRemove.add(tmp);
            }
        }
        playable.removeAll(toRemove);
        return playable;
    }

    /**
     * Method isAssistantPlayable allows me to know if I can play the Assistant of my choice
     *
     * @param assistantPower the power of my chosen assistantCard
     * @return true if the assistant is playable, false otherwise
     */
    private boolean isAssistantPlayable(int assistantPower){
        boolean cardWasPlayed;
        boolean noOtherPlayableAssistants = true;

        cardWasPlayed = wasCardPlayed(assistantPower);

        if(cardWasPlayed){
            for(int i = 0; i<getCurrentSchoolBoard().getRemainingAssistants().size(); i++){

                int currentCardPower = getCurrentSchoolBoard().getRemainingAssistants().get(i).getTurnPower();


                if(!wasCardPlayed(currentCardPower)){
                    noOtherPlayableAssistants=false;
                    break;
                }
            }
        }else return true;

        return noOtherPlayableAssistants;
    }


    private boolean wasCardPlayed(int turnPower){
        for(int j=0; j<turn; j++){
            int toCheck = turnOrder.get(j).getLastPlayedAssistantPower();
            if(toCheck==turnPower){
                return true;
            }
        }return false;
    }


    /**
     * Method correctOrder sorts the turnOrder with the correct order of players for the next action phase
     */
    public void correctOrder(){
        turnOrder.sort(new SortByPower());
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
     * Method changePhase id used to change the strategy of the game in order to switch phases
     *
     * @param nextStrategy to be applied
     */
    public void changePhase(PhaseStrategy nextStrategy){
        currentStrategy = nextStrategy;
    }

    /**
     * Method endGame sets the winner team
     */
    public void endGame(){
        Team winningTeam;
        int score = 10;
        for (Team t: teams){
            if(t.getSchoolBoardWithTowers().getTowersNumber()<score){
                winningTeam = t;
                score = winningTeam.getSchoolBoardWithTowers().getTowersNumber();
            }
        }
    }

    /**
     * Method nextTurn is used to change round; if the round is the 10th, it is also the last
     */
    public void nextRound(){
        this.round++;
        if(round==10) isLastRoundFlag=true;
    }

    /**
     * Method nextTurn is used to iterate on the turnOrder and keep track of the players' turn
     */
    public void nextTurn(){
        hasMovedMotherNature=false;
        disksMovedThisTurn = 0;
        if(activeCharacterCard!=null){
            activeCharacterCard.setWasUsed(false);
            activeCharacterCard = null;
        }
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
     * Method payCoins is used when a call to a character is made
     *
     * @param coinsUsed cost of character usage
     */
    public void payCoins(int coinsUsed){
        Coin toBeMoved;
        for(int i=0; i<coinsUsed; i++){
            toBeMoved = getCurrentSchoolBoard().getFirstCoin();
            getCurrentSchoolBoard().removeCoin(toBeMoved);
            freeCoins.insertElement(toBeMoved);
        }
    }

    /**
     * Method collectCoin moves a coin from freeCoins to a player's schoolBoard, if a coin is available
     */
    public void collectCoin(){
        Coin toBeMoved = freeCoins.getFirstCoin();
        if(toBeMoved!=null){
            freeCoins.removeElement(toBeMoved);
            getCurrentSchoolBoard().insertCoin(toBeMoved);
        }
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
     * Method payAndActivateCharacter is used to pay and set active the chosen character,
     * it also increases the cost of the selected character by one the first time
     *
     * @param characterName selected
     */
    public void payAndSetActiveCharacter(CharacterName characterName){

        for (CharacterCard characterCard:characterCards) {
            if (characterCard.getName().equals(characterName)){
                if (characterCards.contains(characterCard)){
                            int cost = characterCard.getCost();
                            payCoins(cost);
                            if(!characterCard.wasPayedBefore()){
                                Coin coinTmp = freeCoins.getFirstCoin();
                                freeCoins.removeElement(coinTmp);
                                characterCard.insertCoin(coinTmp);
                            }
                            activeCharacterCard = characterCard;
                }
            }
        }


    }

    /**
     * getPlayableCharacters returns the Characters that have a cost compatible with the coins possessed by a player
     *
     * @return ArrayList</Selectable> the characters that can be played
     */
    public ArrayList<Selectable> getPlayableCharacters(){
        ArrayList<Selectable> result = new ArrayList<>();
        for (CharacterCard c: getAvailableCharacters()
             ) {
            if(getCurrentSchoolBoard().getNumOfCoins()>=c.getCost()){
                result.add(c);
            }
        }
        return result;

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
                Student tmp = s.getFirstStudentInRoomOfColor(color);
                s.removeFromDiningRoom(tmp);
                bag.insertElement(tmp);

            }
        }

    }

}