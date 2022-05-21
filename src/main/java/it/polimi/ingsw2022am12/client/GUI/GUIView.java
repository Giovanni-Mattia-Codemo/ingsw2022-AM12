package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.View;
import it.polimi.ingsw2022am12.client.model.ClientGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.*;


public class GUIView extends Application implements View {

    private ClientGame myGame;
    private ClientInputHandler inputHandler;
    private ArrayList<Scene> activeScene;


    private ArrayList<SchoolBoardContainer> schools;

    /**
     * Default constructor
     */
    public GUIView(){

    }

    /**
     * Constructor for setting the game
     *
     * @param game being sent to view
     */
    public GUIView(ClientGame game, ClientInputHandler inputHandler){
        myGame = game;
        this.inputHandler = inputHandler;
    }
    
    @Override
    public void start(Stage stage) throws IOException {

        SchoolBoardView schools = new SchoolBoardView(myGame);
        HBox box = new HBox();
        GameStateView state = new GameStateView();
        /*
              Button checkIsland = new Button("Go to Islands");
              checkIsland.SetOnAction(e-> stage.setScene(islandScene);
         */
        box.getChildren().addAll(schools, state);

        Scene scene = new Scene(box);
        stage.setScene(scene);

        stage.setResizable(true);
        stage.show();

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


        //INITIAL ISLAND SCENE

        /*
        CircularPane islandLayout = new CircularPane();
        String resource = null;
        for(int i = 0; i < 11; i++) {
            Button islandButton = new Button();

            setMinWidth(1.0);
            setMinHeight(1.0);
            setMaxHeight(Double.MAX_VALUE);
            setMaxWidth(Double.MAX_VALUE);
            setBackground(Background.EMPTY);

            switch(i)
            { case 0 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island1.png")).toString();
              case 1 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island2.png")).toString();
              case 2 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island3.png")).toString();
              case 3 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island1.png")).toString();
              case 4 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island2.png")).toString();
              case 5 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island3.png")).toString();
              case 6 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island1.png")).toString();
              case 7 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island2.png")).toString();
              case 8 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island3.png")).toString();
              case 9 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island1.png")).toString();
              case 10 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island2.png")).toString();
              case 11 -> resource = Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/island3.png")).toString();
              default -> {
            }
            }

            if(resource!=null){
            Image islandImage = new Image(resource);

            ImageView img = new ImageView(islandImage);
            setGraphic(img);
            img.fitHeightProperty().bind(heightProperty());
            img.fitWidthProperty().bind(widthProperty());

            islandLayout.getChildren().add(islandButton);
        }

        Button changeScene = new Button("Back to SchoolBoard");
        changeScene.setOnAction (e-> stage.setScene(scene));

        Vbox vbox = new VBox();
        vbox.getChildren().add(islandLayout, changeScene);


        Scene islandScene = new Scene(vBox);



         */



    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void viewMessage(String message) {

    }

    @Override
    public void updateGameView(ClientGame game, UpdateFlag flag) {
       /*
        for(SchoolBoardContainer sc : schools){
            sc.updateGame(game);
        }

        */
    }
}