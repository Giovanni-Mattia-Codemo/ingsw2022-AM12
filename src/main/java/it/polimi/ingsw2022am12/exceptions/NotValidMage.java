package it.polimi.ingsw2022am12.exceptions;

/**
 * Class NotValidMage is thrown when a user tries to pick an already picked mage.
 *
 * @see Exception
 */
public class NotValidMage extends Exception{

    /**
     * Method getMessage returns the message of this NotValidMage exception
     *
     * @return the message of the NotValidMage exception object
     */
    @Override
    public String getMessage(){
        return "The selected mage was already picked";
    }
}
