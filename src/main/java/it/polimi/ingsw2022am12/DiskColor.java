package it.polimi.ingsw2022am12;

/**
 * Class DiskColor contains the enumeration of the possible colors of the Students and Professors
 */
public enum DiskColor {
    GREEN(0),
    RED(1),
    YELLOW(2),
    PINK(3),
    BLUE(4);



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
            case 1 -> "RED";
            case 2 -> "YELLOW";
            case 4 -> "BLUE";
            case 3 -> "PINK";
            case 0 -> "GREEN";
            default -> null;
        };
    }
}
