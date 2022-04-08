package it.polimi.ingsw2022am12.server.model.actions;

import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.CharacterName;
import it.polimi.ingsw2022am12.server.model.Game;
import it.polimi.ingsw2022am12.server.model.PossibleAction;
import it.polimi.ingsw2022am12.server.model.Selectable;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class that defines the process of activation of a generic Character Card
 */
public class ActivateCharacter extends PossibleAction {

    /**
     * "Constructor" Method of ActivateCharacter class
     *
     */
    public ActivateCharacter() {
        super(1);
    }

    private CharacterName characterName;

    /**
     * Method checkInputValidity checks if I'm using the correct type and number of inputs required by my action
     *
     * @param input my chosen inputs
     * @param game the instance of my game
     * @return ActionStep number of inputs needed by my PossibleAction
     */
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
                        return ActionStep.OK;
                    }
                }
            }
        }return ActionStep.NOTOK;
    }

    /**
     * Method useAction uses the power of the Character, and sets the Character to Used
     *
     * @param game the game that is being played currently
     */
    @Override
    public void useAction(Game game) {
        game.payAndSetActiveCharacter(characterName);
    }
}
