package it.polimi.ingsw2022am12;

public class UpdateFlagSchool extends UpdateFlag{
    private String nick;
    public UpdateFlagSchool( String nick) {
        super(Flag.SCHOOL);
        this.nick=nick;
    }

    public String getNick() {
        return nick;
    }
}
