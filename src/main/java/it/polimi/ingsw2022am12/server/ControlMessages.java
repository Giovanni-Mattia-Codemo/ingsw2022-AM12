package it.polimi.ingsw2022am12.server;

/**
 * ControlMessages is an enum that contains the type of messages a client can receive from the server
 */
public enum ControlMessages {
    RETRY(0, "Try another"),
    ACCEPTED(1, "Input accepted"),
    GAMEISFULL(2, "Game is full"),
    GAMEISBEINGCREATED(3, "Game format is being decided, wait"),
    GAMEWASSET(5, "The game format has already been set"),
    INVALIDVALUES(6, "Invalid input, try again"),
    INVALIDUSER(7, "You do not have permission to select that"),
    ALREADYIN(8,"you are already in the game"),
    INSERTMODE(4, "Insert the number of players and mode of the match: number of players between 2 and 4, followed by either true or false to be in the expert mode or not");

    private final int value;
    private final String message;

    /**
     * Constructor method of ControlMessages. Initiates the values of the enum
     *
     * @param newValue value of the specific message
     * @param s message for the client
     */
    ControlMessages(final int newValue, String s){
        value = newValue;
        message = s;
    }

    /**
     * Method getValue returns the correspondent value of a message
     *
     * @return int value of message
     */
    public int getValue(){
        return value;
    }

    /**
     * Method getMessage returns a certain message
     *
     * @return String message
     */
    public String getMessage() {
        return message;
    }
}