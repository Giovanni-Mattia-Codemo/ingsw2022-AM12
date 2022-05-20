package it.polimi.ingsw2022am12;

/**
 *UpdateFlag is the class that handles the updating and getting of flags that signal changes in the game
 */
public class UpdateFlag {
    private Flag flag;

    /**
     * constructor method of the UpdateFlag class
     * @param flag the flag I want to use
     */
    public UpdateFlag(Flag flag){
        this.flag = flag;
    }

    /**
     * Getter method for flag
     * @return flag the current flag
     */
    public Flag getFlag() {
        return flag;
    }
}
