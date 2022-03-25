package it.polimi.ingsw2022am12.exceptions;

/**
 * Class NotValidAssistant is thrown if AssistantCreator was given a wrong assistant number
 *
 * @see Exception
 */
public class NotValidAssistant extends Exception{

    /**
     * Method getMessage returns the message of this NotValidAssistant exception
     *
     * @return the message of the NotValidAssistant exception object
     */
    @Override
    public String getMessage(){
        return "Invalid assistant number";
    }
}
