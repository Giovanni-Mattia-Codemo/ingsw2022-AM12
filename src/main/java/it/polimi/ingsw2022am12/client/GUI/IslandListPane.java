package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.model.ClientIsland;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * IslandListPane is the graphical component that displays the 12 islands in circular layout at the beginning of the game
 */
public class IslandListPane extends CircularPane{

    private final Client client;
    private final ArrayList<IslandPane> islandStacks;

    /**
     * Constructor method of IslandListPane
     *
     * @param client the client that must interact with my scene
     */
    public IslandListPane(Client client) {
        super();
        this.client = client;
        islandStacks = new ArrayList<>();

        //CircularPane settings
        setMinWidth(1.0);
        setMinHeight(1.0);
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        setBackground(Background.fill(Color.CYAN));

        //Populating IslandListPane with game islands
        for (ClientIsland clientIsland : client.getClientGame().getIslands()) {
            IslandPane island = new IslandPane(clientIsland.getID(), client);
            int ratio = 3;
            island.maxHeightProperty().bind(this.heightProperty().multiply(0.05).multiply(ratio));
            island.maxWidthProperty().bind(this.widthProperty().multiply(0.05).multiply(ratio));
            islandStacks.add(island);
            getChildren().add(island);
        }

    }

    /**
     * refresh resets all the graphical assets in this pane according to the current state of the game
     */
    public void refresh(){
        ArrayList<IslandPane> temp = new ArrayList<>(islandStacks);
        for (ClientIsland clientIsland : client.getClientGame().getIslands()) {

            for(IslandPane i : islandStacks){
                if(clientIsland.getID()==i.getIslandId()){
                    i.refresh(clientIsland.getID());
                    temp.remove(i);
                }
            }
        }
        for(IslandPane i : temp){
            getChildren().remove(i);
            System.out.println(getChildren().size());
            islandStacks.remove(i);
        }
    }
}

