package it.polimi.ingsw2022am12.server.model;

/**
 * Factory method of Assistant.
 * It returns a new Assistant card.
 * It is used to create a deck of Assistant cards.
 */

public abstract class AssistantCreator {

    /**
     * Method returns the new Assistant card chosen. Each Assistant can have ONE value of turnPower
     *                                                       (Integer from 1 to 10)
     *@param turnPower turnPower
     *@return Assistant created with the values of the switch case
     */
    public static Assistant createAssistant(int turnPower){
        return switch (turnPower) {
            case 1 -> new Assistant(1, 1);
            case 2 -> new Assistant(2, 1);
            case 3 -> new Assistant(3, 2);
            case 4 -> new Assistant(4, 2);
            case 5 -> new Assistant(5, 3);
            case 6 -> new Assistant(6, 3);
            case 7 -> new Assistant(7, 4);
            case 8 -> new Assistant(8, 4);
            case 9 -> new Assistant(9, 5);
            case 10 -> new Assistant(10, 5);
            default -> null;
        };
    }
}
