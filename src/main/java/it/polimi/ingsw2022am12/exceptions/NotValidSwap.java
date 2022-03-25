package it.polimi.ingsw2022am12.exceptions;

/**
 * Class NotValidSwap is thrown when a user tries to do an illegal move between entrance and dining room.
 *
 * @see Exception
 */
public class NotValidSwap extends Exception{

    /**
     * Method getMessage returns the message of this NotValidSwap exception
     *
     * @return the message of the NotValidSwap exception object
     */
    @Override
    public String getMessage(){
        return "Not a valid swap";
    }
}
