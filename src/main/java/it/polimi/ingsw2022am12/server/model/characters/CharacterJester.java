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
        students = new StudentDiskCollection();
    }

    @Override
    public void InitCharacter(Game game) {
        for (int i = 0; i < jesterStudents; i++) {
            students.insertElement(game.drawStudentFromBag());
        }
    }


    @Override
    public PossibleAction getPossibleAction() {
        if(movesDone<3){
            return new JesterAction();
        }
        return null;
    }


    public boolean containsStudent(Student student){
        return students.contains(student);
    }
    public StudentDiskCollection getStudents(){
        return students;
    }

    @Override
    public void setWasUsed(boolean isUsed) {
        if(isUsed) {
            if (movesDone < 3) {
                movesDone++;
            } else super.setWasUsed(true);
        }else{
            movesDone = 0;
            super.setWasUsed(false);
        }
    }


}
