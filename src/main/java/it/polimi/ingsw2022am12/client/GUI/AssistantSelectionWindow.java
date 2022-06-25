package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.model.ClientAssistant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

/**
 * AssistantSelectionWindow is the class that creates the window through which we display a scene for the selection of
 * an Assistant
 */
public class AssistantSelectionWindow implements Window {

    /**
     * displayScene method creates the new window and the new scene for the assistant's selection; the Assistants are
     * ToggleButtons, to enable only one selection at a time
     *
     * @param client the client that needs the window to open
     */
    @Override
    public void displayScene(Client client) {
        //Stage settings
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Assistant Selection");
        window.setMinWidth(250);
        window.setOnCloseRequest(e -> closeProgram(window));

        Label label = new Label("Select an Assistant");
        label.setFont(new Font("Algerian", 25));

        VBox layout = new VBox();
        layout.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, Insets.EMPTY)));

        //Assistant box
        HBox assistantsLayout = new HBox();
        ToggleGroup assistants = new ToggleGroup();
        ArrayList<ClientAssistant> assistantList = client.getClientGame().getPlayableAssistants();
        for(ClientAssistant s : assistantList){
            ToggleButton assistantButton = new ToggleButton();
            Image assistant = null;
            switch (s.getTurnPower()){
                case 1->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (1).png")).toString());
                case 2->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (2).png")).toString());
                case 3->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (3).png")).toString());
                case 4->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (4).png")).toString());
                case 5->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (5).png")).toString());
                case 6->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (6).png")).toString());
                case 7->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (7).png")).toString());
                case 8->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (8).png")).toString());
                case 9->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (9).png")).toString());
                case 10->assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (10).png")).toString());
                default -> {}
            }
            //Assistant image settings
            ImageView assistantView = new ImageView(assistant);
            assistantView.fitHeightProperty().bind(assistantButton.heightProperty());
            assistantView.fitWidthProperty().bind(assistantButton.widthProperty());
            //Associate image to button
            assistantButton.setGraphic(assistantView);
            assistantButton.setMinSize(1.0, 1.0);
            assistantButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            assistantButton.setToggleGroup(assistants);
            assistantButton.getProperties().put ("id", s.getTurnPower());
            //Bind button to toggle group
            assistantsLayout.getChildren().add(assistantButton);
            assistantButton.prefHeightProperty().bind(assistantButton.widthProperty().multiply(1.44));
            assistantButton.prefWidthProperty().bind(assistantsLayout.widthProperty().divide(assistantList.size()));

            HBox.setHgrow(assistantButton, Priority.ALWAYS);


        }

        //Setting button to confirm choice
        Button selection = new Button("SELECT");
        selection.setOnAction(e-> {
            if(assistants.getSelectedToggle()!=null){
                    int assistantNum = (int) assistants.getSelectedToggle().getProperties().get("id");
                    ClientInputHandler.handle("Assistant "+assistantNum, client);
                    window.close();
            }
        });

        //Add objects to Vbox pane
        layout.getChildren().addAll(label, assistantsLayout, selection);
        layout.setAlignment(Pos.CENTER);
        //Set scene
        Scene scene = new Scene(layout, 800, 200);
        window.setScene(scene);
        window.show();
    }

    /**
     * closeProgram is the method associated to the "close window" button
     *
     * @param window the stage I want to close
     */
    private void closeProgram(Stage window) {
        window.close();
    }

}