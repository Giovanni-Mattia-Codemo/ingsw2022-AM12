package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import java.util.ArrayList;

public class BardAction extends PossibleAction {
    private DiskColor student1;
    private DiskColor student2;

    public BardAction() {
        super(2);
    }

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

    @Override
    public void useAction(Game game){
        game.swapStudents(student1, student2);
        game.getActiveCharacterCard().setWasUsed(true);
    }

}