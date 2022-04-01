package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.model.characters.CharacterPrincess;

import java.util.ArrayList;

public class PrincessAction extends PossibleAction {

    private DiskColor color;

    public PrincessAction(){
        super(2);
    }


    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if(input.size()==1){
            if (input.get(0) instanceof Student){
                Student tmp = (Student) input.get(0);
                if(tmp.getPositionID()==((CharacterPrincess)game.getActiveCharacterCard()).getStudents().getID()){
                    if(((CharacterPrincess)game.getActiveCharacterCard()).getStudents().getFirstStudentOfColor(tmp.getColor()).isPresent()){
                        color = tmp.getColor();
                        return ActionStep.OK;
                    }

                }
            }
        }
        return ActionStep.NOTOK;
    }

    @Override
    public void useAction(Game game) {
        game.moveStudentFromCardToRoom(color);
        game.getActiveCharacterCard().setWasUsed(true);
    }
}
