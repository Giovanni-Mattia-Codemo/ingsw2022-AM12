package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.exceptions.NotPresent;

import java.util.ArrayList;

/**
 * Class IslandTileSet defines a single island or the aggregation of more islands
 */
public class IslandTileSet implements Selectable {

    private final StudentDiskCollection students;
    private final NoEntryCollection noEntries;
    private int numOfIslandsInThisSet;
    private final TowerCollection towers;

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
     * Method insertStudent inserts a student in StudentDiskCollection students
     *
     * @param student to be added
     */
    public void insertStudent(Student student){
        students.insertElement(student);
    }

    /**
     * Method removeStudent takes a student and, if it's contained in the list students, it removes it
     *
     * @param student to be removed
     * @throws NotPresent if the student isn't contained in the collection
     */
    public void removeStudent(Student student) throws NotPresent{
        if(students.contains(student))
            students.removeElement(student);
        else throw new NotPresent();
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
     * @throws NotPresent if there's no towers in the collection
     */
    public void removeTower(Tower tower) throws NotPresent {
        if(towers.contains(tower))
            towers.removeElement(tower);
        else throw new NotPresent();
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
     * Method removeNoEntries removes a noEntry card from the NoEntryCollection noEntries
     *
     * @param noEntry to be removed
     * @throws NotPresent if the collection is empty
     */
    public void removeNoEntry(NoEntry noEntry) throws NotPresent{
        if(noEntries.contains(noEntry))
            noEntries.removeElement(noEntry);
        else throw new NotPresent();
    }

    /**
     * Method giveBackNoEntry takes the first noEntry in noEntries and moves it back into the character
     * that holds the noEntries
     *
     * @throws NotPresent thrown when no towers are left on this schoolBoard
     */
    public void giveBackNoEntry() throws NotPresent{
        NoEntry tmp = noEntries.getFirstNoEntry();
        if(tmp!=null){
            tmp.getCharacterNoEntryCollection().insertElement(tmp);
            noEntries.removeElement(tmp);
        }else throw new NotPresent();
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
}