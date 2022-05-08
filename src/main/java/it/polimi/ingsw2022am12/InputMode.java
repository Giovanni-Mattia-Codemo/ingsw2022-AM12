package it.polimi.ingsw2022am12;

/**
 * Input mode is a class that defines three inputs to insert during the connection to the server
 * (Tag for identification, number of players and if the Expert Mode has been selected)
 */
public class InputMode {
    private final String tag = "InputMode";
    private int number = 2;
    private boolean mode = true;

    public InputMode(int n, boolean b){
        number =n;
        mode = b;
    }

    /**
     * Constructor method of InputMode class
     */
    public InputMode(){

    }

    /**
     * Getter method, returns number
     *
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter method, returns mode
     *
     * @return mode
     */
    public boolean getMode(){
        return mode;
    }
}
