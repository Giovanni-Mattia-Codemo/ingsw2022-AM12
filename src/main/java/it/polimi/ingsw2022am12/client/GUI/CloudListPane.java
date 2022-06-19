package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.model.ClientStudentCollection;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * CloudListPane is the graphical component that shows the various clouds on screen
 */
public class CloudListPane extends CircularPane {

    private final Client client;
    private final ArrayList<CloudPane> clouds;

    /**
     * Constructor method for CloudListPane class
     * 
     * @param client the client visualizing the clouds' list
     */
    public CloudListPane(Client client){
        super();
        this.client = client;
        clouds = new ArrayList<>();

        setMinWidth(1.0);
        setMinHeight(1.0);
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        setBackground(Background.fill(Color.CYAN));

        for(ClientStudentCollection i : client.getClientGame().getClouds()){
            CloudPane cloud = new CloudPane(i.getID(), client);
            int ratio = 3;
            cloud.maxHeightProperty().bind(this.heightProperty().multiply(0.1).multiply(ratio));
            cloud.maxWidthProperty().bind(this.widthProperty().multiply(0.1).multiply(ratio));
            clouds.add(cloud);
            getChildren().add(cloud);
        }

    }

    /**
     * refresh resets all the graphics for the objects placed on the various clouds in the list according to the current
     * state of the game
     */
    public void refresh(){
        for(ClientStudentCollection i : client.getClientGame().getClouds()){
            for(CloudPane pane : clouds){
                if(pane.getCloudId()==i.getID()){
                    pane.refresh();
                }
            }
        }
    }
}
