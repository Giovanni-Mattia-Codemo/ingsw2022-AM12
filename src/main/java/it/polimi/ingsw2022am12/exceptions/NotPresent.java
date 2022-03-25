package it.polimi.ingsw2022am12.exceptions;

/**
 * Class NotPresent is thrown when an Object isn't contained in a specific collection
 *
 * @see Exception
 */
public class NotPresent extends Exception{

    /**
     * Method getMessage returns the message of this NotPresent exception
     *
     * @return the message of the NotPresent exception object
     */
    @Override
    public String getMessage(){
        return "Element not present in this collection";
    }
}
