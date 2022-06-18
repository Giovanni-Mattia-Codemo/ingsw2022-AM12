package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * GameStateView class visualizes the state of the game,in particular it shows the turn order and round number
 */
public class GameStateView extends VBox {
    private final static int messageListSize = 20;
    private final Label round;
    private final Label turn;
    private final Label players;
    private final Button switcher;
    private final VBox messageList;

    /**
     * Constructor method of GameStateView
     */
    public GameStateView(){
        super();
        setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));
        round = new Label();
        round.setText("ROUND: 0");
        turn = new Label();
        turn.setText("TURN: 0");
        Label turnOrder = new Label();
        turnOrder.setText("THE PLAYER ORDER IS:");
        players = new Label("");
        switcher = new Button("SWITCH SCENE");
        getChildren().addAll(round, turn, turnOrder, players);
        messageList = new VBox();
        messageList.setSpacing(5);
        ScrollPane messages = new ScrollPane(messageList);
        messages.setStyle("-fx-background: rgb(229,198,159);\n -fx-background-color: rgb(229,198,159)");
        messages.prefHeightProperty().bind(this.heightProperty().divide(2));
        getChildren().add(messages);
        getChildren().add(switcher);
        setAlignment(Pos.TOP_CENTER);
    }

    public Button getSwitcher() {
        return switcher;
    }

    /**
     * Setter method for roundNumber
     *
     * @param roundNumber the number of the round
     */
    private void setRound(int roundNumber){
        this.round.setText("THIS IS THE ROUND NUMBER: "+roundNumber);
    }

    /**
     * Setter method for turnNumber
     *
     * @param nick the number of the turn
     */
    private void setTurn(String nick){
        this.turn.setText("CURRENT PLAYER: "+nick);
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
        this.players.setText(text+"\n\n");
    }

    public void refresh(Client client){
        setRound(client.getClientGame().getRound());
        setTurn(client.getClientGame().getOrderedNicks().get(client.getClientGame().getTurn()));
        setTurnOrder(client.getClientGame().getOrderedNicks());
    }

    public void addMessage(String msg){
        msg = msg.toUpperCase();
        messageList.getChildren().add(new Label(msg));
        while(messageList.getChildren().size()>= messageListSize){
            messageList.getChildren().remove(0);
        }
    }

    public void clearMessages(){
        messageList.getChildren().clear();
    }
}
