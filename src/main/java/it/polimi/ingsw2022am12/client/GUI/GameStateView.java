package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
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
    private final Button toIslands;
    private VBox messageList;

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
    private void setRound(int roundNumber){
        this.round.setText("This is the round number "+roundNumber);
    }

    /**
     * Setter method for turnNumber
     *
     * @param turnNumber the number of the turn
     */
    private void setTurn(int turnNumber){
        this.turn.setText("This is the turn of the player number "+(turnNumber+1));
    }

    /**
     * setTurnOrder visualizes the turn order
     * 
     * @param order the ordered arrayList of players
     */
    private void setTurnOrder(ArrayList<String> order) {
        String text = "";
        for(String s:order){
            text = text.concat(s+"\t");
        }
        this.turnOrder.setText("The player order for this round is: "+text);
    }

    public void refresh(Client client){
        setRound(client.getClientGame().getRound());
        setTurn(client.getClientGame().getTurn());
        setTurnOrder(client.getClientGame().getOrderedNicks());
    }

    public void addMessage(String msg){
        messageList.getChildren().add(new Label(msg));
        while(messageList.getChildren().size()>= messageListSize){
            messageList.getChildren().remove(0);
        }
    }
}
