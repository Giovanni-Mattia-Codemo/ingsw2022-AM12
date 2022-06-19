package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Class that defines the layout of a scene used to represent a part of our table: it contains the 12 islands, the clouds and
 * the 3 characters randomly drawn at the beginning of the game
 */
public class IslandView extends ScrollPane {

    private final IslandListPane islandList;
    private final CloudListPane cloudList;
    private final CharacterListPane characters;

    /**
     * Constructor method of IslandView
     *
     * @param client the client that must interact with the scene
     */
    public IslandView(Client client){
        super();
        StackPane islandCloudStack = new StackPane();
        islandList = new IslandListPane(client);
        cloudList = new CloudListPane(client);
        characters = new CharacterListPane(client);

        VBox box = new VBox();
        box.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        box.setMinSize(1.0, 1.0);
        box.setFillWidth(false);
        box.getChildren().add(islandCloudStack);
        box.getChildren().add(characters);

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

    /**
     * refresh method resets all the graphical assets of this pane according to the current state of the game
     */
    public void refresh(){
        islandList.refresh();
        cloudList.refresh();
        characters.refresh();
    }

    /**
     * Getter method for islandList
     *
     * @return IslandListPane the layout that contains the graphical assets of the islands
     */
    public IslandListPane getIslandList() {
        return islandList;
    }
}
