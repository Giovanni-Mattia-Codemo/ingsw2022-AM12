package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.model.ClientGame;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class SchoolBoardView extends ScrollPane{

    private ArrayList<SchoolBoardContainer> schools;
    private ClientGame myGame;

    public SchoolBoardView(ClientGame myGame){
        super();
        schools= new ArrayList<>();
        double schoolRatio = 0.4337708831;
        GridPane box = new GridPane();


        for(int i=0; i<4;i++){

            Label name = new Label();
            name.setMinSize(1.0,1.0);
            name.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            name.setText("Wario"+i);
            box.addRow(i*2, name);

            SchoolBoardContainer schoolBoard = new SchoolBoardContainer(myGame);
            schools.add(schoolBoard);

            box.addRow((i*2)+1, schoolBoard);

            schoolBoard.prefWidthProperty().bind(Bindings.min(widthProperty(),heightProperty().divide(schoolRatio)));
            schoolBoard.prefHeightProperty().bind(Bindings.min(widthProperty().multiply(schoolRatio),heightProperty()));

        }

        box.setGridLinesVisible(true);
        box.setAlignment(Pos.CENTER);
        setContent(box);

    }

}
