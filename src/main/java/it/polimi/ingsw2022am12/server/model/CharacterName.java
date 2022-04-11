package it.polimi.ingsw2022am12.server.model;

/**
 * Class CharacterName contains the enumeration of the possible names for the characters
 */
public enum CharacterName {
    CHARACTER_MONK(0),
    CHARACTER_BEGGAR(1),
    CHARACTER_PRINCESS(2),
    CHARACTER_HERBALIST(3),
    CHARACTER_BARD(4),
    CHARACTER_HAG(5),
    CHARACTER_HERALD(6),
    CHARACTER_HOST(7),
    CHARACTER_CENTAUR(8),
    CHARACTER_KNIGHT(9),
    CHARACTER_JESTER(10),
    CHARACTER_MERCHANT(11);

    private final int value;

    /**
     * Constructor method of CharacterName. Initiates the values of the enum
     *
     * @param newValue value of the specific name
     */
    CharacterName(final int newValue){
        this.value = newValue;
    }

    /**
     * Method getValue returns the correspondent value of a CharacterName
     *
     * @return value of name
     */
    public int getValue(){
        return value;
    }
}
