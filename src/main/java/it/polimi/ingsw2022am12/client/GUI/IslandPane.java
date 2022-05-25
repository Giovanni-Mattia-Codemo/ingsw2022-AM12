package it.polimi.ingsw2022am12.client.GUI;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class IslandPane extends CircularPane{

    public IslandPane() {
        super();
        String resource = null;

        setMinWidth(1.0);
        setMinHeight(1.0);
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        setBackground(Background.fill(Color.CYAN));


        for (int i = 0; i < 12; i++) {
            StackPane stack = new StackPane();

            //VBox island = new VBox();

            stack.setMinSize(1.0,1.0);
            stack.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            int ratio = 1;

            switch (i) {
                case 0, 3, 6, 9 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island1.png")).toString();
                case 1, 4, 7, 10 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island2.png")).toString();
                case 2, 5, 8, 11 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island3.png")).toString();
                default -> {
                }
            }

            stack.maxHeightProperty().bind(this.heightProperty().multiply(0.1).multiply(ratio));
            stack.maxWidthProperty().bind(this.widthProperty().multiply(0.1).multiply(ratio));

            if (resource != null) {

                Image islandImage = new Image(resource);
                ImageView img = new ImageView(islandImage);
                stack.getChildren().add(img);
                img.setPreserveRatio(true);

                img.fitWidthProperty().bind(stack.widthProperty());
                img.fitHeightProperty().bind(stack.heightProperty());

                //stack.getChildren().add(island);

                getChildren().add(stack);
            }

        }
    }

    public static void main(String[] args) {
        Platform.startup(()->{
            Stage stage = new Stage();
            IslandPane islands = new IslandPane();
            Scene scene = new Scene(islands, 800, 600);
            islands.maxHeightProperty().bind(scene.heightProperty().multiply(1));
            islands.maxWidthProperty().bind(scene.widthProperty().multiply(1));

            stage.setScene(scene);
            stage.show();
        });
    }
}

