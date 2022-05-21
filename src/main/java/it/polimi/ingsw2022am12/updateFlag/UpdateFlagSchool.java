package it.polimi.ingsw2022am12.updateFlag;

/**
 * UpdateFlagSchool is the class that handles the updating and getting of flags that signal changes in the SchoolBoards
 */
public class UpdateFlagSchool extends UpdateFlag {

    private final String nick;

    /**
     * constructor method of the UpdateFlagSchool class, also uses the constructor of the superclass
     * @param nick the nick I want to use
     */
    public UpdateFlagSchool( String nick) {
        super(Flag.SCHOOL);
        this.nick=nick;
    }

    /**
     * Getter method for nick
     * @return String nick
     */
    public String getNick() {
        return nick;
    }
}
