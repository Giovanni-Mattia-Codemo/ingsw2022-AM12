package it.polimi.ingsw2022am12.client;

import java.util.ArrayList;
import java.util.Map;

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
    private ClientStudentCollection[] clouds;
    private String[] professors;
    private ArrayList<ClientTeam> teams;
    private ArrayList<ClientCharacter> characters;
    private String activeCharacter;


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


    public void setTeams(ArrayList<ClientTeam> teams) {
        this.teams = teams;
    }
}
