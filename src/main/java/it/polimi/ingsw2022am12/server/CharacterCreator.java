package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.exceptions.NotValidCharacter;

public abstract class CharacterCreator {

    public static Character createCharacter(int numOfCharacter) throws Exception {
        return switch (numOfCharacter) {
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
            case 12 -> new CharacterMonk();
            default -> throw new NotValidCharacter();
        };
    }
}
