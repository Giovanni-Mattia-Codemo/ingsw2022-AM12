package it.polimi.ingsw2022am12.server.controller;

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
    INVALIDUSER(7, "You do not have permission to do that"),
    ALREADYIN(8,"you are already in the game"),
    REQUESTINGNICK(9, "Insert a Nick to enter the game"),
    INSERTMODE(4, "Insert the number of players and mode of the match: number of players between 2 and 4, followed by either true or false to be in the expert mode or not"),
    WAITINGFORPLAYERS(10, "Player connected, waiting for more"),
    MATCHMAKINGCOMPLETE(11, "Your match is starting"),
    GAMEHASNTSTARTED(12, "The game isn't ready yet"),
    INVALIDSELECTION(13, "Invalid selection"),
    ACTIONCOMPLETED(14, "Action completed successfully"),
    ASSIGNEDNICK(15, "Your nick has been set"),
    SELECTMAGE(16, "Select a mage"),
    CHARACTERACTION(17, "To activate a character select it"),
    BARDACTION(18, "To use the bard select:"),
    CHARACTERCARD(19, " it's character card"),
    BARDACTION1(20, " a student from the entrance."),
    BARDACTION2(21, " the student to swap it with."),
    DRAWFROMCLOUD(22, "To draw from a cloud select it."),
    HAGACTION(23, "To use the Hag select:"),
    COLORSELECTION(24, " a color."),
    HERALDACTION(25, "To use the herald select:"),
    HERALDACTION1(26, " an island to conquer."),
    HERBALISTACTION(27, "To use the herbalist select:"),
    HERBALISTACTION1(28, " select an available no entry token."),
    HERBALISTACTION2(29, " select an island to place it on."),
    JESTERACTION(30, "To use the jester select:"),
    JESTERACTION1(31, " a student on the card."),
    JESTERACTION2(32, " the student to swap in your entrance."),
    MERCHANTACTION(33, "To use the merchant select: "),
    MONKACTION(34, "To use the monk select:"),
    MONKACTION1(35, " select the island to place it on."),
    MOVEFROMENTRANCETODININGROOM(36, "To move a student from the entrance to a dining room select: "),
    DININGROOM(37, " select the dining rooms."),
    MOVEFROMENTRANCETOISLAND(38, "To move a student from the entrance to an island select: "),
    MOVEFROMENTRANCETOISLAND2(39, " select the chosen island."),
    MOVEMOTHERNATURE(40, "To move mother nature select: "),
    MOVEMOTHERNATURE1(41, "the island with mother nature."),
    MOVEMOTHERNATURE2(42, " select the island where you want to move her."),
    PLAYASSISTANT(43, "Select an assistant"),
    PRINCESSACTION(44, "To use the princess:"),
    TURNENDED(45, "Your turn has ended"+ "\n"),
    DISCONNECTION(46, "Game is closing because of a player disconnection"),
    SERVERUNREACHABLE(47, "Connection with server lost");



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
