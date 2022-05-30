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

    private Client client;
    private Scene tryAgainLater, tryAnother;
    private Scene schoolBoardScene, islandScene, pickMageScene;
    private SchoolBoardView mySchools;
    private Scene initialScene, nickInputScene, gameSettingsScene, waitingQueueScene, gameIsFullScene;
    private Stage primary;
    private IslandView islandView;
    boolean firstTime = true;
    boolean afterFirstUpdate = false;

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
    public void updateGameView(ClientGame game, UpdateFlag flag) {

        switch (flag.getFlag()){
            case FULLGAME -> Platform.runLater(this::refreshAll);
            case SCHOOL -> Platform.runLater(this::refreshAll);
            default -> Platform.runLater(this::refreshAll);
        }

    }

    public void refreshAll(){
        if(afterFirstUpdate) {
            mySchools.refresh();
            islandView.refresh();

        }else{
            afterFirstUpdate = true;
        }
    }

    @Override
    public void viewControlMessages(ArrayList<ControlMessages> msg) {
        for(ControlMessages message: msg) {
            switch (message) {
                case REQUESTINGNICK: {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> primary.setScene(nickInputScene));
                    firstTime = false;
                    break;
                }
                case INSERTMODE: {
                    Platform.runLater(() -> primary.setScene(gameSettingsScene));
                    break;
                }
                case GAMEISBEINGCREATED: {
                    if (firstTime) {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                            firstTime = false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Platform.runLater(() -> primary.setScene(tryAgainLater));
                    break;
                }
                case RETRY: {
                    Platform.runLater(() -> primary.setScene(tryAnother));
                    break;
                }
                case ASSIGNEDNICK: {
                    System.out.println("Your nick has been set");
                    break;
                }
                case WAITINGFORPLAYERS: {
                    Platform.runLater(() -> primary.setScene(waitingQueueScene));
                    break;
                }
                case SELECTMAGE: {
                    System.out.println("In select mage");
                    Platform.runLater(() -> {
                        setPickMageScene();
                        primary.setScene(pickMageScene);
                    });
                    break;
                }
                case MATCHMAKINGCOMPLETE: {
                    //SchoolBoard setup logic
                    Platform.runLater(()-> {
                                setSchoolScene();
                            });


                    System.out.println("Starting match");
                    break;
                }
                case GAMEISFULL: {
                    Platform.runLater(() -> primary.setScene(gameIsFullScene));
                    break;
                }
                case PLAYASSISTANT:{
                    Platform.runLater(()->new AssistantSelectionWindow().displayScene(client));
                }

                default:
                    System.out.println(message.getMessage());
                    break;
            }
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

    private void setIslandScene(){
        islandView = new IslandView(client);
        islandView.getSwitcher().setOnAction(e->{
            Platform.runLater(()-> {
                primary.setScene(schoolBoardScene);
            });
        });
        islandScene = new Scene(islandView, 800, 600);
    }

    private void setSchoolScene(){
        setIslandScene();
        SchoolBoardView schools = new SchoolBoardView(client);
        mySchools = schools;
        HBox box = new HBox();
        GameStateView state = new GameStateView();

        state.getToIslands().setOnAction(e->{
            Platform.runLater(()-> {
                primary.setScene(islandScene);
            });
        });
        box.getChildren().addAll(schools, state);
        state.prefWidthProperty().bind(box.widthProperty().multiply(0.2));
        schools.prefWidthProperty().bind(box.widthProperty().multiply(0.8));
        HBox.setHgrow(state, Priority.NEVER);
        HBox.setHgrow(schools, Priority.NEVER);
        schoolBoardScene = new Scene(box);
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
        pickMageScene = new Scene(new MageSelectionPane(client, this), 400, 300);
    }

    public void enterGameScene(){
        primary.setScene(schoolBoardScene);
    }

}