package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.model.ClientAssistant;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
     * displayScene method creates the new window and the new scene for the assistant's selection
     *
     * @param client the client that needs the window to open
     * @param nick nickname of the player
     */
    @Override
    public void displayScene(Client client, String nick) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Assistant Selection");
        window.setMinWidth(250);

        window.setOnCloseRequest(e -> closeProgram(window));

        Label label = new Label("Select an Assistant:");

        VBox layout = new VBox();
        HBox assistantsLayout = new HBox();


        ToggleGroup assistants = new ToggleGroup();

        ArrayList<ClientAssistant>assistantList = client.getClientGame().getSchoolBoardByNick(nick).getAssistants();
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
            ImageView assistantView = new ImageView(assistant);

            assistantView.fitHeightProperty().bind(assistantButton.heightProperty());
            assistantView.fitWidthProperty().bind(assistantButton.widthProperty());

            assistantButton.setGraphic(assistantView);
            assistantButton.setMinSize(1.0, 1.0);
            assistantButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            assistantButton.setToggleGroup(assistants);
            assistantButton.getProperties().put ("id", s.getTurnPower());
            assistantsLayout.getChildren().add(assistantButton);
            assistantButton.prefHeightProperty().bind(assistantButton.widthProperty().multiply(1.44));
            assistantButton.prefWidthProperty().bind(assistantsLayout.widthProperty().divide(assistantList.size()));

            HBox.setHgrow(assistantButton, Priority.ALWAYS);


        }



        Button selection = new Button("select");
        selection.setOnAction(e-> {if(assistants.getSelectedToggle()!=null){
                    ClientInputHandler.handle("Assistant "+assistants.getSelectedToggle().getProperties().get("id"), client);
                    window.close();
        }
            else System.out.println("Null value");
        });//clientInputHandler.handle("Assistant "+assistants.getSelectedToggle().getProperties().get("id")));

        layout.getChildren().addAll(label, assistantsLayout, selection);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);
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


    /**
     * displayConfirmScene creates a popup to confirm the selection of the Assistant
     */
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
            this.closeAfterSelection();
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

    /**
     * closeAfterSelection method keeps the game going after the selection of an Available assistant
     */
    @Override
    public void closeAfterSelection() {

    }
}