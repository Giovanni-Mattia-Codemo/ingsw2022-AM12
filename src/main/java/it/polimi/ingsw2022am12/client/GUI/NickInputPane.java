package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class NickInputPane extends VBox {

    public NickInputPane(Client client){
        super();
        this.setBackground(Background.fill(Color.LIGHTGRAY));
        Label nickInsert = new Label("Insert your nick for the game");
        nickInsert.setAlignment(Pos.CENTER);
        final TextField name = new TextField();
        name.setPromptText("Enter your nick.");
        name.setAlignment(Pos.CENTER);
        name.setPrefColumnCount(20);

        Button submit = new Button("Submit");
        submit.setAlignment(Pos.CENTER);
        submit.setOnAction(e->{
            if(name.getText() != null && !name.getText().isEmpty()){
               ClientInputHandler.handle("Nick "+name.getText(), client);
            }
        });
        this.setAlignment(Pos.CENTER);
        getChildren().addAll(nickInsert, name, submit);

    }
}
