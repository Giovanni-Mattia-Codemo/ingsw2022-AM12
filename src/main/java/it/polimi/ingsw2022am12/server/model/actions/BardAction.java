package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
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
    public ArrayList<ControlMessages> getUserSelectionsMessage() {
        ArrayList<ControlMessages> messages = new ArrayList<>();
        messages.add(ControlMessages.BARDACTION);
        if(!score.containsKey(0)){
            messages.add(ControlMessages.CHARACTERCARD);
        }else if(!score.containsKey(1)){
            messages.add(ControlMessages.BARDACTION1);
        }else if(!score.containsKey(2)){
            messages.add(ControlMessages.BARDACTION2);
        }
        return messages;
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