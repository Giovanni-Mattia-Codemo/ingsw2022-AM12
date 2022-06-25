package it.polimi.ingsw2022am12.client.model;

import it.polimi.ingsw2022am12.*;
import it.polimi.ingsw2022am12.updateFlag.Flag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlagCharacter;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlagSchool;

import java.util.ArrayList;

/**
 * ClientGame represents the main attributes of the game from the client's side
 */
public class ClientGame {
    private int round;
    private int freeCoins;
    private int turn;
    private boolean isLastRound;
    private boolean mode;
    private int motherNatureIndex;
    private String phase;
    private ArrayList<ClientSchoolBoard> schoolBoards;
    private ArrayList<String> orderedNicks;
    private ArrayList<ClientIsland> islands;
    private ArrayList<ClientStudentCollection> clouds;
    private final String[] professors;
    private ArrayList<ClientTeam> teams;
    private ArrayList<ClientCharacter> characters;
    private String activeCharacter;

    /**
     * Default constructor of the ClientGame class
     */
    public ClientGame(){
        this.professors = new String[5];
    }

    /**
     * Setter method for round
     *
     * @param round number of the round
     */
    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Setter method for freeCoins
     *
     * @param freeCoins number of freeCoins
     */
    public void setFreeCoins(int freeCoins) {
        this.freeCoins = freeCoins;
    }

    /**
     * Setter method for turn
     *
     * @param turn number of the turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Setter method for lastRound
     *
     * @param lastRound true if it is the last round
     */
    public void setLastRound(boolean lastRound) {
        isLastRound = lastRound;
    }

    /**
     * Setter method for mode
     * @param mode boolean value, true is ExpertMode is on
     */
    public void setMode(boolean mode) {
        this.mode = mode;
    }

    /**
     * Setter method for motherNatureIndex
     * @param motherNatureIndex index of the position of mother nature
     */
    public void setMotherNatureIndex(int motherNatureIndex) {
        this.motherNatureIndex = motherNatureIndex;
    }

    /**
     * Setter method for phase
     * @param phase name of the phase
     */
    public void setPhase(String phase) {
        this.phase = phase;
    }

    /**
     * Setter method for SchoolBoards
     * @param schoolBoards list of school boards
     */
    public void setSchoolBoards(ArrayList<ClientSchoolBoard> schoolBoards) {
        this.schoolBoards = schoolBoards;
    }

    /**
     * Setter method for activeCharacter
     * @param activeCharacter name of the active character
     */
    public void setActiveCharacter(String activeCharacter) {
        this.activeCharacter = activeCharacter;
    }

    /**
     * Setter method for characters
     * @param characters list of characters
     */
    public void setCharacters(ArrayList<ClientCharacter> characters) {
        this.characters = characters;
    }

    /**
     * Setter method for clouds
     * @param clouds list of clouds
     */
    public void setClouds(ArrayList<ClientStudentCollection> clouds) {
        this.clouds = clouds;
    }

    /**
     * Setter method for islands
     * @param islands list of islands
     */
    public void setIslands(ArrayList<ClientIsland> islands) {
        this.islands = islands;
    }

    /**
     * Setter method for professors
     * @param i index in the professors' array
     * @param professor my chosen professor
     */
    public void setProfessor(int i, String professor) {
        this.professors[i] = professor;
    }

    /**
     * Setter method for orderedNicks
     * @param orderedNicks the nicknames in order
     */
    public void setOrderedNicks(ArrayList<String> orderedNicks) {
        this.orderedNicks = orderedNicks;
    }

    /**
     * Setter method for teams
     * @param teams list of ClientTeams
     */
    public void setTeams(ArrayList<ClientTeam> teams) {
        this.teams = teams;
    }

    /**
     * Method that iterates on the array list of school boards and returns a School board if the nicks match, else returns
     * null, if it can't find a match
     *
     * @param nick of the player
     * @return ClientSchoolBoard of the player
     */
    public ClientSchoolBoard getSchoolBoardByNick(String nick) {
        for (ClientSchoolBoard s:schoolBoards
             ) {
            if(s.getNick().equals(nick)){
                return s;
            }
        }
        return null;
    }

    /**
     * Method that iterates on the array list of ClientStudentCollections and returns a cloud if the ids match, else returns
     * null, if it can't find a match
     *
     *
     * @param id of my cloud
     * @return cloud which is a clientStudentCollection
     */
    public ClientStudentCollection getCloudByID(int id){
        for (ClientStudentCollection c:clouds
             ) {
            if (c.getID()==id){
                return c;
            }

        }
        return  null;
    }

    /**
     * Getter method for islands
     * @return the list of islands in this current instance of the client's game
     */
    public ArrayList<ClientIsland> getIslands() {
        return islands;
    }

    /**
     * Method that iterates on the array list of ClientIslands and returns an island if the ids match, else returns
     * null, if it can't find a match
     *
     * @param id of the island
     * @return clientIsland selected
     */
    public ClientIsland getIslandByID(int id){
        for (ClientIsland i:islands
             ) {
            if (i.getID()==id){
                return i;
            }


        }
        return null;
    }

    /**
     * Getter method for clouds
     * @return the list of clouds in this current instance of the client's game
     */
    public ArrayList<ClientStudentCollection> getClouds() {
        return clouds;
    }

    /**
     * Getter method for turn
     * @return int turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Getter method for round
     * @return int round
     */
    public int getRound() {
        return round;
    }

    /**
     * Getter method for phase
     * @return String phase
     */
    public String getPhase() {
        return phase;
    }

    /**
     * Getter method for FreeCoins
     * @return int freeCoins
     */
    public int getFreeCoins() {
        return freeCoins;
    }

    /**
     * Getter method for mode
     *
     * @return true if its character mode
     */
    public boolean isCharacterMode() {
        return mode;
    }

    /**
     * Getter method for motherNatureIndex
     * @return int motherNatureIndex
     */
    public int getMotherNatureIndex() {
        return motherNatureIndex;
    }

    /**
     * Getter method for orderedNicks
     * @return ArrayList of orderedNicks
     */
    public ArrayList<String> getOrderedNicks() {
        return orderedNicks;
    }

    /**
     * getCharacterByName iterates on the array of Characters and returns a ClientCharacter if the names match, else returns
     * null, if it can't find a match
     *
     * @param name of the ClientCharacter
     * @return ClientCharacter selected
     */
    public ClientCharacter getCharacterByName(String name){
        for (ClientCharacter c:characters
        ) {
            if (c.getName().equals(name)){
                return c;
            }

        }
        return  null;
    }

    /**
     * Getter method for characters
     * @return the list of characters in this current instance of the client's game
     */
    public ArrayList<ClientCharacter> getCharacters() {
        return characters;
    }

    /**
     * Getter method for activeCharacter
     * @return String activeCharacter
     */
    public String getActiveCharacter() {
        return activeCharacter;
    }

    /**
     * Getter method for professors
     * @return String[] professors
     */
    public String[] getProfessors() {
        return professors;
    }

    /**
     * Getter method for schoolBoards
     * @return the list of schoolBoards in this current instance of the client's game
     */
    public ArrayList<ClientSchoolBoard> getSchoolBoards(){
        return schoolBoards;
    }

    /**
     * Getter method for isLastRound
     * @return boolean isLastRound
     */
    public boolean isLastRound() {
        return isLastRound;
    }

    /**
     * Getter method for teams
     * @return the list of teams in this current instance of the client's game
     */
    public ArrayList<ClientTeam> getTeams() {
        return teams;
    }

    /**
     * getTeamByNick returns the Team to which a certain player belongs
     * @param nick of the player
     * @return team to which the player belongs
     */
    public ClientTeam getTeamByNick(String nick){
        for(ClientTeam team : teams){
            if(team.getPlayer1().equals(nick)||team.getPlayer2().equals(nick)){
                return team;
            }
        }
        return null;
    }

    /**
     * Getter method for the available mages
     * @return the list of available mages in this current instance of the client's game
     */
    public ArrayList<Integer> getAvailableMages(){
        ArrayList<Integer> availableMages = new ArrayList<>();
        availableMages.add(0);
        availableMages.add(1);
        availableMages.add(2);
        availableMages.add(3);
        for(ClientSchoolBoard s:schoolBoards){
            if(s.getMage()!=-1){
                availableMages.removeIf(a->s.getMage()== a);
            }
        }
        return availableMages;
    }

    /**
     * getBoardStringToView uses the current values of the Game's attributes, and writes them all in a string
     *
     * @return string message that represents the state of the Game
     */
    public String getBoardStringToView(){
        String msg = "";
        msg = msg.concat("Phase:"+phase+"\n");
        msg = msg.concat("Round:"+round+"\n");
        msg = msg.concat("Turn:"+turn+"\n");
        if(round==1){
            if(!teams.get(0).getPlayer2().equals("null")){
                msg = msg.concat("The teams in this match are:"+"\n");
                int i = 0;
                for(ClientTeam team:teams){
                    msg = msg.concat("Team "+i+ ": "+team.getPlayer1()+" and "+team.getPlayer2()+"\n");
                    i++;
                }
            }
        }
        msg = msg.concat("Turn order: ");
        for(String nick:orderedNicks){
            msg = msg.concat(nick+" ");
        }
        msg = msg.concat("\n");
        for(ClientSchoolBoard s : schoolBoards){
            msg = msg.concat(s.getNick()+"'s school board"+"\n");
            msg = msg.concat("\t Entrance: id "+s.getEntrance().getID()+". Students: "+s.getEntrance().getStudentsAsString()+"\n");
            msg = msg.concat("\t Dining room: "+s.getDiningRooms().getID()+". Students: "+s.getDiningRooms().getStudentsAsString()+"\n");
            if(mode){
                msg = msg.concat("\t Coins:" + s.getCoins()+"\n");
            }
            msg = msg.concat("\t Towers: "+s.getTowers()+"\n");
            for(int i= 0; i< professors.length; i++){
                if(professors[i].equals(s.getNick())){
                    msg = msg.concat("\t"+DiskColor.RED.getColor(i)+" professor" +"\n");
                }

            }
            msg = msg.concat("\t Assistants: "+ s.getAssistantsPowers()+"\n");


        }
        msg = msg.concat("Islands: \n");
        for (ClientIsland clientIsland: islands){
            msg = msg.concat(clientIsland.getID()+" with students: "+clientIsland.getStudents().getStudentsAsString());
            msg = msg.concat("\t"+clientIsland.getConqueror());
            msg = msg.concat("\nNum of no entries: "+clientIsland.getNumOfNoEntries());
            msg = msg.concat("\n");
        }

        msg = msg.concat("Mother nature position: " +motherNatureIndex+"\n");

        msg = msg.concat("Clouds:"+"\n");
        for(ClientStudentCollection cloud : clouds){
            msg = msg.concat(cloud.getID()+" with students: "+cloud.getStudentsAsString()+"\n");
        }
        if(mode){
            for(ClientCharacter character: characters){
                msg = msg.concat("character "+ character.getName()+". Cost:"+character.getCost()+". ");
                if(character.getStudents()!=null){
                    msg = msg.concat("Students: "+ character.getStudents().getStudentsAsString());
                }else if(character.getNumberOfNoEntries()!=0){
                    msg = msg.concat(character.getNumberOfNoEntries()+" no entries, with id "+ character.getNoEntryCollectionID());
                }
                msg = msg.concat("\n");
            }

            msg = msg.concat("Coins on table: "+freeCoins+"\n");
        }
        if(!activeCharacter.equals("null")){
            msg = msg.concat("The active character is: "+activeCharacter);
        }
        if(isLastRound){
            msg = msg.concat("THIS IS THE LAST ROUND");
        }
        return msg;
    }


    /**
     * updateFromGame changes the old state of the game to the current one if a flag signals some changes
     *
     * @param newGame the new state of the game
     * @param myFlag the update flag that signals the changes
     */
    public void updateFromGame(ClientGame newGame, UpdateFlag myFlag){
        round = newGame.getRound();
        turn = newGame.getTurn();
        phase = newGame.getPhase();
        teams = newGame.getTeams();
        motherNatureIndex = newGame.getMotherNatureIndex();
        freeCoins = newGame.getFreeCoins();
        orderedNicks = newGame.getOrderedNicks();
        for(int i=0; i< professors.length; i++){
            professors[i]=newGame.getProfessors()[i];
        }
        activeCharacter = newGame.getActiveCharacter();
        isLastRound = newGame.isLastRound();

        switch (myFlag.getFlag()){
            case FULLGAME:

                for(String nick: orderedNicks){
                    updateFromGame(newGame, new UpdateFlagSchool(nick));
                }

                updateFromGame(newGame, new UpdateFlag(Flag.ISLANDS));
                updateFromGame(newGame, new UpdateFlag(Flag.CLOUDS));
                for(ClientCharacter character: newGame.getCharacters()){
                    updateFromGame(newGame, new UpdateFlagCharacter(character.getName()));
                }
                break;

            case SCHOOL:
                String nickToCheck = ((UpdateFlagSchool)myFlag).getNick();
                getSchoolBoardByNick(nickToCheck).updateFromSchool(newGame.getSchoolBoardByNick(nickToCheck));
                break;

            case CLOUDS:

                for (ClientStudentCollection cloud:clouds
                     ) {
                    cloud.updateFromCollection(newGame.getCloudByID(cloud.getID()));
                }

                break;

            case ISLANDS:
                ArrayList<ClientIsland> toRemove = new ArrayList<>();
                for (ClientIsland i:islands){
                    ClientIsland tmp = newGame.getIslandByID(i.getID());
                    if(tmp != null){
                        i.updateFromIsland(tmp);
                    }else{
                        toRemove.add(i);
                    }

                }
                islands.removeAll(toRemove);
                break;
            case CHARACTERS:
                String nameToCheck = ((UpdateFlagCharacter)myFlag).getNick();
                if(!nameToCheck.equals("null")){
                    getCharacterByName(nameToCheck).updateFromCharacter(newGame.getCharacterByName(nameToCheck));
                }
                break;
            default:
                break;
        }
    }

    /**
     * getPlayableAssistants method returns the list of available Assistant cards
     * @return list of playableAssistants
     */
    public ArrayList<ClientAssistant> getPlayableAssistants(){
        ArrayList<ClientAssistant> playable = new ArrayList<>(getSchoolBoardByNick(orderedNicks.get(turn)).getAssistants());
        ArrayList<ClientAssistant> toRemove = new ArrayList<>();
        for(int i = 0; i<getSchoolBoardByNick(orderedNicks.get(turn)).getAssistants().size(); i++){
            ClientAssistant tmp = getSchoolBoardByNick(orderedNicks.get(turn)).getAssistants().get(i);
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
            for(int i = 0; i<getSchoolBoardByNick(orderedNicks.get(turn)).getAssistants().size(); i++){

                int currentCardPower = getSchoolBoardByNick(orderedNicks.get(turn)).getAssistants().get(i).getTurnPower();

                if(!wasCardPlayed(currentCardPower)){
                    noOtherPlayableAssistants=false;
                    break;
                }
            }
        }else return true;

        return noOtherPlayableAssistants;
    }


    /**
     * wasCardPlayed returns true if the Assistant card was already played, else false
     *
     * @param turnPower the turnPower of the assistant I want to check
     * @return true if the card was played
     */
    private boolean wasCardPlayed(int turnPower){
        for(int j=0; j<turn; j++){
            int toCheck = getSchoolBoardByNick(orderedNicks.get(j)).getPlayedAssistant();
            if(toCheck==turnPower){
                return true;
            }
        }return false;
    }

}
