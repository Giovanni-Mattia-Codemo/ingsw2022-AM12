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
        playersNumLabel.setFont(new Font("Arial", 18));
        playersNumLabel.setAlignment(Pos.CENTER);
        ChoiceBox<String> playersNum = new ChoiceBox<>();
        playersNum.setId("Players Number");
        playersNum.getItems().add("2");
        playersNum.getItems().add("3");
        playersNum.getItems().add("4");
        playersNum.setStyle("-fx-pref-width: 220;");
        Label modalityLabel = new Label("Set game difficulty");
        modalityLabel.setFont(new Font("Arial", 18));
        modalityLabel.setAlignment(Pos.CENTER);

        ChoiceBox<String> mode = new ChoiceBox<>();
        mode.getItems().add("CharacterMode");
        mode.getItems().add("EasyMode");

        Button submit = new Button("Submit");
        submit.setAlignment(Pos.CENTER);

        submit.setOnAction(e->{
            String modality = "false";
            if(playersNum.getValue()!=null&&mode.getValue()!=null){
                if(mode.getValue().equals("CharacterMode")){
                    modality = "true";
                }
                ClientInputHandler.handle("GameSettings "+Integer.parseInt(playersNum.getValue())+" "+modality, client);
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(playersNumLabel,playersNum,modalityLabel, mode, submit);
    }

}
