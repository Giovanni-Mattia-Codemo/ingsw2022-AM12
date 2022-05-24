package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.client.View;
import it.polimi.ingsw2022am12.client.model.ClientGame;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GUIView implements View, Runnable{

    private ClientGame myGame;
    private Client client;
    private Scene tryAgainLater, tryAnother;
    private Scene schoolBoardScene, pickMageScene;
    private Scene initialScene, nickInputScene, gameSettingsScene, waitingQueueScene, gameIsFullScene;
    private Stage primary;
    private String update;
    boolean firstTime = true;




    private ArrayList<SchoolBoardContainer> schools;

    /**
     * Default constructor
     */
    public GUIView(){

    }

    /**
     * Constructor for setting the game
     *
     *
     */
    public GUIView(Client client){

        this.client = client;
    }


    /*
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("lanimaaa");

        System.out.println(activeScene);

        stage.setScene(activeScene);
        this.stage = stage;
        stage.show();
        System.out.println(activeScene);
        /*
        SchoolBoardView schools = new SchoolBoardView(myGame);
        HBox box = new HBox();
        GameStateView state = new GameStateView();
        /*
              Button checkIsland = new Button("Go to Islands");
              checkIsland.SetOnAction(e-> stage.setScene(islandScene);

        box.getChildren().addAll(schools, state);

        Scene scene = new Scene(box);
        stage.setScene(scene);

        stage.setResizable(true);
        stage.show();



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

    }
*/


    @Override
    public void run() {

        primary = new Stage();
        primary.setTitle("Eryantis");
        setInitialScene();
        nickInputScene = new Scene(new NickInputPane(client), 400, 300);
        gameSettingsScene = new Scene(new GameSettingsPane(client), 400, 300);
        primary.setResizable(true);
        primary.setScene(initialScene);
        primary.show();
        setTryAgain();
        setTryAnother();
        setWaitingQueueScene();
        setGameIsFullScene();

    }

    @Override
    public void viewMessage(String message) {
            switch (message) {
                case "Insert a Nick to enter the game": {
                    try{
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Platform.runLater(()->primary.setScene(nickInputScene));
                    firstTime = false;
                    break;
                }
                case "Insert the number of players and mode of the match: number of players between 2 and 4, followed by either true or false to be in the expert mode or not" :{
                    Platform.runLater(()->primary.setScene(gameSettingsScene));
                    break;
                }
                case "Game format is being decided, wait":{
                    if(firstTime){
                        try{
                            TimeUnit.SECONDS.sleep(3);
                            firstTime = false;
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(()->primary.setScene(tryAgainLater));
                    break;
                }
                case "Try another": {
                    Platform.runLater(()->primary.setScene(tryAnother));
                    break;
                }
                case "Your nick has been set": {
                    System.out.println("Your nick has been set");
                    break;
                }
                case "Player connected, waiting for more":{
                    Platform.runLater(()->primary.setScene(waitingQueueScene));
                    break;
                }
                case "Select a mage":{
                    System.out.println("In select mage");
                    Platform.runLater(()->{
                        setPickMageScene();
                        primary.setScene(pickMageScene);});
                    break;
                }
                case "Your match is starting":{
                    //SchoolBoard setup logic
                    System.out.println("Starting match");
                    break;
                }
                case "Game is full":{
                    Platform.runLater(()->primary.setScene(gameIsFullScene));
                    break;
                }
                default:
                    System.out.println(message);
                    break;
            }
    }



    @Override
    public void updateGameView(ClientGame game, UpdateFlag flag) {
       if(myGame == null){
           myGame = game;
       }else{

       }
    }

    @Override
    public void viewControlMessages(ArrayList<ControlMessages> msg) {

    }

    private void handleUpdates(){
        if(update!=null){
            System.out.println("my update:"+ update);
            update = null;
            primary.setScene(schoolBoardScene);
        }
    }

    private void setTryAgain(){
        VBox pane = new VBox();
        Label tryAnother = new Label("The game is being decided, wait");
        Button close = new Button("Got it");
        close.setOnAction(e->{
            primary.setScene(nickInputScene);
        });
        pane.getChildren().addAll(tryAnother, close);
        pane.setAlignment(Pos.CENTER);
        tryAgainLater = new Scene(pane, 400, 300);
    }

    private void setTryAnother(){
        VBox pane = new VBox();
        Label tryAnotherLabel = new Label("The name you picked was already taken");
        Button close = new Button("Got it");
        close.setOnAction(e->{
            primary.setScene(nickInputScene);
        });
        pane.getChildren().addAll(tryAnotherLabel, close);
        pane.setAlignment(Pos.CENTER);
        tryAnother = new Scene(pane, 400, 300);
    }

    private void setInitialScene(){
        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false,false,true,true);
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/LOGO CRANIO CREATIONS_bianco.png")).toString());
        Background background = new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
        GridPane pane = new GridPane();

        pane.setBackground(background);
        double h = image.getHeight()/2;
        double w = image.getWidth()/2;

        GridPane.setFillHeight(pane, true);
        GridPane.setFillWidth(pane, true);
        pane.setMinHeight(1);
        pane.setMinWidth(1);
        pane.setPrefSize(w, h);
        pane.setMaxWidth(Double.POSITIVE_INFINITY);
        pane.setMaxHeight(Double.POSITIVE_INFINITY);
        pane.setAlignment(Pos.CENTER_LEFT);
        initialScene = new Scene(pane);
    }

    private void setWaitingQueueScene(){
        VBox pane = new VBox();
        Label waitingQueueLabel = new Label("Waiting for other players to log in");
        waitingQueueLabel.setAlignment(Pos.CENTER);
        pane.getChildren().add(waitingQueueLabel);
        pane.setAlignment(Pos.CENTER);
        waitingQueueScene = new Scene(pane, 400, 300);
    }

    private void setGameIsFullScene(){
        VBox pane = new VBox();
        Label fullGame = new Label("The game you tried to log in is full, try again later");
        fullGame.setAlignment(Pos.CENTER);
        Button exit = new Button("EXIT");
        exit.setOnAction(e->{
            client.disconnected();
            primary.close();
        });
        pane.getChildren().addAll(fullGame, exit);
        pane.setAlignment(Pos.CENTER);
        gameIsFullScene = new Scene(pane, 400, 300);
    }

    private void setPickMageScene(){
        System.out.println("In pick mage");
        pickMageScene = new Scene(new MageSelectionPane(client), 400, 300);
    }

}