package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.model.characters.CharacterJester;

import java.util.ArrayList;

public class JesterAction extends PossibleAction {

    private Student s0;
    private Student s1;

    public JesterAction(){
        super(2);
    }


    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        boolean onChar = false;
        boolean inEntrance= false;
        for (Selectable s: input) {
            if(s instanceof Student){
                if(((CharacterJester) game.getActiveCharacterCard()).containsStudent((Student) s)){
                    if(!onChar){
                        onChar=true;
                    }else return ActionStep.NOTOK;
                }else if(game.getStudentsInEntranceOfCurrentTurn().contains(s)){
                    if(!inEntrance){
                        inEntrance=true;
                    }else return ActionStep.NOTOK;
                }

            }else return ActionStep.NOTOK;
        }
        if(input.size()==1&&(onChar || inEntrance)){
            return ActionStep.HALFOK;
        }else if(input.size()==2&& onChar && inEntrance){
            s0=(Student) input.get(0);
            s1=(Student) input.get(1);

            return ActionStep.OK;
        }else return ActionStep.NOTOK;
    }

    @Override
    public void useAction(Game game) {

        game.jesterSwap(s0, s1);
        game.getActiveCharacterCard().setWasUsed(true);

    }
}
