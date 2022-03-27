package it.polimi.ingsw2022am12.server.model.actions;

/**
 * Class ActionStep contains the enumeration of the possible ActionStep
 */
public enum ActionStep {
    NOTOK(0),
    HALFOK(1),
    OK(2);

    private final int value;

    /**
     * Constructor method of ActionStep. Initiates the values of the enum
     *
     * @param newValue value of the specific action step
     */
    ActionStep(final int newValue){
        value = newValue;
    }

    /**
     * Method getValue returns the correspondent value of an action step
     *
     * @return value of action step
     */
    public int getValue(){
        return value;
    }
}
