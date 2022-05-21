package it.polimi.ingsw2022am12;

/**
 * Class that represents the Nickname string from the client's side
 */
public class NickInput {

    private String nick;

    /**
     * Constructor class of nickInput
     * @param nick nickname of the player
     */
    public NickInput(String nick){
        this.nick = nick;
    }

    /**
     * Setter method for nick
     * @param nick nickname of the player
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Getter method for nick
     * @return nickname of the player
     */
    public String getNick() {
        return nick;
    }
}
