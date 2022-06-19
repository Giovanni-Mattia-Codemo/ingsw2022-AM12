package it.polimi.ingsw2022am12.client.model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import java.util.Objects;

public class NoEntryImage extends Button {

    public NoEntryImage(){
        super();
        Image noEntry = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/deny_island_icon.png")).toString());
        ImageView img = new ImageView(noEntry);
        setVisible(true);
        setGraphic(img);
        img.fitHeightProperty().bind(this.heightProperty());
        img.fitWidthProperty().bind(this.widthProperty());
        setMinWidth(1.0);
        setMinHeight(1.0);
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        setBackground(Background.EMPTY);
        prefHeightProperty().bind(this.widthProperty());
    }
}
