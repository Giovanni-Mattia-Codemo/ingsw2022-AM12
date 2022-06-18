package it.polimi.ingsw2022am12.server.model;

/**
 * Class Tower defines the object tower
 */
public class Tower extends PlaceableObject {

    private final Team team;  //The team that owns a certain tower

    /**
     * Constructor method of the class Tower. Assigns a team to the Tower
     *
     * @param team assigned
     */
    public Tower(Team team){
        this.team = team;
    }

    /**
     * Method getTeam returns the owning team of the tower
     *
     * @return team owner
     */
    public Team getTeam(){
        return team;
    }

}


