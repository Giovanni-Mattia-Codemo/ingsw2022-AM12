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

public class IslandPane extends StackPane {

    private int islandId;
    private final Client client;
    private final ImageView islandImageView;
    private final ArrayList<Label> studentNumberLabels;
    private final Label numOfTowers;
    private final Button towerButton;
    private final Button motherNature;

    public IslandPane(int id, Client client) {
        super();
        islandId = id;
        studentNumberLabels = new ArrayList<>();
        this.client = client;
        towerButton = new Button();
        numOfTowers = new Label();
        islandImageView = new ImageView();
        GridPane island = new GridPane();

        island.setMinSize(1.0, 1.0);
        island.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Button islandButton = new Button();
        /*
        String resource = null;
        switch (id) {
            case 0, 3, 6, 9 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island1.png")).toString();
            case 1, 4, 7, 10 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island2.png")).toString();
            case 2, 5, 8, 11 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island3.png")).toString();
            default -> {
            }
        }
        if (resource != null) {

         */
        islandButton.setMinWidth(1.0);
        islandButton.setMinHeight(1.0);
        islandButton.setMaxHeight(Double.MAX_VALUE);
        islandButton.setMaxWidth(Double.MAX_VALUE);
        islandButton.setBackground(Background.EMPTY);
        islandButton.setOnAction(e -> ClientInputHandler.handle("Island " + islandId, client));
        getChildren().add(islandImageView);
        islandImageView.setPreserveRatio(true);
        islandImageView.fitWidthProperty().bind(widthProperty());
        islandImageView.fitHeightProperty().bind(heightProperty());

        HBox box;
        HBox.setHgrow(island, Priority.NEVER);
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
            ImageView img = new ImageView(image);
            studentImg.setGraphic(img);
            img.fitHeightProperty().bind(studentImg.heightProperty());
            img.fitWidthProperty().bind(studentImg.widthProperty());
            studentImg.setVisible(true);
            studentImg.setMinWidth(1.0);
            studentImg.setMinHeight(1.0);
            studentImg.setMaxHeight(Double.MAX_VALUE);
            studentImg.setMaxWidth(Double.MAX_VALUE);
            studentImg.setBackground(Background.EMPTY);
            studentImg.prefHeightProperty().bind(studentImg.widthProperty());
            studentImg.prefWidthProperty().bind(box.widthProperty().divide(5));

            box.getChildren().add(studentImg);
            Label number = new Label();
            studentNumberLabels.add(number);
            box.getChildren().add(number);
            island.add(box, i % 2, i / 2);
        }

        towerButton.setBackground(Background.EMPTY);
        box = new HBox();
        box.setMinSize(1.0, 1.0);
        box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        towerButton.setMinSize(1.0, 1.0);
        towerButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        box.getChildren().add(towerButton);

        box.getChildren().add(numOfTowers);
        towerButton.prefHeightProperty().bind(towerButton.widthProperty());
        towerButton.prefWidthProperty().bind(box.widthProperty().divide(5));
        island.add(box, 1, 2);

        box = new HBox();
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
        motherNature.prefHeightProperty().bind(motherNature.widthProperty());
        box.getChildren().add(motherNature);
        island.add(box, 0, 3);
        motherNature.prefHeightProperty().bind(motherNature.widthProperty());
        motherNature.prefWidthProperty().bind(box.widthProperty());

        getChildren().add(island);
        setAlignment(island, Pos.CENTER);
        getChildren().add(islandButton);

        island.prefHeightProperty().bind(this.heightProperty());
        island.prefWidthProperty().bind(this.widthProperty());
        island.setAlignment(Pos.CENTER);

        refresh(islandId);

    }

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
            numOfTowers.setText("x" + client.getClientGame().getIslandByID(id).getNumber());
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

    public int getIslandId() {
        return islandId;
    }
}
