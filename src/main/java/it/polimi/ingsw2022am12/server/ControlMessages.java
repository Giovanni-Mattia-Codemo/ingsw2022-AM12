package it.polimi.ingsw2022am12.server;

public enum ControlMessages {
    RETRY(0),
    ACCEPTED(1),
    GAMEISFULL(2),
    GAMEISBEINGCREATED(3),
    GAMEWASSET(5),
    INVALIDVALUES(6),
    INVALIDUSER(7),
    INSERTMODE(4);

    private final int value;

    /**
     * Constructor method of DiskColor. Initiates the values of the enum
     *
     * @param newValue value of the specific color
     */
    ControlMessages(final int newValue){
        value = newValue;
    }

    /**
     * Method getValue returns the correspondent value of a color
     *
     * @return value of color
     */
    public int getValue(){
        return value;
    }
}
