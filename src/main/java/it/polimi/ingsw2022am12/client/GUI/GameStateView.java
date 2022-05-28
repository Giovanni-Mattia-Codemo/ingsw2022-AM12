package it.polimi.ingsw2022am12.client.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

/**
 * GameStateView class visualizes the state of the game,in particular it shows the turn order and round number
 */
public class GameStateView extends Pane {

    private final Label round;
    private final Label turn;
    private final Label turnOrder;
    private Button toIslands;

    /**
     * Constructor method of GameStateView
     */
    public GameStateView(){
        super();
        VBox box = new VBox();
        round = new Label();
        round.setText("Round 0");
        turn = new Label();
        turn.setText("Turn 0");
        turnOrder = new Label();
        turnOrder.setText("Not yet defined");
        toIslands = new Button("To islands");
        box.getChildren().addAll(round, turn, turnOrder, toIslands);
        getChildren().add(box);
    }

    public Button getToIslands() {
        return toIslands;
    }

    /**
     * Setter method for roundNumber
     *
     * @param roundNumber the number of the round
     */
    public void setRound(int roundNumber){
        this.round.setText("This is the round number "+roundNumber);
    }

    /**
     * Setter method for turnNumber
     *
     * @param turnNumber the number of the turn
     */
    public void setTurn(int turnNumber){
        this.turn.setText("This is the turn of the player number "+turnNumber);
    }

    /**
     * setTurnOrder visualizes the turn order
     * 
     * @param order the ordered arrayList of players
     */
    public void setTurnOrder(ArrayList<String> order) {
        String text = "";
        for(String s:order){
            text = text.concat(s+"\t");
        }
        this.turnOrder.setText("The player order for this round is: "+text);
    }
}
