package it.polimi.ingsw2022am12.client;

/**
 * Class that represents the team from the client's side
 */
public class ClientTeam {
    private String player1;
    private String player2;

    /**
     * Default constructor for ClientTeam
     */
    public ClientTeam(){


    }

    /**
     * Getter method for player1
     * @return name of player1
     */
    public String getPlayer1() {
        return player1;
    }

    /**
     * Getter method for player2
     * @return name of player2
     */
    public String getPlayer2() {
        return player2;
    }

    /**
     * Setter method for player1
     * @param player1 name of player1
     */
    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    /**
     * Setter method for player2
     * @param player2 name of player2
     */
    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
}
