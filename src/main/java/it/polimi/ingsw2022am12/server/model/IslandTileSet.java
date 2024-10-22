package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.DiskColor;
import java.util.ArrayList;

/**
 * Class IslandTileSet defines a single island or the aggregation of more islands
 */
public class IslandTileSet implements Selectable {

    private int ID;
    private String owner;
    private final StudentDiskCollection students;
    private final NoEntryCollection noEntries;
    private int numOfIslandsInThisSet;
    private final TowerCollection towers;
    private int savedTowers;

    /**
     * Constructor method of IslandTileSet. Creates the new collections and sets the numOfIsland to 1
     */
    public IslandTileSet(){
        this.students = new StudentDiskCollection();
        this.noEntries = new NoEntryCollection();
        this.towers = new TowerCollection();
        this.numOfIslandsInThisSet = 1;
    }

    /**
     * Constructor method of IslandTileSet. Creates the new collections and sets everything to null
     */
    public IslandTileSet(int ID){
        this.ID= ID;
        students = null;
        noEntries = null;
        towers = null;
    }

    /**
     * Constructor method of IslandTileSet
     * @param id of the island
     * @param students on the island
     * @param noEntryID id of the NoEntry on the island
     * @param numOfNoEntries number of NoEntries on the island
     * @param numOfIslands number of the islands that form this set (can go from 1 to 10)
     * @param savedTowers towers contained on the IslandTileSet
     * @param owner of the island
     */
    public IslandTileSet(int id, StudentDiskCollection students, int noEntryID, int numOfNoEntries, int numOfIslands,  int savedTowers, String owner){
        ID = id;
        this.towers = new TowerCollection();
        this.students = students;
        this.noEntries = new NoEntryCollection(noEntryID);
        for(int i = 0; i<numOfNoEntries;i++){
            this.noEntries.insertElement(new NoEntry());
        }
        this.numOfIslandsInThisSet = numOfIslands;
        this.savedTowers = savedTowers;
        this.owner = owner;
    }

    /**
     * Getter method of IslandTileSet
     *
     * @return ID of the island
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter method of IslandTileSet
     *
     * @param ID the ID of this IslandTileSet
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Method getNumOfIsland it's the getter method for integer variable numOfIsland
     *
     * @return numOfIslands
     */
    public int getNumOfIslandsInThisSet() {
        return numOfIslandsInThisSet;
    }

    /**
     * Method addIsland increments the attribute numOfIslands by the number of new connected islands
     */
    public void addIsland(int numOfIslands){
        this.numOfIslandsInThisSet +=numOfIslands;
    }

    /**
     * Method getStudents is used to get the list of students contained in the StudentDiskCollection students
     * Note that the method calls the StudentDiskCollection method "getAllStudents" that returns a copy of the list
     *
     * @return ArrayList of students
     */
    public ArrayList<Student> getStudents(){
        return students.getAllStudents();
    }

    /**
     * Method only used for testing
     *
     * @return student collection
     */
    public StudentDiskCollection getStudentCollection(){
        return students;
    }

    /**
     * Method insertStudent inserts a student in StudentDiskCollection students
     *
     * @param student to be added
     */
    public void insertStudent(Student student){
        students.insertElement(student);
    }

    /**
     * Method getTowers is used to get the list of towers contained in the TowerCollection towers
     * Note that the method calls the TowerCollection method "getTowers" that returns a copy of the list
     *
     * @return ArrayList of towers
     */
    public ArrayList<Tower> getTowers(){
        return towers.getTowers();
    }

    /**
     * Method getFirstTower returns the first tower of the TowerCollection towers
     *
     * @return a tower
     */
    public Tower getFirstTower(){
        return towers.getFirstTower();
    }

    /**
     * Method insertTower adds a tower to the TowerCollection towers
     *
     * @param tower to be added
     */
    public void insertTower(Tower tower){
        towers.insertElement(tower);
    }

    /**
     * Method removeTower removes a specific tower from the TowerCollection towers
     *
     * @param tower to be removed
     */
    public void removeTower(Tower tower){
        if(towers.contains(tower))
            towers.removeElement(tower);
    }

    /**
     * Method removeAllTowers removes all the towers from the TowerCollection towers
     */
    public void removeAllTowers(){
        int size = towers.size();
        for(int i = 0; i<size; i++){
            try {
                this.removeTower(towers.getFirstTower());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Method getNoEntries is used to get the list of noEntries contained in the NoEntryCollection noEntries
     * Note that the method calls the NoEntryCollection method "getAllNoEntries" that returns a copy of the list
     *
     * @return ArrayList of noEntries
     */
    public ArrayList<NoEntry> getNoEntries() {
        return noEntries.getAllNoEntries();
    }

    /**
     * Method insertNoEntries adds a noEntry card to the noEntryCollection noEntries
     *
     * @param noEntry to be added
     */
    public void insertNoEntries(NoEntry noEntry){
        noEntries.insertElement(noEntry);
    }

    /**
     * Gtter method for the NoEntry's id
     * @return the id of my noEntry
     */
    public int getNoEntryID(){
        return noEntries.getMyId();
    }

    /**
     * Method giveBackNoEntry takes the first noEntry in noEntries and moves it back into the character
     * that holds the noEntries
     *
     */
    public void giveBackNoEntry(){
        NoEntry tmp = noEntries.getFirstNoEntry();
        if(tmp!=null) {
            tmp.getCharacterNoEntryCollection().insertElement(tmp);
            noEntries.removeElement(tmp);
        }
    }

    /**
     * Method getIslandsStudentsOfColor returns the number of students of a specific color placed on the island
     *
     * @param color student's color requested
     * @return int number of students of that specific color
     */
    public int getIslandsStudentsOfColor(DiskColor color){
        return students.getByColor(color);
    }

    /**
     * Method getOwningTeam returns the Team that owns the island, returns null if not owned yet
     *
     * @return Team owner team
     */
    public Team getOwningTeam(){
        if (towers.size()>0)
        return towers.getFirstTower().getTeam();
        else return null;
    }

    /**
     * Method isEqual compares two objects, and checks if they are in the same state
     *
     * @param toCompare the Selectable object to compare
     * @return boolean true if the objects have the same values
     */
    @Override
    public boolean isEqual(Selectable toCompare) {
        if(toCompare instanceof IslandTileSet){
            return ((IslandTileSet) toCompare).getID() == this.ID;
        }
        return false;
    }

    /**
     * fillSavedTowers refills the island with the correct amount of towers according to the current state of the game
     * @param game the instance of my game
     */
    public void fillSavedTowers(Game game){
        Team tmp = null;
        for(Team t: game.getTeams()){
            if(t.contains(game.getSchoolBoardByNick(owner))){
                tmp = t;
                break;
            }
        }

        for(int i = 0; i<savedTowers; i++){
            towers.insertElement(new Tower(tmp));
        }
    }

    /**
     * setNoEntryCharacter places all the noEntries on the Herbalist Character
     * @param characterNoEntryCollection the collection of NoEntries on a character
     */
    public void setNoEntryCharacter(NoEntryCollection characterNoEntryCollection){
        for(NoEntry e : noEntries.getAllNoEntries()){
            e.setCharacterNoEntryCollection(characterNoEntryCollection);
        }
    }
}