package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.exceptions.NotValidSwap;
import it.polimi.ingsw2022am12.server.model.*;
import java.util.ArrayList;

/**
 * Class that defines the process of activation of a Bard Card
 */
public class BardAction extends PossibleAction {

    private DiskColor student1;
    private DiskColor student2;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
    int inDiningRoom=0;
    int inEntrance=0;
    int entranceIndex= 0;
    int roomIndex= 0;
        for (int i = 0; i < input.size(); i++){
            Selectable tmp = input.get(i);
            if(tmp instanceof Student ){
                if(((Student)tmp).getPositionID()==game.getCurrentSchoolBoard().getEntrance().getID()){
                    inEntrance ++;
                    entranceIndex = i;
                }else if(((Student)tmp).getPositionID()==game.getCurrentSchoolBoard().getDiningRoom().getID()) {
                    inDiningRoom++;
                    roomIndex = i;
                }
            }else return ActionStep.NOTOK;
        }

        if(input.size()==1){
            if(inEntrance==1||inDiningRoom==1){
                return ActionStep.HALFOK;
            }
        }else if (input.size()==2){
            if(inDiningRoom==1&&inEntrance==1){
                student1=((Student) input.get(entranceIndex)).getColor();
                student2=((Student) input.get(roomIndex)).getColor();
                return ActionStep.OK;
            }
        }
        return ActionStep.NOTOK;

    }

    /**
     * Method useAction uses the power of the Bard, and sets the Bard to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game){
        try {
            game.swapStudents(student1, student2);
        } catch (NotValidSwap e) {
            e.printStackTrace();
        }
        game.getActiveCharacterCard().setWasUsed(true);
    }

}