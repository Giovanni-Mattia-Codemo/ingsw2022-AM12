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

        //Creates grid pane to put students on the cloud
        GridPane cloud = new GridPane();
        cloud.setMinSize(1.0, 1.0);
        cloud.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Loads cloud image from resources
        Image img = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/cloud_card.png")).toString());
        ImageView cloudImg = new ImageView(img);

        //Adds image to CloudPane and sets its bindings
        getChildren().add(cloudImg);
        cloudImg.setPreserveRatio(true);
        cloudImg.fitWidthProperty().bind(widthProperty());
        cloudImg.fitHeightProperty().bind(heightProperty());

        //Creates button to make the cloud selectable
        Button cloudButton = new Button();
        cloudButton.setMinWidth(1.0);
        cloudButton.setMinHeight(1.0);
        cloudButton.setMaxHeight(Double.MAX_VALUE);
        cloudButton.setMaxWidth(Double.MAX_VALUE);
        cloudButton.setBackground(Background.EMPTY);

        //Sets the cloudButton action
        cloudButton.setOnAction(e -> ClientInputHandler.handle("Cloud " + cloudId, client));

        //Creates HBox to fill the gridPane cells
        HBox box;
        HBox.setHgrow(cloud, Priority.NEVER);

        String resource = null;
        for (DiskColor c : DiskColor.values()) {
            int i = c.getValue();

            //For every cycle an HBox is instantiated
            box = new HBox();
            box.setFillHeight(false);
            box.setMinSize(1.0, 1.0);
            box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            box.prefHeightProperty().bind(cloud.heightProperty().multiply(0.25));
            box.prefWidthProperty().bind(cloud.widthProperty().multiply(0.5));

            //Creates button used to visualize student image
            Button studentImg = new Button();
            switch (i) {
                case 1 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_red.png")).toString();
                case 4 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_blue.png")).toString();
                case 3 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_pink.png")).toString();
                case 2 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_yellow.png")).toString();
                case 0 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_green.png")).toString();
            }

            //Loading the student image from the resources and sets the studentButton graphics
            Image image = null;
            if (resource != null) {
                image = new Image(resource);
            }
            ImageView imageView = new ImageView(image);
            studentImg.setGraphic(imageView);
            imageView.fitHeightProperty().bind(studentImg.heightProperty());
            imageView.fitWidthProperty().bind(studentImg.widthProperty());

            //Button settings
            studentImg.setVisible(true);
            studentImg.setMinWidth(1.0);
            studentImg.setMinHeight(1.0);
            studentImg.setMaxHeight(Double.MAX_VALUE);
            studentImg.setMaxWidth(Double.MAX_VALUE);
            studentImg.setBackground(Background.EMPTY);
            studentImg.prefHeightProperty().bind(box.widthProperty());
            studentImg.prefWidthProperty().bind(studentImg.heightProperty());

            //HBox settings
            box.setFillHeight(false);
            HBox.setHgrow(studentImg,Priority.NEVER);

            //Adding button to box
            box.getChildren().add(studentImg);

            //Creating new label with the number of students of a specific color
            Label number = new Label("x" + client.getClientGame().getCloudByID(cloudId).getByColor(c));
            number.setMinSize(1.0,1.0);
            number.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            HBox.setHgrow(number, Priority.NEVER);
            number.prefHeightProperty().bind(box.heightProperty());
            number.prefWidthProperty().bind(number.heightProperty());

            //Adding label to array list of labels
            studentNumberLabels.add(number);

            //Adding label to box
            box.getChildren().add(number);

            //Adding box in the right cell of the gridPane
            cloud.add(box, i % 2, i / 2);
        }
        //Adding to CloudPane the cloud gridPane
        getChildren().add(cloud);
        //Adding to CloudPane teh cloudButton
        getChildren().add(cloudButton);

        //Aligning cloud gridPane to the center of the CloudPane
        setAlignment(cloud, Pos.CENTER);

        //Settings of cloud gridPane
        cloud.maxHeightProperty().bind(this.heightProperty().multiply(0.8));
        cloud.maxWidthProperty().bind(this.widthProperty().multiply(0.8));
        cloud.setAlignment(Pos.CENTER);

    }

    /**
     * refresh resets all the graphical assets in this layout according to the current state of the game
     *
     */
    public void refresh(){
        //Changes number of students by color
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
