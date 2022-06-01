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

    public IslandTileList(ArrayList<IslandTileSet> islands, int motherNature){
        this.islands = new ArrayList<>(islands);
        this.motherNature = getByIndex(motherNature);

    }

    /**
     * getIslandsAsSelectable returns an array of islands as Selectable objects
     *
     * @return ArrayList</Selectable> array of selectable islands
     */
    public ArrayList<Selectable> getIslandsAsSelectable(){
        return new ArrayList<>(islands);
    }


    public ArrayList<IslandTileSet> getIslands(){
        return islands;
    }

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
     * @param islandToCheck second islandTileSet (destination of the content of both islands)
     */
    public void mergeIslands(IslandTileSet left, IslandTileSet islandToCheck){

            int leftSize = left.getTowers().size(); //determines if the first island is a single island or a cluster of islands

            islandToCheck.addIsland(leftSize);
            for(int i=0; i<leftSize; i++){
                islandToCheck.insertTower(left.getFirstTower());  //puts all the towers contained in the first set in the second one
            }

            ArrayList<Student> studentsToMove = left.getStudents();  //determines how many students the first island has

            int stdSize = studentsToMove.size();

            for (int i = 0; i < stdSize; i++) {
                islandToCheck.insertStudent(studentsToMove.get(0));   //puts all the students contained in the first set in the second one
            }

            ArrayList<NoEntry> noEntriesToMove = left.getNoEntries();  //determines how many noEntries the first island has

            int noEntrySize = noEntriesToMove.size();

            for (int i = 0; i < noEntrySize; i++) {
                islandToCheck.insertNoEntries(noEntriesToMove.get(0));    //puts all the noEntries contained in the first set in the second one
            }

            islands.remove(left);   //the first set is now useless, so I remove it from the list of islands
            updateIslandIDs();    //I update the number of islands, since now there is one less
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
            leftIndex=islands.size()-1;   //we are in a circle, if my island is the first, the one on its left is the last one
        }else leftIndex=islandIndex-1;    //else it's just the index of my island decreased by one

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


    /**
     * Method updateIslandIDs sets new IDs for my islands, after a merge
     */
    private void updateIslandIDs(){
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