package it.polimi.ingsw2022am12.client;

import it.polimi.ingsw2022am12.server.model.DiskColor;

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

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public void setMotherNatureIndex(int motherNatureIndex) {
        this.motherNatureIndex = motherNatureIndex;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public void setSchoolBoards(ArrayList<ClientSchoolBoard> schoolBoards) {
        this.schoolBoards = schoolBoards;
    }

    public void setActiveCharacter(String activeCharacter) {
        this.activeCharacter = activeCharacter;
    }

    public void setCharacters(ArrayList<ClientCharacter> characters) {
        this.characters = characters;
    }

    public void setClouds(ArrayList<ClientStudentCollection> clouds) {
        this.clouds = clouds;
    }


    public void setIslands(ArrayList<ClientIsland> islands) {
        this.islands = islands;
    }

    public void setProfessor(int i, String professor) {
        this.professors[i] = professor;
    }

    /**
     * Setter method for teams
     * @param teams list of ClientTeams
     */
    public void setTeams(ArrayList<ClientTeam> teams) {
        this.teams = teams;
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
        msg = msg.concat("Turn order:"+"\n");
        for(ClientSchoolBoard s : schoolBoards){
            msg = msg.concat(s.getNick()+"'s school board"+"\n");
            msg = msg.concat("\t Entrance: id "+s.getEntrance().getID()+". Students: "+s.getEntrance().getStudentsAsString()+"\n");
            msg = msg.concat("\t Dining room: "+". Students: "+s.getDiningRooms().getStudentsAsString()+"\n");
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
        return msg;
    }

}
