package it.polimi.ingsw2022am12.server.model;

/**
 * Class DiskColor contains the enumeration of the possible colors of the Students and Professors
 */
public enum DiskColor {
    RED(0),
    YELLOW(1),
    BLUE(2),
    PINK(3),
    GREEN(4);

    private final int value;

    /**
     * Constructor method of DiskColor. Initiates the values of the enum
     *
     * @param newValue value of the specific color
     */
    DiskColor(final int newValue){
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

    public String getColor(int i){
        return switch(i){
            case 0 -> "RED";
            case 1 -> "YELLOW";
            case 2 -> "BLUE";
            case 3 -> "PINK";
            case 4 -> "GREEN";
            default -> null;
        };
    }
}
