package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;

/**
 * Class Team defines the teams playing the game
 */
public class Team {
    private ArrayList<String> nicks;
    private final ArrayList<SchoolBoard> schoolBoards;

    /**
     * Constructor method of Team creates a new ArrayList of school boards
     */
    public Team(){
        this.schoolBoards = new ArrayList<>();
    }

    /**
     * Method getSchoolBoardWithTowers returns the reference to attribute schoolBoardWithTowers
     *
     * @return this schoolBoardWithTowers
     */
    public SchoolBoard getSchoolBoardWithTowers(){
        return schoolBoards.get(0);
    }

    /**
     * Method addSchoolBoard adds a new school board to the team
     *
     * @param schoolBoard to be added
     */
    public void addSchoolBoard(SchoolBoard schoolBoard){
        schoolBoards.add(schoolBoard);
    }

    /**
     * Method getSchoolBoards returns the ArrayList of schoolBoards
     *
     * @return schoolBoards of the team
     */
    public ArrayList<SchoolBoard> getSchoolBoards(){
        return schoolBoards;
    }

    /**
     * addNick adds the nick of the player to a list
     *
     * @param nick of the player
     */
    public void addNick(String nick){
        if(nicks==null){
            nicks= new ArrayList<>();
        }
        nicks.add(nick);
    }

    /**
     * setSchoolsFromGame adds the schoolBoards to a certain team according to the current state of the game
     * @param game the current instance of my game
     */
    public void setSchoolsFromGame(Game game){
        for(String nick: nicks){
            schoolBoards.add(game.getSchoolBoardByNick(nick));
        }
    }

    /**
     * contains method checks if the schoolBoard in input is in this Team
     * @param s the schoolBoard I want to check
     * @return true if the schoolBoard is in the Team, false otherwise
     */
    public boolean contains(SchoolBoard s){
        for (SchoolBoard sc:schoolBoards
             ) {

            if(sc.equals(s)){
                return true;
            }

        }
        return false;
    }
}
