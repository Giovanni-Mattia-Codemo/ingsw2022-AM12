package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import it.polimi.ingsw2022am12.server.model.actions.MonkAction;

/**
 * Class Character defines the main properties of a Monk card
 */
public class CharacterMonk extends CharacterCard {

    // four students on card, during my turn I can place one of the students on island, filled at the end of turn

    private StudentDiskCollection students;
    private static final int monkStudents= 4;

    /**
     * Constructor method of the CharacterMonk class
     *
     */
    public CharacterMonk(){
        super(CharacterName.CHARACTER_MONK, 1);
        students = new StudentDiskCollection();
    }

    /**
     * Getter method of the CharacterHag class
     *
     * @return StudentDiskCollection the students placed on this card
     */
    public StudentDiskCollection getStudents() {
        return students;
    }

    /**
     * Method init Character initializes the four students placed on this card
     *
     * @param game the current instance of the game
     */
    @Override
    public void initCharacter(Game game) {
        for (int i = 0; i < monkStudents; i++) {
            students.insertElement(game.drawStudentFromBag());
        }
    }

    /**
     * Method PossibleAction allows me to know the Monk power
     *
     * @return PossibleAction the Monk action I can use
     */
    @Override
    public PossibleAction getPossibleAction() {
        return new MonkAction();
    }


    /**
     * Method setWasUsed tells if the character was already used through a boolean flag
     *
     * @param game the current instance of the game
     * @param isUsed is true if the character has already been used, else false
     */
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
