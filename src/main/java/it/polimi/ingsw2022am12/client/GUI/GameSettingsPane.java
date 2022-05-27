package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class GameSettingsPane extends VBox {

    public GameSettingsPane(Client client){
        super();
        Label playersNumLabel = new Label("Enter the number of players for the match (2-4)");
        playersNumLabel.setAlignment(Pos.CENTER);
        final TextField playersNum = new TextField();
        playersNum.setPromptText("Number between 2 and 4");
        playersNum.setPrefColumnCount(2);
        playersNum.getText();
        playersNum.setAlignment(Pos.CENTER);
        //GridPane.setConstraints(playersNum, 0, 0);

        Label modalityLabel = new Label("Game difficulty");
        modalityLabel.setAlignment(Pos.CENTER);
        final TextField modality = new TextField();
        modality.setPromptText("Boolean");
        modality.setAlignment(Pos.CENTER);
        modality.setPrefColumnCount(6);
        //GridPane.setConstraints(modality, 0, 1);

        Button submit = new Button("Submit");
        submit.setAlignment(Pos.CENTER);
        //GridPane.setConstraints(submit, 0, 1);

        submit.setOnAction(e->{
            if(playersNum.getText()!=null && !playersNum.getText().isEmpty() &&modality.getText() != null && !modality.getText().isEmpty()){
                ClientInputHandler.handle("GameSettings "+playersNum.getText()+" "+modality.getText(), client);
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(playersNumLabel,playersNum,modalityLabel, modality, submit);
    }

}
