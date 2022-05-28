package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class IslandView extends ScrollPane {

    private final IslandListPane islandList;
    private final CloudListPane cloudList;
    private final Button switcher;

    public IslandView(Client client){
        super();

        islandList = new IslandListPane(client);
        cloudList = new CloudListPane(client);
        switcher = new Button("To school board");

        VBox box = new VBox();
        box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        box.setMinSize(1.0, 1.0);

        box.getChildren().add(cloudList);
        box.getChildren().add(islandList);
        box.getChildren().add(switcher);

        cloudList.maxHeightProperty().bind(this.heightProperty());
        cloudList.maxWidthProperty().bind(this.widthProperty());

        islandList.maxHeightProperty().bind(this.heightProperty());
        islandList.maxWidthProperty().bind(this.widthProperty());

        box.setAlignment(Pos.CENTER);
        setContent(box);

    }

    public void refresh(){
        islandList.refresh();
        cloudList.refresh();
    }

    public Button getSwitcher() {
        return switcher;
    }

    public IslandListPane getIslandList() {
        return islandList;
    }
}
