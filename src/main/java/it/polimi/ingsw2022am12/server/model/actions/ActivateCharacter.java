package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;

public class ActivateCharacter extends PossibleAction {

    /**
     * "Constructor" Method of PossibleAction class
     *
     */
    public ActivateCharacter() {
        super(1);
    }

    private CharacterName characterName;

    @Override
    public ActionStep checkInputValidity(ArrayList<Selectable> input, Game game) {
        if(input.size()==1){
            if(input.get(0) instanceof CharacterCard){
                if(((CharacterCard) input.get(0)).getCost()<=game.getCurrentSchoolBoard().getNumOfCoins()){

                    if(game.getAvailableCharacters().stream()
                            .map(CharacterCard::getName)
                            .collect(Collectors.toList())
                            .contains(((CharacterCard) input.get(0)).getName())){
                        characterName = ((CharacterCard) input.get(0)).getName();
                    }

                    return ActionStep.OK;
                }
            }
        }return ActionStep.NOTOK;
    }

    @Override
    public void useAction(Game game) {
        game.payAndSetActiveCharacter(characterName);
    }
}
