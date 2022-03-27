package it.polimi.ingsw2022am12.server.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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
        Random rnd = new Random();
        int index = rnd.nextInt(numOfIslandsMAX);
        motherNature = islands.get( index);
    }



    /**
     * Method moveMotherNature changes the position of motherNature
     *
     * @param steps moves of mother nature
     */
    public void moveMotherNature(int steps){
        motherNature=islands.get((islands.indexOf(motherNature)+steps)%islands.size());
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
        return this.islands.get(idx);
    }

    /**
     * Method getIslandsInRange returns the reachable islands given a numerical range in input
     *
     * @param range max possible move of mother nature
     * @return ArrayList of reachable islands
     */
    public ArrayList<IslandTileSet> getIslandsInRange(int range){
        ArrayList<IslandTileSet> tmp = new ArrayList<>();
        int temp = range - (islands.size()-getMotherNatureIndex()+1);
        if(temp<=0) tmp.addAll(islands.subList(getMotherNatureIndex(), getMotherNatureIndex()+range));
        else {tmp.addAll(islands.subList(getMotherNatureIndex()+1, islands.size()-1));
              tmp.addAll(islands.subList(0, temp-1));
            return new ArrayList<>(
                  new HashSet<>(tmp));
        }
        return tmp;
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
    }

    /**
     * Method checkAndMerge checks if it's possible to merge different islandTileSets with its neighbours
     *
     * @param islandIndex island to be checked
     */
    public void checkAndMerge(int islandIndex){
        IslandTileSet left = islands.get((islandIndex-1)%islands.size());
        IslandTileSet islandToCheck = islands.get(islandIndex);

        if(left.getOwningTeam()==islandToCheck.getOwningTeam()){
            if(left!=motherNature){
                mergeIslands(left, islandToCheck);

            }else {
                mergeIslands(islandToCheck,left);

                islandToCheck = left;
            }
        }

        IslandTileSet right = islands.get((islands.indexOf(islandToCheck)+1)%islands.size());
        if(right.getOwningTeam()==islandToCheck.getOwningTeam()){
            if(right!=motherNature){
                mergeIslands(right, islandToCheck);

            }else{
                mergeIslands(islandToCheck, right);
            }
        }
    }

    public int distanceFromMotherNature(IslandTileSet islandTileSet){
        if (islands.indexOf(islandTileSet)>getMotherNatureIndex()) return islands.indexOf(islandTileSet)-getMotherNatureIndex();
        return (numOfIslandSets()-getMotherNatureIndex())+islands.indexOf(islandTileSet);
    }

}