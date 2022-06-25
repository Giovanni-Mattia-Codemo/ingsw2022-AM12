package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.model.NoEntryImage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * IslandPane is the graphical component that represents the layout of the object on a single Island tile
 */
public class IslandPane extends StackPane {

    private int islandId;
    private final Client client;
    private final ImageView islandImageView;
    private final ArrayList<Label> studentNumberLabels;
    private final Label numOfTowers;
    private final Button towerButton;
    private final Button motherNature;
    private final Label numOfNoEntries;

    /**
     * Constructor method of the IslandPane class
     *
     * @param id the id of the single Island
     * @param client the client that will interact with the Island
     */
    public IslandPane(int id, Client client) {
        super();
        islandId = id;
        studentNumberLabels = new ArrayList<>();
        this.client = client;
        towerButton = new Button();
        numOfNoEntries = new Label();
        numOfTowers = new Label();
        islandImageView = new ImageView();

        //GridPane settings
        GridPane island = new GridPane();
        island.setMinSize(1.0, 1.0);
        island.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //StackPane settings
        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Creating button to make the island selectable
        Button islandButton = new Button();
        islandButton.setMinWidth(1.0);
        islandButton.setMinHeight(1.0);
        islandButton.setMaxHeight(Double.MAX_VALUE);
        islandButton.setMaxWidth(Double.MAX_VALUE);
        islandButton.setBackground(Background.EMPTY);
        //islandButton action
        islandButton.setOnAction(e -> ClientInputHandler.handle("Island " + islandId, client));

        //Adding island image to IslandPane
        getChildren().add(islandImageView);
        islandImageView.setPreserveRatio(false);
        islandImageView.fitWidthProperty().bind(widthProperty());
        islandImageView.fitHeightProperty().bind(heightProperty());

        //Creates HBox to fill the gridPane cells
        HBox box;
        HBox.setHgrow(island, Priority.NEVER);
        String resource = null;
        for (DiskColor c : DiskColor.values()) {
            int i = c.getValue();
            //For every cycle an HBox is instantiated
            box = new HBox();
            box.setFillHeight(false);
            box.setMinSize(1.0, 1.0);
            box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
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
            ImageView img = new ImageView(image);
            studentImg.setGraphic(img);
            img.fitHeightProperty().bind(studentImg.heightProperty());
            img.fitWidthProperty().bind(studentImg.widthProperty());
            //Button settings
            studentImg.setVisible(true);
            studentImg.setMinWidth(1.0);
            studentImg.setMinHeight(1.0);
            studentImg.setMaxHeight(Double.MAX_VALUE);
            studentImg.setMaxWidth(Double.MAX_VALUE);
            studentImg.setBackground(Background.EMPTY);
            studentImg.prefHeightProperty().bind(box.heightProperty());
            studentImg.prefWidthProperty().bind(studentImg.heightProperty());
            //HBox settings
            HBox.setHgrow(studentImg, Priority.NEVER);
            box.getChildren().add(studentImg);

            Label number = new Label();
            number.setMinSize(1.0,1.0);
            number.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            number.prefHeightProperty().bind(box.heightProperty());
            number.prefWidthProperty().bind(number.heightProperty());
            studentNumberLabels.add(number);

            HBox.setHgrow(number, Priority.NEVER);
            box.getChildren().add(number);
            island.add(box, i % 2, i / 2);

            box.prefHeightProperty().bind(heightProperty().multiply(0.25));
            box.prefWidthProperty().bind(widthProperty().multiply(0.5));

        }

        towerButton.setBackground(Background.EMPTY);

        box = new HBox();
        box.setFillHeight(false);
        box.prefHeightProperty().bind(heightProperty().multiply(0.25));
        box.prefWidthProperty().bind(widthProperty().multiply(0.5));
        box.setMinSize(1.0, 1.0);
        box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(towerButton, Priority.NEVER);

        towerButton.setMinSize(1.0, 1.0);
        towerButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        towerButton.prefHeightProperty().bind(box.heightProperty());
        towerButton.prefWidthProperty().bind(towerButton.heightProperty());
        box.getChildren().add(towerButton);

        box.getChildren().add(numOfTowers);
        numOfTowers.prefHeightProperty().bind(box.heightProperty());
        numOfTowers.prefWidthProperty().bind(numOfTowers.heightProperty());
        numOfTowers.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        numOfTowers.setMinSize(1.0, 1.0);
        HBox.setHgrow(numOfTowers, Priority.NEVER);
        island.add(box, 1, 2);

        box = new HBox();
        box.prefHeightProperty().bind(heightProperty().multiply(0.25));
        box.prefWidthProperty().bind(widthProperty().multiply(0.5));
        box.setMinSize(1.0, 1.0);
        box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        motherNature = new Button();
        resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/mother_nature.png")).toString();
        Image image = new Image(resource);
        ImageView img = new ImageView(image);
        motherNature.setGraphic(img);
        img.fitHeightProperty().bind(motherNature.heightProperty());
        img.fitWidthProperty().bind(motherNature.widthProperty());
        motherNature.setMinWidth(1.0);
        motherNature.setMinHeight(1.0);
        motherNature.setMaxHeight(Double.MAX_VALUE);
        motherNature.setMaxWidth(Double.MAX_VALUE);
        motherNature.setBackground(Background.EMPTY);
        motherNature.prefHeightProperty().bind(box.heightProperty());
        motherNature.prefWidthProperty().bind(motherNature.heightProperty());

        box.getChildren().add(motherNature);
        island.add(box, 0, 3);


        if(client.getClientGame().getCharacterByName("CHARACTER_HERBALIST")!=null){
            NoEntryImage noEntryButton = new NoEntryImage();
            noEntryButton.setBackground(Background.EMPTY);
            box = new HBox();
            box.prefHeightProperty().bind(heightProperty().multiply(0.25));
            box.prefWidthProperty().bind(widthProperty().multiply(0.5));
            box.setMinSize(1.0, 1.0);
            box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            box.setFillHeight(false);

            noEntryButton.setMinSize(1.0, 1.0);
            noEntryButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            box.getChildren().add(noEntryButton);
            HBox.setHgrow(noEntryButton, Priority.NEVER);
            box.getChildren().add(numOfNoEntries);
            numOfNoEntries.prefHeightProperty().bind(box.heightProperty());
            numOfNoEntries.prefWidthProperty().bind(numOfNoEntries.heightProperty());
            numOfNoEntries.setMinSize(1.0, 1.0);
            numOfNoEntries.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            HBox.setHgrow(numOfNoEntries, Priority.NEVER);
            noEntryButton.prefWidthProperty().bind(box.widthProperty().divide(5));
            island.add(box, 1, 3);
            noEntryButton.prefHeightProperty().bind(box.heightProperty());
            noEntryButton.prefWidthProperty().bind(noEntryButton.heightProperty());
        }

        getChildren().add(island);
        setAlignment(island, Pos.CENTER);
        getChildren().add(islandButton);

        island.maxHeightProperty().bind(this.heightProperty().multiply(0.5));
        island.maxWidthProperty().bind(this.widthProperty().multiply(0.5));
        island.setAlignment(Pos.CENTER);

        refresh(islandId);

    }

    /**
     * refresh resets all the graphical assets in this layout according to the current state of the game
     * @param id the id of the Island
     */
    public void refresh(int id){
        islandId = id;
        for(DiskColor c: DiskColor.values()){
            int color = c.getValue();
            studentNumberLabels.get(color).setText("x" + client.getClientGame().getIslandByID(id).getIslandStudentsOfColor(c));
        }

        motherNature.setVisible(id == client.getClientGame().getMotherNatureIndex());

        String conqueror = client.getClientGame().getIslandByID(id).getConqueror();
        if (!conqueror.equals("null")) {
            Image towerImage = null;
            switch (client.getClientGame().getTeams().indexOf(client.getClientGame().getTeamByNick(conqueror))) {
                case 0 -> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/white_tower.png")).toString());
                case 1 -> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/grey_tower.png")).toString());
                case 2 -> towerImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/black_tower.png")).toString());
                default -> {
                }
            }
            ImageView img = new ImageView(towerImage);
            img.fitHeightProperty().bind(towerButton.heightProperty());
            img.fitWidthProperty().bind(towerButton.widthProperty());
            towerButton.setGraphic(img);
            towerButton.setVisible(true);
            numOfTowers.setText("x" + client.getClientGame().getIslandByID(id).getNumber());
            numOfTowers.setVisible(true);
        }else{
            towerButton.setVisible(false);
            numOfTowers.setVisible(false);
        }
        if(client.getClientGame().getCharacterByName("CHARACTER_HERBALIST")!=null){
            numOfNoEntries.setText("x" + client.getClientGame().getIslandByID(islandId).getNumOfNoEntries());
        }
        Image resource;
        switch(client.getClientGame().getIslandByID(id).getNumber()){
            case 1 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island1.png")).toString());
            case 2 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/2_islands_cluster.png")).toString());
            case 3 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/3_islands_cluster.png")).toString());
            case 4 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/4_islands_cluster.png")).toString());
            case 5 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/5_islands_cluster.png")).toString());
            case 6 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/6_islands_cluster.png")).toString());
            case 7 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/7_islands_cluster.png")).toString());
            case 8 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/8_islands_cluster.png")).toString());
            case 9 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/9_islands_cluster.png")).toString());
            case 10 -> resource = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/10_islands_cluster.png")).toString());
            default -> {
                return;
            }
        }
        islandImageView.setImage(resource);
    }

    /**
     * Getter method for islandId
     *
     * @return the id of the Island
     */
    public int getIslandId() {
        return islandId;
    }
}
