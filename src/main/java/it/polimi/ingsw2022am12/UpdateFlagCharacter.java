package it.polimi.ingsw2022am12;

public class UpdateFlagCharacter extends UpdateFlag{
    private String nick;
    public UpdateFlagCharacter(String nick) {
        super(Flag.CHARACTERS);
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }
}
