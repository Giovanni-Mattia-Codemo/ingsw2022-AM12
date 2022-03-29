package it.polimi.ingsw2022am12.server.model;

import it.polimi.ingsw2022am12.exceptions.NotValidCharacter;
import it.polimi.ingsw2022am12.server.model.characters.*;

/**
 * Factory method of Character.
 * It returns a new Character card.
 * It is used to fill a CharacterCollection.
 */
public abstract class CharacterCreator {

    /**
     * Method returns the new CharacterCard card chosen.
     *
     *@param numOfCharacter random int given in the setup phase
     *@throws NotValidCharacter Invalid character number
     *
     */
    public static CharacterCard createCharacter(int numOfCharacter) throws Exception {
        return switch (numOfCharacter) {
            case 0 -> new CharacterMonk();
            case 1 -> new CharacterBeggar();
            case 2 -> new CharacterPrincess();
            case 3 -> new CharacterHerbalist();
            case 4 -> new CharacterBard();
            case 5 -> new CharacterHag();
            case 6 -> new CharacterHerald();
            case 7 -> new CharacterHost();
            case 8 -> new CharacterCentaur();
            case 9 -> new CharacterKnight();
            case 10 -> new CharacterJester();
            case 11 -> new CharacterMerchant();
            default -> throw new NotValidCharacter();
        };
    }
}
