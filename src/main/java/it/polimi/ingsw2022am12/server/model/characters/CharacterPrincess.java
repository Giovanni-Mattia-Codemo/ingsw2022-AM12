package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import it.polimi.ingsw2022am12.server.model.actions.MonkAction;
import it.polimi.ingsw2022am12.server.model.actions.PrincessAction;

public class CharacterPrincess extends CharacterCard {
    //4 students here, pick student and place it in room
    private StudentDiskCollection students;
    private static final int princessStudents= 4;

    public CharacterPrincess(){
        super("Princess", 2);
        students=new StudentDiskCollection();
    }





    public StudentDiskCollection getStudents() {
        return students;
    }

    @Override
    public void InitCharacter(Game game) {
        for (int i = 0; i < princessStudents; i++) {
            students.insertElement(game.drawStudentFromBag());
        }
    }

    @Override
    public PossibleAction getPossibleAction() {

        return new PrincessAction();
    }



    public void setWasUsed(boolean isUsed, Game game) {
        if(isUsed){
            super.setWasUsed(true);
        }else{
            for(int i = students.amount(); i<princessStudents; i++){
                students.insertElement(game.drawStudentFromBag());
                super.setWasUsed(false);
            }

        }

    }
}
