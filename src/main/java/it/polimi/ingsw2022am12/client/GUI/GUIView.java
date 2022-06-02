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
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

public class GUIView implements View, Runnable{

    private final Client client;
    private Scene tryAgainLater, tryAnother;
    private Scene schoolBoardScene, islandScene, pickMageScene;
    private GameStateView gameStateView;
    private SchoolBoardView mySchools;
    private Scene activeScene;
    private Scene nickInputScene, gameSettingsScene, waitingQueueScene, gameIsFullScene, matchIsStartingScene, endMatchScene, serverDownScene;
    private Stage primary;
    private IslandView islandView;
    boolean afterFirstUpdate = false;

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
        nickInputScene = new Scene(new NickInputPane(client), 400, 300);
        gameSettingsScene = new Scene(new GameSettingsPane(client), 400, 300);
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
            switch (message) {
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
                case DISCONNECTION:{
                    Platform.runLater(()-> {
                        primary.setScene(disconnectionScene);
                        client.disconnected();
                    });
                    break;
                }
                case SERVERUNREACHABLE:{
                    Platform.runLater(()->{
                        primary.setScene(serverDownScene);
                    });
                    break;
                }
                default:
                    Platform.runLater(()-> gameStateView.addMessage(message.getMessage()));
                    break;
            }
        }
    }



    private void setTryAgain(){
        VBox pane = new VBox();
        Label tryAnother = new Label("The game is being decided, wait");
        pane.getChildren().addAll(tryAnother);
        pane.setAlignment(Pos.CENTER);
        tryAgainLater = new Scene(pane, 400, 300);
    }

    private void setIslandScene(){
        islandView = new IslandView(client);
        islandView.getSwitcher().setOnAction(e-> Platform.runLater(()-> primary.setScene(schoolBoardScene)));
        islandScene = new Scene(islandView, 600, 400);
    }

    private void setMatchIsStartingScene(){
        VBox pane = new VBox();
        Label matchIsStarting = new Label("Your match is starting, wait for your turn");
        pane.getChildren().addAll(matchIsStarting);
        pane.setAlignment(Pos.CENTER);
        matchIsStartingScene = new Scene(pane, 400, 300);
    }

    private void setDisconnectionScene(){
        VBox pane = new VBox();
        Label disconnection = new Label(message.getMessage());
        pane.getChildren().addAll(disconnection);
        pane.setAlignment(Pos.CENTER);
        endMatchScene = new Scene(pane, 400, 300);
        primary.setScene(endMatchScene);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void setSchoolScene(){
        setIslandScene();
        SchoolBoardView schools = new SchoolBoardView(client);
        mySchools = schools;
        HBox box = new HBox();

        gameStateView.getToIslands().setOnAction(e-> Platform.runLater(()->
            primary.setScene(islandScene)
        ));
        box.getChildren().addAll(schools, gameStateView);
        gameStateView.prefWidthProperty().bind(box.widthProperty().multiply(0.2));
        schools.prefWidthProperty().bind(box.widthProperty().multiply(0.8));
        HBox.setHgrow(gameStateView, Priority.NEVER);
        HBox.setHgrow(schools, Priority.NEVER);
        schoolBoardScene = new Scene(box, 600, 400);
    }

    private void setGameStateView(){
        gameStateView = new GameStateView();
    }

    private void setTryAnother(){
        VBox pane = new VBox();
        Label tryAnotherLabel = new Label("The name you picked was already taken");
        Button close = new Button("Got it");
        close.setOnAction(e->Platform.runLater(()-> primary.setScene(nickInputScene)));
        pane.getChildren().addAll(tryAnotherLabel, close);
        pane.setAlignment(Pos.CENTER);
        tryAnother = new Scene(pane, 400, 300);
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
        pickMageScene = new Scene(new MageSelectionPane(client, this), 400, 300);
    }

    public void enterGameScene(){
        primary.setScene(schoolBoardScene);
    }

    public void setServerDownScene(){
        VBox pane = new VBox();
        Label serverDownLabel = new Label("The server you tried to connect to is unreachable");
        Button close = new Button("Reconnect");
        close.setOnAction(e->{
            Platform.runLater(client::connect);
            serverDownLabel.setText("The server is still unreachable, try again later");
        });
        pane.getChildren().addAll(serverDownLabel, close);
        pane.setAlignment(Pos.CENTER);
        serverDownScene = new Scene(pane, 400, 300);
    }

    @Override
    public void connectionFailedPrompt(){
        Platform.runLater(()->primary.setScene(serverDownScene));
    }
}