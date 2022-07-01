package it.polimi.ingsw2022am12.client.GUI;

/**
 * Enum that contains the color of the towers
 */
public enum TowerColor {
    WHITE_TOWER(0),
    BLACK_TOWER(1),
    GREY_TOWER(2);

    /**
     * Constructor method of TowerColor class
     * @param i towerNum
     */
    TowerColor(final int i) {
    }

    public String getName(int i){
        switch (i){
            case 0 -> {
                return "WHITE_TOWER";
            }
            case 1 -> {
                return "BLACK_TOWER";
            }
            case 2 ->{
                return "GREY_TOWER";
            }
            default -> {
                return null;
            }
        }
    }
}
