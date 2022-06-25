package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * GameSettingsPane creates a special window that appears at the beginning of the Game, and allows the first connected player
 * to select the settings of the match
 */
public class GameSettingsPane extends VBox {

    /**
     * Constructor method of GameSettingsPane
     *
     * @param client the chosen client (client of the first player)
     */
    public GameSettingsPane(Client client){
        super();

        Label playersNumLabel = new Label("Enter the number of players for the match");
        playersNumLabel.setFont(new Font("Algerian", 18));
        playersNumLabel.setAlignment(Pos.CENTER);

        //Creating choiceBox with possible players number for the match
        ChoiceBox<String> playersNum = new ChoiceBox<>();
        playersNum.setId("Players Number");
        playersNum.getItems().add("2");
        playersNum.getItems().add("3");
        playersNum.getItems().add("4");
        //Sets choiceBox pose
        playersNum.setStyle("-fx-pref-width: 220;");

        Label modalityLabel = new Label("Set game difficulty");
        modalityLabel.setFont(new Font("Algerian", 18));
        modalityLabel.setAlignment(Pos.CENTER);

        //Creating choiceBox with possible mode for the match
        ChoiceBox<String> mode = new ChoiceBox<>();
        mode.getItems().add("CHARACTER MODE");
        mode.getItems().add("EASY MODE");

        Button submit = new Button("SUBMIT");
        submit.setAlignment(Pos.CENTER);
        submit.setOnAction(e->{
            String modality = "false";
            if(playersNum.getValue()!=null&&mode.getValue()!=null){
                if(mode.getValue().equals("CHARACTER MODE")){
                    modality = "true";
                }
                ClientInputHandler.handle("GameSettings "+Integer.parseInt(playersNum.getValue())+" "+modality, client);
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(playersNumLabel,playersNum,modalityLabel, mode, submit);
    }

}
