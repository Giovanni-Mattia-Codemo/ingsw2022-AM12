package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * Class that defines the layout of a scene used to insert the nick of the player at the beginning of the game
 */
public class NickInputPane extends VBox {

    /**
     * Constructor method of NickInputPane
     *
     * @param client the client that must interact with the scene
     */
    public NickInputPane(Client client){
        super();
        Label nickInsert = new Label("Insert your nick for the game");
        nickInsert.setFont(new Font("Algerian", 25));
        nickInsert.setAlignment(Pos.CENTER);
        final TextField name = new TextField();
        name.setPromptText("Enter your nick.");
        name.setAlignment(Pos.CENTER);
        name.setPrefColumnCount(20);
        name.setMaxWidth(200);

        Button submit = new Button("SUBMIT");
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
