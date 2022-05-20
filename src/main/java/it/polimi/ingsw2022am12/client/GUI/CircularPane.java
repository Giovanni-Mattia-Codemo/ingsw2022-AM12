package it.polimi.ingsw2022am12.client.GUI;

import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class CircularPane extends Pane {

    @Override
    protected void layoutChildren() {

        final int radiusOfLayout = 200;
        final double offsetForChildrenInCircle = 360 / getChildren().size();
        double degrees = 0;  //Starting point from 0 degrees, moves clockwise from now on
        for (Node node : getChildren()) {
            double x = radiusOfLayout * Math.cos(Math.toRadians(degrees)) + getWidth() / 2;
            double y = radiusOfLayout * Math.sin(Math.toRadians(degrees)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degrees += offsetForChildrenInCircle;
        }
    }

}
