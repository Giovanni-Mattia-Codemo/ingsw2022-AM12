package it.polimi.ingsw2022am12.server;

public class CharacterHag extends Character{
    //choose color, -3 students of color
    public CharacterHag(){
        super("Hag", 3);
    }

    @Override
    public PossibleAction activate() {
        return new HagAction(super.getCost());
    }
}
