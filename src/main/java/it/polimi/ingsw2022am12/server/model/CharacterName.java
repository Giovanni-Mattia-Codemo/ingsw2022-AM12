package it.polimi.ingsw2022am12.server.model;

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

    CharacterName(final int newValue){
        this.value = newValue;
    }

    public int getValue(){
        return value;
    }
}
