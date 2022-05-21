package it.polimi.ingsw2022am12.server.model.characters;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import it.polimi.ingsw2022am12.server.model.actions.PrincessAction;

/**
 * Class Character defines the main properties of a Princess card
 */
public class CharacterPrincess extends CharacterCard {

    //place four students here, pick student and place it in diningRoom

    private final StudentDiskCollection students;
    private static final int princessStudents= 4;

    /**
     * Constructor method of the CharacterPrincess class
     *
     */
    public CharacterPrincess(){
        super(CharacterName.CHARACTER_PRINCESS, 2);
        students = new StudentDiskCollection();
    }

    /**
     * Getter method of the CharacterPrincess class
     *
     * @return StudentDiskCollection the students placed of this card
     */
    public StudentDiskCollection getStudents() {
        return students;
    }

    /**
     * Method initCharacter places the students on this card
     *
     * @param game the current instance of the game
     */
    @Override
    public void initCharacter(Game game) {
        for (int i = 0; i < princessStudents; i++) {
            students.insertElement(game.drawStudentFromBag());
        }
    }

    /**
     * Method PossibleAction allows me to know the Princess power
     *
     * @return PossibleAction the Princess action I can use
     */
    @Override
    public PossibleAction getPossibleAction() {
        return new PrincessAction();
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
            for(int i = students.amount(); i<princessStudents; i++){
                students.insertElement(game.drawStudentFromBag());
                super.setWasUsed(false);
            }
        }
    }
}