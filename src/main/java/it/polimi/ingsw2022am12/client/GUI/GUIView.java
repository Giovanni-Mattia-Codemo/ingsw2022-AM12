package it.polimi.ingsw2022am12.client.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;


public class GUIView extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        /*
        Button b = newStudent("RED");
        Button c = new Button("Press me");
        Button d = newStudent("PINK");
        */


        AnchorPane m = new AnchorPane();
        HBox s = newSchoolBoard();


        s.setAlignment(Pos.CENTER);


        HBox hBox = new HBox();
                hBox.getChildren().addAll(s);
    hBox.setAlignment(Pos.BOTTOM_CENTER);
        m.getChildren().add(hBox);

        AnchorPane.setBottomAnchor(hBox, 10.0);
        /*
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.DEFAULT.getWidth(), BackgroundSize.DEFAULT.getHeight(), false,false,true,false);
        Image image = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Plancia_DEF2.png").toString());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT,bSize));
        Background background = new Background(backgroundImage);
        s.setBackground(background);*/
        //ImageView img = new ImageView(image);
        //AnchorPane.setBottomAnchor(s, 200.0);
        //m.getChildren().add(img);
        //AnchorPane.setTopAnchor(hBox, 200.0);


        Scene scene = new Scene(m);

        stage.setMinHeight(300);
        stage.setMinWidth(300);
        stage.setFullScreen(true);
        stage.setResizable(true);
        stage.setFullScreenExitHint("Press ESC to exit");



        stage.setTitle("Eryantis");
        stage.setScene(scene);
        stage.show();
    }

    public Button newStudent(String color){
        Button b = new Button();

        String resource = null;

        switch (color) {
            case "RED" -> resource = getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_red.png").toString();
            case "BLUE" -> resource = getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_blue.png").toString();
            case "PINK" -> resource = getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_pink.png").toString();
            case "YELLOW" -> resource = getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_yellow.png").toString();
            case "GREEN" -> resource = getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/student_green.png").toString();
            default -> {
            }
        }

        if(resource!=null){
            Image image = new Image(resource);
            ImageView img = new ImageView(image);

            img.setFitHeight(b.getHeight());
            img.setFitWidth(b.getWidth());
            b.setGraphic(img);
        }
        b.setMinWidth(1.0);
        b.setMinHeight(1.0);
        return b;
    }

    public HBox newSchoolBoard(){

        Button button = newStudent("RED");


        //Background for the schoolBoard
        button.setMaxHeight(1.0);
        button.setMaxWidth(1.0);

        HBox schoolBoard = new HBox();
        HBox.setHgrow(schoolBoard,Priority.ALWAYS);
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,false,true,false);
        Image image = new Image(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Plancia_DEF2.png").toString());
        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
        schoolBoard.setBackground(background);

        schoolBoard.setMinHeight(300.0);
        schoolBoard.setMinWidth(300.0);
        schoolBoard.setMaxSize(800.0,800.0);
        schoolBoard.getChildren().add(button);
        schoolBoard.setAlignment(Pos.CENTER);
        schoolBoard.setPadding(Insets.EMPTY);
        return schoolBoard;

    }



    public static void main(String[] args) {
        launch();
    }
}