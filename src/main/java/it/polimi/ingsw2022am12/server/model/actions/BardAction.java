package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterBard;

import java.util.ArrayList;

/**
 * Class that defines the process of activation of a Bard Card
 */
public class BardAction extends PossibleAction {

    /**
     * Constructor method of the BardAction class
     */
    public BardAction(){
        super(3);
    }

    /**
     * setSelectables method sets the selectable map, associating a key value to a certain object
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {

        ArrayList<Selectable> tmp = new ArrayList<>(game.getCurrentSchoolBoard().getEntrance().getStudentsAsSelectables());
        boolean b = tmp.removeIf(a -> game.getCurrentSchoolBoard().isDiningRoomFull(((Student) a).getColor()));
        ArrayList<Selectable> character = new ArrayList<>();
        character.add(game.getActiveCharacterCard());

        selectables.put(0, character);
        selectables.put(1, tmp);
        selectables.put(2, game.getCurrentSchoolBoard().getDiningRoom().getStudentsAsSelectables());
    }

    /**
     * getUserSelectionsMessage returns a certain string based on the users selections
     * @return string message
     */
    @Override
    public String getUserSelectionsMessage() {
        String msg = "";
        msg = msg.concat("To use the bard select:");
        if(!score.containsKey(0)){
            msg = msg.concat(" it's character card");
        }else if(!score.containsKey(1)){
            msg = msg.concat(" a student from the entrance.");
        }else if(!score.containsKey(2)){
            msg = msg.concat(" the student to swap it with.");
        }
        return msg;
    }



    /**
     * Method useAction uses the power of the Bard, and sets the Bard to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){
        Student student1 = (Student)score.get(1);
        Student student2 = (Student)score.get(2);

        game.swapStudents(student1.getColor(), student2.getColor());

        game.getActiveCharacterCard().setWasUsed(true);
    }

}