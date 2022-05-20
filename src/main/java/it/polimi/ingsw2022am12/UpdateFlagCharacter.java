package it.polimi.ingsw2022am12;

/**
 * UpdateFlagCharacter is the class that handles the updating and getting of flags that signal changes in the Characters
 */
public class UpdateFlagCharacter extends UpdateFlag{

    private String nick;

    /**
     * constructor method of the UpdateFlagCharacter class, also uses the constructor of the superclass
     * @param nick the nick I want to use
     */
    public UpdateFlagCharacter(String nick) {
        super(Flag.CHARACTERS);
        this.nick = nick;
    }

    /**
     * Getter method for nick
     * @return String nick
     */
    public String getNick() {
        return nick;
    }
}
