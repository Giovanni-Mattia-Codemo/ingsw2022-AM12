package it.polimi.ingsw2022am12.exceptions;

/**
 * Class NotValidCharacter is thrown if CharacterCreator was given a wrong character number
 *
 * @see Exception
 */
public class NotValidCharacter extends Exception{

    /**
     * Method getMessage returns the message of this NotValidCharacter exception
     *
     * @return the message of the NotValidCharacter exception object
     */
    @Override
    public String getMessage(){
        return "Invalid character number";
    }
}