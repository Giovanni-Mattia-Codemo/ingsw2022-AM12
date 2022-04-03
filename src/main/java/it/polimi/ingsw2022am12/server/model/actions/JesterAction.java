package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterJester;

import java.util.ArrayList;

public class JesterAction extends PossibleAction {

    private DiskColor s0;
    private DiskColor s1;


    public JesterAction(){
        super(2);
    }


    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        int charIndex= 0;
        int entranceIndex = 0;
        boolean onChar = false;
        boolean inEntrance= false;
        for (Selectable s: input) {
            if(s instanceof Student){
                if(((CharacterJester) game.getActiveCharacterCard()).getStudents().getID()==((Student) s).getPositionID()){
                    if(!onChar){
                        onChar=true;
                        charIndex=input.indexOf(s);
                    }else return ActionStep.NOTOK;
                }else if(game.getCurrentSchoolBoard().getEntrance().getID()==((Student) s).getPositionID()){
                    if(!inEntrance){
                        inEntrance=true;
                        entranceIndex=input.indexOf(s);
                    }else return ActionStep.NOTOK;
                }else return ActionStep.NOTOK;

            }else return ActionStep.NOTOK;
        }
        if(input.size()==1){
            return ActionStep.HALFOK;
        }else if(input.size()==2&& onChar && inEntrance){
            s0=((Student) input.get(charIndex)).getColor();
            s1=((Student) input.get(entranceIndex)).getColor();


            return ActionStep.OK;
        }else return ActionStep.NOTOK;
    }

    @Override
    public void useAction(Game game) {

        game.jesterSwap(s0, s1);
        game.getActiveCharacterCard().setWasUsed(true);

    }
}