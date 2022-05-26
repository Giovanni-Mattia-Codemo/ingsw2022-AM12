package it.polimi.ingsw2022am12.client.GUI;

import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

/**
 * CircularPane defines a custom type of Pane that is used to display the islands in the GUI View
 */
public class CircularPane extends Pane {

    /**
     * layoutChildren defines mathematically the position of children inside the layout
     */
    @Override
    protected void layoutChildren() {

        final double radiusOfLayout = Math.min(maxHeightProperty().getValue()/2, maxWidthProperty().getValue()/2);
        final double offsetForChildrenInCircle = 360 / getChildren().size();
        double degrees = 0;  //Starting point from 0 degrees, moves clockwise from now on
        double x = 0;
        double y = 0;
        for (Node node : getChildren()) {
            if(360-Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))<=degrees||degrees<Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))){
                x = maxWidthProperty().getValue()*0.9;
                y = (x-maxWidthProperty().getValue()*0.5)*Math.tan(Math.toRadians(degrees))+maxHeightProperty().getValue()*0.5;
            }else if(180-Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))>degrees&&degrees>=Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))){
                y= maxHeightProperty().getValue()*0.9;
                x = (y-maxHeightProperty().getValue()*0.5)/Math.tan(Math.toRadians(degrees))+maxWidthProperty().getValue()*0.5;
            }else if((Math.toRadians(180)-Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue())<=Math.toRadians(degrees)&&Math.toRadians(degrees)<Math.toRadians(180)+Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))){
                x = maxWidthProperty().getValue()*0.1;
                y = (maxWidthProperty().getValue()*0.5-x)*Math.tan(Math.toRadians(degrees))+maxHeightProperty().getValue()*0.5;
            }else if(Math.toRadians(180)+Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue())<=Math.toRadians(degrees)&&Math.toRadians(degrees)<Math.toRadians(360)-Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue())){
                y= maxHeightProperty().getValue()*0.1;
                x = (maxHeightProperty().getValue()*0.5-y)/Math.tan(Math.toRadians(degrees))+maxWidthProperty().getValue()*0.5;
            }
            //double x = Math.min(Math.abs()) radiusOfLayout * Math.cos(Math.toRadians(degrees)) + getWidth()/ 2;
            //double y = radiusOfLayout * Math.sin(Math.toRadians(degrees)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degrees += offsetForChildrenInCircle;
        }
    }

}
