package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IslandView extends ScrollPane {

    private final IslandListPane islandList;
    private final CloudListPane cloudList;
    private final Button switcher;
    private final CharacterListPane characters;

    public IslandView(Client client){
        super();
        StackPane islandCloudStack = new StackPane();
        islandList = new IslandListPane(client);
        cloudList = new CloudListPane(client);
        characters = new CharacterListPane(client);
        switcher = new Button("To school board");

        VBox box = new VBox();
        box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        box.setMinSize(1.0, 1.0);
        box.setFillWidth(false);
        //box.getChildren().add(cloudList);
        //box.getChildren().add(islandList);
        box.getChildren().add(islandCloudStack);
        box.getChildren().add(characters);
        box.getChildren().add(switcher);

        cloudList.maxHeightProperty().bind(this.widthProperty().divide(3));
        cloudList.maxWidthProperty().bind(this.widthProperty().divide(3));
        cloudList.setPickOnBounds(false);

        islandList.maxHeightProperty().bind(this.widthProperty());
        islandList.maxWidthProperty().bind(this.widthProperty());

        islandCloudStack.setAlignment(Pos.CENTER);
        islandCloudStack.getChildren().addAll(islandList, cloudList);
        VBox.setVgrow(islandCloudStack, Priority.NEVER);

        islandCloudStack.prefHeightProperty().bind(this.widthProperty());
        islandCloudStack.prefWidthProperty().bind(this.widthProperty());

        characters.prefHeightProperty().bind(this.widthProperty().multiply(6.0/2.0).multiply(0.33));
        characters.prefWidthProperty().bind(this.widthProperty());
        VBox.setVgrow(characters, Priority.NEVER);



        box.setAlignment(Pos.CENTER);
        setContent(box);

    }

    public void refresh(){
        islandList.refresh();
        cloudList.refresh();
        characters.refresh();
    }

    public Button getSwitcher() {
        return switcher;
    }

    public IslandListPane getIslandList() {
        return islandList;
    }
}
