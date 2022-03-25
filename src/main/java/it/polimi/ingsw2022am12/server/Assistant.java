package it.polimi.ingsw2022am12.server;

/**
 * Class that defines the properties of an Assistant card, which is characterized by TWO unique numbers (different for every Assistant)
 */

public class Assistant implements Selectable{

    private final int turnPower;   //FINAL INT; these values are not meant to change during the game!
    private final int motherNatureRange;  //FINAL INT; these values are not meant to change during the game!

    /**
     * Constructor method of the class Assistant. Sets all parameters of the class.
     *
     * @param turnPower; integer number, the lowest value that has been played by
     *                            one of the players, determines who will be the first of the next turn
     * @param motherNatureRange; integer number, determines the MAXIMUM amount of "jumps" MotherNature can make
     *                                    from tile to tile
     */

    public Assistant(int turnPower, int motherNatureRange) {
        this.turnPower = turnPower;
        this.motherNatureRange = motherNatureRange;
    }

    /**
     * Method (NO PARAMETERS) that returns the number of moves MotherNature can make during the player's turn.
     *           (NOTE! The player can choose only ONE value in the range determined by motherNatureRange)
     *
     * @return int motherNatureRange
     */

    public int getMotherNatureRange() {
        return motherNatureRange;
    }

    /**
     * Method (NO PARAMETERS) that returns the power of the Assistant card selected.
     *             (The "power" of the Assistant is an Integer number, which determines the order of players in the next turn,
     *                  starting from the lowest value, to the highest)
     *
     * @return int turnPower
     */
    public int getTurnPower() {
        return turnPower;
    }

    /**
     * Method printAssistantPower prints on the screen the value of assistant's turnPower
     */
    public void printAssistantPower(){
        System.out.println(getTurnPower());
    }
}