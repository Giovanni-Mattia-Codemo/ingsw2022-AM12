package it.polimi.ingsw2022am12.server;

import java.util.ArrayList;
import java.util.HashSet;

public class IslandTileList {

    private ArrayList<IslandTileSet> islands;
    private IslandTileSet motherNature;
    private static int islandMAX = 12;

    /**
     * Constructor method of IslandTileList
     */
    public IslandTileList(){
        this.islands = new ArrayList<IslandTileSet>();
        for (int i = 0; i<islandMAX; i++){
            islands.add(new IslandTileSet());
        }
        motherNature = islands.get((int) (Math.random()*11));
    }

    /**
     * Method getMotherNature returns the IslandTileSet containing Mother Nature
     *
     * @return islandTileSet
     */
    public IslandTileSet getMotherNature(){
        return motherNature;
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
              ArrayList<IslandTileSet> temp1 = new ArrayList<>(
                    new HashSet<>(tmp));
              return temp1;
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
}