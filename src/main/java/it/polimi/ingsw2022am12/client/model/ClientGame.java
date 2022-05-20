package it.polimi.ingsw2022am12.client.model;

import it.polimi.ingsw2022am12.*;
import java.util.ArrayList;

/**
 * ClientGame represents the main attributes of the game from the client's side
 */
public class ClientGame {
    private String thisClientNick;
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
     * getThisClientNick getter method of thisClientNick
     *
     * @return the Nick of a single client
     */
    public String getThisClientNick() {
        return thisClientNick;
    }

    /**
     * Setter method of thisClientNick
     *
     * @param thisClientNick the Nick of a single client
     */
    public void setThisClientNick(String thisClientNick) {
        this.thisClientNick = thisClientNick;
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

    public ClientSchoolBoard getSchoolBoardByNick(String nick) {
        for (ClientSchoolBoard s:schoolBoards
             ) {
            if(s.getNick().equals(nick)){
                return s;
            }
        }
        return null;
    }

    public ClientStudentCollection getCloudByID(int id){
        for (ClientStudentCollection c:clouds
             ) {
            if (c.getID()==id){
                return c;
            }

        }
        return  null;
    }

    public ClientIsland getIslandByID(int id){
        for (ClientIsland i:islands
             ) {
            if (i.getID()==id){
                return i;
            }


        }
        return null;
    }

    public int getTurn() {
        return turn;
    }

    public int getRound() {
        return round;
    }

    public String getPhase() {
        return phase;
    }

    public int getFreeCoins() {
        return freeCoins;
    }

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

    public ClientCharacter getCharacterByName(String name){
        for (ClientCharacter c:characters
        ) {
            if (c.getName().equals(name)){
                return c;
            }

        }
        return  null;
    }

    public String getActiveCharacter() {
        return activeCharacter;
    }

    public String[] getProfessors() {
        return professors;
    }

    public boolean isLastRound() {
        return isLastRound;
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
            msg = msg.concat(clientIsland.getID()+" with students: "+clientIsland.getStudents().getStudentsAsString()+"\n");

        }

        msg = msg.concat("Mother nature position: " +motherNatureIndex+"\n");

        msg = msg.concat("Clouds:"+phase+"\n");
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


    public void updateFromGame(ClientGame newGame, UpdateFlag myFlag){
        round = newGame.getRound();
        turn = newGame.getTurn();
        phase = newGame.getPhase();
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

                break;

            case SCHOOL:
                String nickTocheck = ((UpdateFlagSchool)myFlag).getNick();
                getSchoolBoardByNick(nickTocheck).updateFromSchool(newGame.getSchoolBoardByNick(nickTocheck));

                break;

            case CLOUDS:

                for (ClientStudentCollection cloud:clouds
                     ) {
                    cloud.updateFromCollection(newGame.getCloudByID(cloud.getID()));
                }

                break;

            case ISLANDS:
                for (ClientIsland i:islands
                     ) {

                    ClientIsland tmp = newGame.getIslandByID(i.getID());
                    if(tmp != null){
                        i.updateFromIsland(tmp);
                    }else{
                        islands.remove(i);
                    }


                }

                break;
            case CHARACTERS:
                String nameToCheck = ((UpdateFlagCharacter)myFlag).getNick();
                getCharacterByName(nameToCheck).updateFromCharacter(newGame.getCharacterByName(nameToCheck));
                break;
            default:
                break;
        }
    }

}
