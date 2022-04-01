package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import it.polimi.ingsw2022am12.server.model.actions.MonkAction;

public class CharacterMonk extends CharacterCard {
    // at start 4 students on card, during turn place a student on island, filled at endturn
    private StudentDiskCollection students;
    private static final int monkStudents= 4;
    public CharacterMonk(){
        super("Monk", 1);
        students = new StudentDiskCollection();
    }

    public StudentDiskCollection getStudents() {
        return students;
    }

    @Override
    public void InitCharacter(Game game) {
        for (int i = 0; i < monkStudents; i++) {
            students.insertElement(game.drawStudentFromBag());
        }
    }

    @Override
    public PossibleAction getPossibleAction() {
        return new MonkAction();
    }



    public void setWasUsed(boolean isUsed, Game game) {
        if(isUsed){
            super.setWasUsed(true);
        }else{
            for(int i = students.amount(); i<monkStudents; i++){
                students.insertElement(game.drawStudentFromBag());
            }
            super.setWasUsed(isUsed);
        }

    }
}
