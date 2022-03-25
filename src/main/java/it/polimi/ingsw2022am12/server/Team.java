package it.polimi.ingsw2022am12.server;

import java.util.ArrayList;

/**
 * Class Team defines the teams playing
 */
public class Team {

    private ArrayList<SchoolBoard> schoolBoards;

    /**
     * Constructor method of Team creates a new ArrayList of school boards
     */
    public Team(){
        this.schoolBoards = new ArrayList<SchoolBoard>();
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
     * @return schoolBoards
     */
    public ArrayList<SchoolBoard> getSchoolBoards(){
        return schoolBoards;
    }
}
