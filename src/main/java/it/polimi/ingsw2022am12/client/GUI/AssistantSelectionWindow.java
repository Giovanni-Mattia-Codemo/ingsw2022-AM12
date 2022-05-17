package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.ClientInputHandler;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.image.Image;
import java.io.IOException;



public class AssistantSelectionWindow implements Window {



    @Override
    public void displayScene() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Character Selection");
        window.setMinWidth(250);



        window.setOnCloseRequest(e -> closeProgram(window));

        Label label = new Label("Select a Character:");



        VBox layout = new VBox();
        HBox assistantsLayout = new HBox();


        ToggleGroup assistants = new ToggleGroup();
        for(int i = 1; i<=10; i++){
            ToggleButton assistantButton = new ToggleButton();
            Image assistant = null;


            switch (i){
                case 1->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (1).png").toString());
                case 2->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (2).png").toString());
                case 3->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (3).png").toString());
                case 4->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (4).png").toString());
                case 5->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (5).png").toString());
                case 6->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (6).png").toString());
                case 7->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (7).png").toString());
                case 8->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (8).png").toString());
                case 9->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (9).png").toString());
                case 10->assistant = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (10).png").toString());
                default -> {}
            }
            ImageView assistantView = new ImageView(assistant);

            assistantView.fitHeightProperty().bind(assistantButton.heightProperty());
            assistantView.fitWidthProperty().bind(assistantButton.widthProperty());

            assistantButton.setGraphic(assistantView);
            assistantButton.setMinSize(1.0, 1.0);
            assistantButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            assistantButton.setToggleGroup(assistants);
            assistantButton.getProperties().put ("id", i);
            assistantsLayout.getChildren().add(assistantButton);

        }

        Button selection = new Button("select");
        selection.setOnAction(e->  //clientInputHandler.handle("Assistant "+assistants.getSelectedToggle().getProperties().get("id")));
        System.out.println("Assistant "+assistants.getSelectedToggle().getProperties().get("id")));
        layout.getChildren().addAll(label, assistantsLayout, selection);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);
        window.setScene(scene);
        window.show();


    }

    private void closeProgram(Stage window) {
        System.out.println("Undoing character selection");
        window.close();
    }


    @Override
    public void displayConfirmScene() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm choice");
        window.setMinWidth(100);

        Label confirmationLabel = new Label();
        confirmationLabel.setText("Are you sure with this selection?");

        Button yes = new Button("Yes");
        Button no = new Button("No");

        yes.setOnAction(e -> {
            window.close();
            this.closeAfterSelection(); //CLOSES BOTH WINDOWS
        });

        no.setOnAction(e -> {
            window.close(); //SHOULD ONLY CLOSE POPUP TO STAY INSIDE SELECTION
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(confirmationLabel, yes, no);
        layout.setAlignment(Pos.CENTER);
        Scene confirmationScene = new Scene(layout);
        window.setScene(confirmationScene);
        window.show();

    }

    @Override
    public void closeAfterSelection() {

    }
}