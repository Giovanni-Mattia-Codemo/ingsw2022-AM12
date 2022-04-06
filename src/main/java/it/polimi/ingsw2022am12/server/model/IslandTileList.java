package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Class IslandTileList defines the collection of IslandTileSet remaining in the game
 */
public class IslandTileList {

    private final ArrayList<IslandTileSet> islands;
    private IslandTileSet motherNature;
    private static final int numOfIslandsMAX = 12;

    /**
     * Constructor method of IslandTileList
     */
    public IslandTileList(){
        this.islands = new ArrayList<>();
        for (int i = 0; i< numOfIslandsMAX; i++){
            islands.add(new IslandTileSet());
        }
        updateIslandIDs();
        Random rnd = new Random();
        int index = rnd.nextInt(numOfIslandsMAX);
        motherNature = islands.get(index);
    }

 /*
    public void printIslands(){
        System.out.println("Islands: ");
        for(IslandTileSet is : islands){
            System.out.println("\n island of index "+is.getID()+": ");
            Team owner = is.getOwningTeam();
            if(owner!= null){
                System.out.println("    Owner: "+ is.getOwningTeam().getTeamName());
            }

            System.out.println("    Towers: "+ is.getTowers().size());
            System.out.print("    Students: ");
            is.getStudentCollection().printStudents();
        }
    }

 */

    /**
     * Method moveMotherNature changes the position of motherNature
     *
     * @param island to move mother nature to
     */
    public void moveMotherNature(IslandTileSet island){
        motherNature=island;
    }

    /**
     * Method getMotherNatureIndex returns the numerical position of mother nature inside the IslandTileList islands
     *
     * @return numerical position
     */
    public int getMotherNatureIndex(){
        return islands.indexOf(motherNature);
    }

    /**
     * Method numOfIslandSets returns the number of remaining separate islands in the game.
     *                      Used to check ending condition.
     *
     * @return value of remaining islandTileSets
     */
    public int numOfIslandSets(){
        return islands.size();
    }

    /**
     * Method getByIndex returns the index of the selected IslandTileSet (islands is an ArrayList!)
     *
     * @param idx index requested
     * @return IslandTileSet
     */
    public IslandTileSet getByIndex(int idx){
        if(idx<islands.size()){
        return this.islands.get(idx);
        }else return null;
    }

    /**
     * Method mergeIslands merges two islandTileSets into one
     *
     * @param left first islandTileSet
     * @param islandToCheck second islandTileSet (destination)
     */
    public void mergeIslands(IslandTileSet left, IslandTileSet islandToCheck){
            int leftSize = left.getTowers().size();
            islandToCheck.addIsland(leftSize);
            for(int i=0; i<leftSize; i++){
                islandToCheck.insertTower(left.getFirstTower());
            }
            ArrayList<Student> studentsToMove = left.getStudents();
            int stdSize = studentsToMove.size();
            for (int i = 0; i < stdSize; i++) {
                islandToCheck.insertStudent(studentsToMove.get(0));
            }
            ArrayList<NoEntry> noEntriesToMove = left.getNoEntries();
            int noEntrySize = noEntriesToMove.size();
            for (int i = 0; i < noEntrySize; i++) {
                islandToCheck.insertNoEntries(noEntriesToMove.get(0));
            }

            islands.remove(left);
        updateIslandIDs();
    }

    /**
     * Method checkAndMerge checks if it's possible to merge different islandTileSets with its neighbours
     *
     * @param islandIndex island to be checked
     */
    public void checkAndMerge(int islandIndex){

        int leftIndex;
        int rightIndex;

        if(islandIndex==0){
            leftIndex=islands.size()-1;
        }else leftIndex=islandIndex-1;

        IslandTileSet left = islands.get(leftIndex);
        IslandTileSet islandToCheck = islands.get(islandIndex);

        if(left.getOwningTeam()==islandToCheck.getOwningTeam()){
            if(left!=motherNature){
                mergeIslands(left, islandToCheck);

            }else {
                mergeIslands(islandToCheck,left);

                islandToCheck = left;
            }
        }

        if(islandToCheck.getID()==islands.size()-1){
            rightIndex = 0;
        }else rightIndex=islandToCheck.getID()+1;

        IslandTileSet right = islands.get(rightIndex);
        if(right.getOwningTeam()==islandToCheck.getOwningTeam()){
            if(right!=motherNature){
                mergeIslands(right, islandToCheck);

            }else{
                mergeIslands(islandToCheck, right);

            }
        }
    }


    public void updateIslandIDs(){
        for(IslandTileSet i: islands){
            i.setID(islands.indexOf(i));
        }
    }

    /**
     * Method distanceFromMotherNature calculates Mother's Nature distance from a certain island
     *
     * @param islandTileSet the island of interest
     * @return int "circular" distance from MotherNature to the selected Island (MotherNature must always be moved CLOCKWISE)
     */
    public int distanceFromMotherNature(IslandTileSet islandTileSet){
        if (islands.indexOf(islandTileSet)>getMotherNatureIndex()) return islands.indexOf(islandTileSet)-getMotherNatureIndex();
        return (numOfIslandSets()-getMotherNatureIndex())+islands.indexOf(islandTileSet);
    }

}