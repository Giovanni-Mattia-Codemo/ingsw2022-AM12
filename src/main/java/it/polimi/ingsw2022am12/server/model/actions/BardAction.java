package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import java.util.ArrayList;

/**
 * Class that defines the process of activation of a Bard Card
 */
public class BardAction extends PossibleAction {

    public BardAction(){
        super(2);
    }

    /**
     * setSelectables method sets the selectable objects
     *
     * @param game instance of my game
     */
    @Override
    public void setSelectables(Game game) {

        ArrayList<Selectable> tmp = new ArrayList<>(game.getCurrentSchoolBoard().getEntrance().getStudentsAsSelectables());
        boolean b = tmp.removeIf(a -> game.getCurrentSchoolBoard().isDiningRoomFull(((Student) a).getColor()));

        selectables.put(0, tmp);
        selectables.put(1, game.getCurrentSchoolBoard().getDiningRoom().getStudentsAsSelectables());
    }

    /**
     * Method useAction uses the power of the Bard, and sets the Bard to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){
        Student student1 = (Student)score.get(0);
        Student student2 = (Student)score.get(1);

        game.swapStudents(student1.getColor(), student2.getColor());

        game.getActiveCharacterCard().setWasUsed(true);
    }

}