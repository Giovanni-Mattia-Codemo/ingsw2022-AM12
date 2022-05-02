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
    private Map<Integer, String> teams;
    private ArrayList<ClientCharacter> characters;
    private String activeCharacter;


}
