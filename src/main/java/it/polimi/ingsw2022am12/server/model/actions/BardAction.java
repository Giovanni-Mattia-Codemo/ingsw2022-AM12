package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import java.util.ArrayList;

public class BardAction extends PossibleAction {
    private Student student1;
    private Student student2;

    public BardAction() {
        super(2);
    }

    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
    int inDiningRoom=0;
    int inEntrance=0;
        for (int i = 0; i < input.size(); i++){
            Selectable tmp = input.get(i);
            if(tmp.getSelectableType()=="Student"){
                if(((Student)tmp).getPosition()==game.getCurrentSchoolBoard().getEntrance()){
                    inEntrance++;
                }else if(((Student)tmp).getPosition()==game.getCurrentSchoolBoard().getDiningRoom()) {
                    inDiningRoom++;
                }
            }
        }

        if(input.size()==1){
            if(inEntrance==1||inDiningRoom==1){
                return ActionStep.HALFOK;
            }
        }else if (input.size()==2){
            if(inDiningRoom==1&&inEntrance==1){
                student1=(Student) input.get(0);
                student2=(Student) input.get(1);
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
