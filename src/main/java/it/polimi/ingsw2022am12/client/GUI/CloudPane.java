package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * CloudPane is the graphical component that represents the layout of the object on a single Cloud tile
 */
public class CloudPane extends StackPane {

    private final int cloudId;
    private final Client client;
    private final ArrayList<Label> studentNumberLabels;

    /**
     * Constructor method of the CloudPane class
     *
     * @param id the id of the single Cloud
     * @param client the client that will interact with the Cloud
     */
    public CloudPane(int id, Client client){
        super();
        cloudId = id;
        this.client = client;
        studentNumberLabels = new ArrayList<>();

        GridPane cloud = new GridPane();
        cloud.setMinSize(1.0, 1.0);
        cloud.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Image img = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/cloud_card.png")).toString());
        ImageView cloudImg = new ImageView(img);
        getChildren().add(cloudImg);
        cloudImg.setPreserveRatio(true);
        cloudImg.fitWidthProperty().bind(widthProperty());
        cloudImg.fitHeightProperty().bind(heightProperty());

        Button cloudButton = new Button();
        cloudButton.setMinWidth(1.0);
        cloudButton.setMinHeight(1.0);
        cloudButton.setMaxHeight(Double.MAX_VALUE);
        cloudButton.setMaxWidth(Double.MAX_VALUE);
        cloudButton.setBackground(Background.EMPTY);
        cloudButton.setOnAction(e -> ClientInputHandler.handle("Cloud " + cloudId, client));

        HBox box;
        HBox.setHgrow(cloud, Priority.NEVER);
        String resource = null;
        for (DiskColor c : DiskColor.values()) {
            int i = c.getValue();
            box = new HBox();
            box.setMinSize(1.0, 1.0);
            box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            Button studentImg = new Button();
            switch (i) {
                case 1 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_red.png")).toString();
                case 4 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_blue.png")).toString();
                case 3 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_pink.png")).toString();
                case 2 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_yellow.png")).toString();
                case 0 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_green.png")).toString();
            }

            Image image = new Image(resource);
            ImageView imageView = new ImageView(image);
            studentImg.setGraphic(imageView);
            imageView.fitHeightProperty().bind(studentImg.heightProperty());
            imageView.fitWidthProperty().bind(studentImg.widthProperty());
            studentImg.setVisible(true);
            studentImg.setMinWidth(1.0);
            studentImg.setMinHeight(1.0);
            studentImg.setMaxHeight(Double.MAX_VALUE);
            studentImg.setMaxWidth(Double.MAX_VALUE);
            studentImg.setBackground(Background.EMPTY);
            studentImg.prefHeightProperty().bind(studentImg.widthProperty());
            studentImg.prefWidthProperty().bind(box.heightProperty());
            box.setFillHeight(false);
            HBox.setHgrow(studentImg,Priority.NEVER);
            box.getChildren().add(studentImg);
            Label number = new Label("x" + client.getClientGame().getCloudByID(cloudId).getByColor(c));
            studentNumberLabels.add(number);
            box.getChildren().add(number);
            cloud.add(box, i % 2, i / 2);
        }

        getChildren().add(cloud);
        getChildren().add(cloudButton);
        setAlignment(cloud, Pos.CENTER);

        cloud.maxHeightProperty().bind(this.heightProperty());
        cloud.maxWidthProperty().bind(this.widthProperty());
        cloud.setAlignment(Pos.CENTER);

    }

    /**
     * refresh resets all the graphical assets in this layout according to the current state of the game
     *
     */
    public void refresh(){
        for(DiskColor c: DiskColor.values()){
            int color = c.getValue();
            studentNumberLabels.get(color).setText("x" + client.getClientGame().getCloudByID(cloudId).getByColor(c));
        }
    }

    /**
     * Getter method for cloudId
     *
     * @return the id of the Cloud
     */
    public int getCloudId() {
        return cloudId;
    }
}
