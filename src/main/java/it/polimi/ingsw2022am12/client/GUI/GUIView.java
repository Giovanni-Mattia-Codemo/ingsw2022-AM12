package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.client.View;
import it.polimi.ingsw2022am12.client.model.ClientGame;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GUIView implements View, Runnable{

    private final Client client;
    private Scene tryAgainLater, tryAnother;
    private Scene pickMageScene;
    private HBox activeViewContent;
    private GameStateView gameStateView;
    private SchoolBoardView mySchools;
    private Scene activeScene;
    private Scene nickInputScene, gameSettingsScene, waitingQueueScene, gameIsFullScene, matchIsStartingScene, serverDownScene;
    private Stage primary;
    private IslandView islandView;
    boolean afterFirstUpdate = false;
    private Image initialSceneImage;

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
        initialSceneImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/EryantisImage.png")).toString());
        setNickInputScene();
        primary.setTitle("Eryantis");
        setGameSettingsScene();
        primary.setResizable(true);
        primary.setScene(nickInputScene);
        primary.show();
        setTryAgain();
        setTryAnother();
        setWaitingQueueScene();
        setGameIsFullScene();
        setMatchIsStartingScene();
        setServerDownScene();
        setGameStateView();
    }



    @Override
    public void updateGameView(ClientGame game, UpdateFlag flag) {
        switch (flag.getFlag()){
            case FULLGAME, CHARACTERS, CLOUDS -> Platform.runLater(this::refreshAll);
            case SCHOOL -> Platform.runLater(this::schoolsRefresh);
            case ISLANDS -> Platform.runLater(this::islandsRefresh);
        }
    }

    private void islandsRefresh(){
        if(afterFirstUpdate) {
            islandView.refresh();
            gameStateView.refresh(client);
        }else{
            afterFirstUpdate = true;
        }
    }

    private void schoolsRefresh(){
        if(afterFirstUpdate) {
            mySchools.refresh();
            gameStateView.refresh(client);
        }else{
            afterFirstUpdate = true;
        }
    }

    private void refreshAll(){
        if(afterFirstUpdate) {
            mySchools.refresh();
            islandView.refresh();
            gameStateView.refresh(client);
        }else{
            afterFirstUpdate = true;
        }
    }

    @Override
    public void viewControlMessages(ArrayList<ControlMessages> msg) {
        for(ControlMessages message: msg) {
            switch(message) {
                case REQUESTINGNICK: {
                    Platform.runLater(() -> primary.setScene(nickInputScene));
                    break;
                }
                case INSERTMODE: {
                    Platform.runLater(() -> primary.setScene(gameSettingsScene));
                    break;
                }
                case GAMEISBEINGCREATED: {
                    Platform.runLater(() -> primary.setScene(tryAgainLater));
                    break;
                }
                case RETRY: {
                    Platform.runLater(() -> primary.setScene(tryAnother));
                    break;
                }
                case ASSIGNEDNICK: {
                    break;
                }
                case WAITINGFORPLAYERS: {
                    Platform.runLater(() -> primary.setScene(waitingQueueScene));
                    break;
                }
                case SELECTMAGE: {
                    Platform.runLater(() -> {
                        setPickMageScene();
                        primary.setScene(pickMageScene);
                    });
                    break;
                }
                case MATCHMAKINGCOMPLETE: {
                    Platform.runLater(()->{
                        setSchoolScene();
                        setIslandScene();
                        gameStateView.refresh(client);
                        activeViewContent.getChildren().add(mySchools);
                        primary.setScene(matchIsStartingScene);
                    });
                    break;
                }
                case GAMEISFULL: {
                    Platform.runLater(() -> primary.setScene(gameIsFullScene));
                    break;
                }
                case PLAYASSISTANT:{
                    Platform.runLater(()->new AssistantSelectionWindow().displayScene(client));
                    break;
                }
                case DISCONNECTION,WINNER,LOSER:{
                    Platform.runLater(()->{
                        setEndMatchScene(message);
                        setServerDownScene();
                    });
                    break;
                }
                case SERVERUNREACHABLE:{
                    Platform.runLater(()->
                        primary.setScene(serverDownScene)
                    );
                    break;
                }
                case ACCEPTED, INVALIDSELECTION, ACTIONCOMPLETED:{
                    Platform.runLater(()->{
                        gameStateView.clearMessages();
                        gameStateView.addMessage(message.getMessage());
                    });
                    break;
                }
                default:
                    Platform.runLater(()-> {
                        gameStateView.addMessage(message.getMessage());
                        primary.setScene(activeScene);
                    });
                    break;
            }
        }
    }

    private void setNickInputScene(){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);
        VBox nickInput = new NickInputPane(client);
        nickInput.prefWidthProperty().bind(pane.widthProperty());
        nickInput.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(nickInput);
        nickInputScene = new Scene(pane, 600, 450);
    }

    private void setGameSettingsScene(){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);
        GameSettingsPane container = new GameSettingsPane(client);
        container.prefWidthProperty().bind(pane.widthProperty());
        container.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(container);
        gameSettingsScene = new Scene(pane, 600, 450);

    }

    private void setTryAgain(){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);
        VBox container = new VBox();
        Label tryAnother = new Label("The game is being decided, wait");
        tryAnother.setFont(new Font("Algerian", 25));
        container.getChildren().addAll(tryAnother);
        container.setAlignment(Pos.CENTER);
        container.prefWidthProperty().bind(pane.widthProperty());
        container.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(container);
        tryAgainLater = new Scene(pane, 600, 450);
    }

    private void setIslandScene(){
        activeViewContent.getChildren().remove(islandView);
        islandView = new IslandView(client);
        islandView.prefWidthProperty().bind(activeViewContent.widthProperty().multiply(0.8));
        HBox.setHgrow(islandView, Priority.NEVER);
    }

    private void setMatchIsStartingScene(){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);
        VBox container = new VBox();
        Label matchIsStarting = new Label("Your match is starting, wait for your turn");
        matchIsStarting.setFont(new Font("Algerian", 25));
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(matchIsStarting);
        container.prefWidthProperty().bind(pane.widthProperty());
        container.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(container);
        matchIsStartingScene = new Scene(pane, 600, 450);
    }

    private void setEndMatchScene(ControlMessages message){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);
        VBox container = new VBox();
        Label disconnection = new Label(message.getMessage());
        disconnection.setFont(new Font("Algerian", 25));
        container.getChildren().addAll(disconnection);
        container.setAlignment(Pos.CENTER);
        container.prefWidthProperty().bind(pane.widthProperty());
        container.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(container);
        primary.setScene(new Scene(pane, 600, 450));
        //Wait some time before disconnecting
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void switchScene(){
        Node active = activeViewContent.getChildren().remove(1);
        if(active==mySchools){
            activeViewContent.getChildren().add(islandView);

        }else if(active==islandView){
            activeViewContent.getChildren().add(mySchools);
        }
    }

    private void setSchoolScene(){
        activeViewContent.getChildren().remove(mySchools);
        mySchools = new SchoolBoardView(client);
        mySchools.prefWidthProperty().bind(activeViewContent.widthProperty().multiply(0.8));
        HBox.setHgrow(mySchools, Priority.NEVER);
    }

    private void setGameStateView(){
        gameStateView = new GameStateView();
        activeViewContent = new HBox();
        activeViewContent.getChildren().add(gameStateView);
        gameStateView.prefWidthProperty().bind(activeViewContent.widthProperty().multiply(0.2));
        HBox.setHgrow(gameStateView, Priority.NEVER);
        activeScene = new Scene(activeViewContent, 800,600);
        gameStateView.getSwitcher().setOnAction(e-> Platform.runLater(this::switchScene));

    }

    private void setTryAnother(){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);
        VBox container = new VBox();
        Button close = new Button("RETRY");
        close.setOnAction(e->Platform.runLater(()-> primary.setScene(nickInputScene)));
        Label tryAnotherLabel = new Label("The name you picked was already taken");
        tryAnotherLabel.setFont(new Font("Algerian", 25));
        container.getChildren().addAll(tryAnotherLabel, close);
        container.setAlignment(Pos.CENTER);
        container.prefWidthProperty().bind(pane.widthProperty());
        container.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(container);
        tryAnother = new Scene(pane, 600, 450);
    }

    private void setWaitingQueueScene(){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);
        VBox container = new VBox();
        Label waitingQueueLabel = new Label("Waiting for other players to log in");
        waitingQueueLabel.setFont(new Font("Algerian", 25));
        container.getChildren().addAll(waitingQueueLabel);
        container.setAlignment(Pos.CENTER);
        container.prefWidthProperty().bind(pane.widthProperty());
        container.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(container);
        waitingQueueScene = new Scene(pane, 600, 450);
    }

    private void setGameIsFullScene(){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);
        VBox container = new VBox();
        Label fullGame = new Label("The game you tried to log in is full, try again later");
        fullGame.setFont(new Font("Algerian", 20));
        Button exit = new Button("EXIT");
        exit.setOnAction(e->{
            client.disconnected();
            primary.close();
        });
        container.getChildren().addAll(fullGame, exit);
        container.setAlignment(Pos.CENTER);
        container.prefWidthProperty().bind(pane.widthProperty());
        container.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(container);
        gameIsFullScene = new Scene(pane, 600, 450);
    }

    private void setPickMageScene(){
        pickMageScene = new Scene(new MageSelectionPane(client), 500, 300);
    }

    public void setServerDownScene(){
        StackPane pane = new StackPane();
        ImageView initialSceneImageView = new ImageView(initialSceneImage);
        initialSceneImageView.fitWidthProperty().bind(pane.widthProperty());
        initialSceneImageView.fitHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(initialSceneImageView);

        VBox container = new VBox();
        Label serverDownLabel = new Label("The server you tried to connect to is unreachable");
        serverDownLabel.setFont(new Font("Algerian", 20));
        Button close = new Button("RECONNECT");
        close.setOnAction(e->{
            Platform.runLater(client::connect);
            serverDownLabel.setText("The server is still unreachable, try again later");
        });
        container.getChildren().addAll(serverDownLabel, close);
        container.setAlignment(Pos.CENTER);
        container.prefWidthProperty().bind(pane.widthProperty());
        container.prefHeightProperty().bind(pane.heightProperty());
        pane.getChildren().add(container);
        serverDownScene = new Scene(pane, 600, 450);
    }

    @Override
    public void connectionFailedPrompt(){
        Platform.runLater(()->primary.setScene(serverDownScene));
    }
}