package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.View;
import it.polimi.ingsw2022am12.client.model.ClientGame;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.*;


public class GUIView extends Application implements View {

    private ClientGame myGame;
    private ArrayList<Scene> activeScene;


    public GUIView(ClientGame game){
        myGame = game;

    }

    public GUIView(){

    }




    @Override
    public void start(Stage stage) throws IOException {

        /*
        schools= new ArrayList<>();
        double schoolRatio = 0.4337708831;
        GridPane box = new GridPane();
        ScrollPane scroll = new ScrollPane(box);
        Scene scene = new Scene(scroll, 800, 600);

        for(int i=0; i<4;i++){

            Label name = new Label();
            name.setMinSize(1.0,1.0);
            name.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            name.setText("Wario"+i);
            box.addRow(i*2, name);

            SchoolBoardContainer schoolBoard = new SchoolBoardContainer();
            schools.add(schoolBoard);

            box.addRow((i*2)+1, schoolBoard);

            schoolBoard.prefWidthProperty().bind(Bindings.min(scroll.widthProperty(),scroll.heightProperty().divide(schoolRatio)));

            schoolBoard.prefHeightProperty().bind(Bindings.min(scroll.widthProperty().multiply(schoolRatio),scroll.heightProperty()));

        }

        box.setGridLinesVisible(true);
        box.setAlignment(Pos.CENTER);


        stage.setMinWidth(0);
        stage.setScene(scene);

        stage.setResizable(true);
        stage.show();

         */




    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void viewMessage(String message) {

    }

    @Override
    public void updateGameView(ClientGame game) {
       /*
        for(SchoolBoardContainer sc : schools){
            sc.updateGame(game);
        }

        */
    }
}