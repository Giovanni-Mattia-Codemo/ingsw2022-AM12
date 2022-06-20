package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.actions.JesterAction;

/**
 * Class Character defines the main properties of a Jester card
 */
public class CharacterJester extends CharacterCard {

    //put six students here in the beginning, during your turn you can swap up to three students with entrance

    private static final int jesterStudents = 6;
    private int movesDone;
    private final StudentDiskCollection students;

    /**
     * Default constructor method of the CharacterJester class
     *
     */
    public CharacterJester(){
        super(CharacterName.CHARACTER_JESTER, 1);
        students = new StudentDiskCollection();
    }

    /**
     * Constructor method of the CharacterJester class
     * @param students the students to place on the card
     * @param movesDone number of moves done
     */
    public CharacterJester(StudentDiskCollection students, int movesDone){
        super(CharacterName.CHARACTER_JESTER, 1);
        this.students = students;
        this.movesDone = movesDone;
    }

    /**
     * Method initCharacter places the six students on this card
     *
     * @param game the current instance of the game
     */
    @Override
    public void initCharacter(Game game) {
        for (int i = 0; i < jesterStudents; i++) {
            students.insertElement(game.drawStudentFromBag());
        }
    }

    /**
     * Method PossibleAction allows me to know the Jester power
     *
     * @return PossibleAction the Jester action I can use
     */
    @Override
    public PossibleAction getPossibleAction() {
        if(movesDone<3){
            return new JesterAction();
        }
        return null;
    }

    /**
     * Getter method of the CharacterJesterClass
     *
     * @return StudentDiskCollection the students placed on the card
     */
    public StudentDiskCollection getStudents(){
        return students;
    }

    /**
     * Method setWasUsed tells if the character was already used through a boolean flag
     *
     * @param isUsed true if this card was used, else false
     */
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

    /**
     * Getter method for movesDone
     *
     * @return number of moves done
     */
    public int getMovesDone() {
        return movesDone;
    }
}
