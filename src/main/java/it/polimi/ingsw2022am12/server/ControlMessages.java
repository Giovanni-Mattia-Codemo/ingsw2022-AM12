package it.polimi.ingsw2022am12.server;

public enum ControlMessages {
    RETRY(0, "Try another"),
    ACCEPTED(1, "You were accepted"),
    GAMEISFULL(2, "Game is full"),
    GAMEISBEINGCREATED(3, "Game format is being decided, wait"),
    GAMEWASSET(5, "The game format has already been set"),
    INVALIDVALUES(6, "Invalid input, try again"),
    INVALIDUSER(7, "You do not have permission to select that"),
    INSERTMODE(4, "Insert the number of players and mode of the match: number of players between 2 and 4, followed by either true or false to be in the expert mode or not");

    private final int value;
    private final String message;

    /**
     * Constructor method of DiskColor. Initiates the values of the enum
     *
     * @param newValue value of the specific color
     * @param s message for the client
     */
    ControlMessages(final int newValue, String s){
        value = newValue;
        message = s;
    }

    /**
     * Method getValue returns the correspondent value of a color
     *
     * @return value of color
     */
    public int getValue(){
        return value;
    }

    public String getMessage() {
        return message;
    }
}
