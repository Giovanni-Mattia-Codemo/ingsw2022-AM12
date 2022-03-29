package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.JesterAction;

public class CharacterJester extends CharacterCard {
    //at start 6 students here, during up to 3 students swapped with entrance

    private static final int jesterStudents = 6;
    private int movesDone;
    private final StudentDiskCollection students;

    public CharacterJester(){
        super("Jester", 1);
    }

}
