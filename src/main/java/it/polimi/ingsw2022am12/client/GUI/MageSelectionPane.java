package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MageSelectionPane extends VBox {

    public MageSelectionPane(Client myClient){
        super();
        Label selectMage = new Label("Pick a mage");
        selectMage.setAlignment(Pos.CENTER);

        HBox mageLayout = new HBox();

        ToggleGroup mages = new ToggleGroup();
        ArrayList<Integer> pickable = new ArrayList<>();
        if(myClient.getClientGame()!=null){
             pickable = myClient.availableMages();
        }else{
            Collections.addAll(pickable, 0, 1, 2, 3);
        }

        for(int i:pickable){
            ToggleButton mageButton = new ToggleButton();
            Image mage = null;

            switch (i){
                case 0->mage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_back_1@3x.png")).toString());
                case 1->mage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_back_11@3x.png")).toString());
                case 2->mage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_back_21@3x.png")).toString());
                case 3->mage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_back_31@3x.png")).toString());
                default -> {}
            }
            ImageView mageView = new ImageView(mage);

            mageView.fitHeightProperty().bind(mageButton.heightProperty());
            mageView.fitWidthProperty().bind(mageButton.widthProperty());

            mageButton.setGraphic(mageView);
            mageButton.setMinSize(1.0, 1.0);
            mageButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            mageButton.setToggleGroup(mages);
            mageButton.getProperties().put ("id", i);
            mageLayout.getChildren().add(mageButton);
            mageButton.prefHeightProperty().bind(mageButton.widthProperty().multiply(1.44));
            mageButton.prefWidthProperty().bind(mageLayout.widthProperty().divide(pickable.size()));
            HBox.setHgrow(mageButton, Priority.ALWAYS);

        }
        Button select = new Button("Select");
        select.setAlignment(Pos.CENTER);
        select.setOnAction(e->{
            if(mages.getSelectedToggle()!=null){
                ClientInputHandler.handle("Mage "+mages.getSelectedToggle().getProperties().get("id"), myClient);
            }
        });

        getChildren().addAll(selectMage, mageLayout, select);
        setAlignment(Pos.CENTER);

    }
}
