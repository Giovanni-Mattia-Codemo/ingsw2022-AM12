package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.exceptions.NotValidAssistant;
import it.polimi.ingsw2022am12.server.model.Assistant;

/**
 * Factory method of Assistant.
 * It returns a new Assistant card.
 * It is used to create a deck of Assistant cards.
 */

public abstract class AssistantCreator {

    /**
     * Method returns the new Assistant card chosen. Each Assistant can have ONE value of turnPower
     *                                                       (Integer from 1 to 10)
     *
     *
     * @param turnPower turnPower
     * @throws NotValidAssistant Invalid assistant number
     *                        (If I use as an input turnPower < 1 || turnPower > 10)
     */
    public static Assistant createAssistant(int turnPower) throws NotValidAssistant {
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
            default -> throw new NotValidAssistant();
        };
    }
}
