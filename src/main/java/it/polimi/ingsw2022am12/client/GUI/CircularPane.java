package it.polimi.ingsw2022am12.client.GUI;

import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class CircularPane extends Pane {

    @Override
    protected void layoutChildren() {

        final double offsetForChildrenInCircle = (double) 360 / getChildren().size();
        double degrees = 0;  //Starting point from 0 degrees, moves clockwise from now on
        double x = 0;
        double y = 0;
        for (Node node : getChildren()) {

            if(degrees<=Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))){
                x = maxWidthProperty().getValue()*0.9;
                y = (x-maxWidthProperty().getValue()*0.5)*Math.tan(Math.toRadians(degrees))+maxHeightProperty().getValue()*0.5;
            }else if(180-Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))>=degrees&&degrees>Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))){
                y= maxHeightProperty().getValue()*0.88;
                x = (y-maxHeightProperty().getValue()*0.45)/Math.tan(Math.toRadians(degrees))+maxWidthProperty().getValue()*0.5;
            }else if(180-Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))<degrees&&degrees<=180+Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))){
                x = maxWidthProperty().getValue()*0.1;
                y = (x-maxWidthProperty().getValue()*0.5)*Math.tan(Math.toRadians(degrees))+maxHeightProperty().getValue()*0.5;
            }else if(180+Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))<degrees&&degrees<=360-Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))){
                y = maxHeightProperty().getValue()*0.12;
                x = (y-maxHeightProperty().getValue()*0.55)/Math.tan(Math.toRadians(degrees))+maxWidthProperty().getValue()*0.5;
            }else if(degrees>360-Math.toDegrees(Math.atan(maxHeightProperty().getValue()/maxWidthProperty().getValue()))){
                x = maxWidthProperty().getValue()*0.9;
                y = (x-maxWidthProperty().getValue()*0.5)*Math.tan(Math.toRadians(degrees))+maxHeightProperty().getValue()*0.5;
            }

            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degrees += offsetForChildrenInCircle;
        }
    }

}
