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

import static javafx.scene.paint.Color.rgb;

/**
 * class that creates the window that will display all the scenes necessary to play the game using a Graphic Interface
 */
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
     * Constructor method for GUIView
     *
     * @param client the client that will interact with this View
     */
    public GUIView(Client client){
        this.client = client;
    }

    /**
     * run method creates the primary window of the game, and runs all the necessary methods to initiate the match, or to
     * handle every possible problem which may occur (Server going down, match already full...)
     */
    @Override
    public void run() {
        primary = new Stage();
        //Loading Eryantis image
        initialSceneImage = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/EryantisImage.png")).toString());
        setNickInputScene();

        //Stage settings
        primary.setTitle("Eryantis");
        primary.getIcons().add(initialSceneImage);
        primary.setResizable(true);
        primary.setScene(nickInputScene);
        primary.show();

        //Setting all scenes
        setGameSettingsScene();
        setTryAgain();
        setTryAnother();
        setWaitingQueueScene();
        setGameIsFullScene();
        setMatchIsStartingScene();
        setServerDownScene();
        setGameStateView();
    }

    /**
     * method that updates the GUI View based on the current state of the game
     *
     * @param game the current state of the game
     * @param flag an update flag that signals the main changes
     */
    @Override
    public void updateGameView(ClientGame game, UpdateFlag flag) {
        switch (flag.getFlag()){
            case FULLGAME, CHARACTERS, CLOUDS -> Platform.runLater(this::refreshAll);
            case SCHOOL -> Platform.runLater(this::schoolsRefresh);
            case ISLANDS -> Platform.runLater(this::islandsRefresh);
        }
    }

    /**
     * method that resets the graphical assets in the scene that displays the islands according to the current state of
     * the game
     */
    private void islandsRefresh(){
        if(afterFirstUpdate) {
            islandView.refresh();
            gameStateView.refresh(client);
        }else{
            afterFirstUpdate = true;
        }
    }

    /**
     * method that resets the graphical assets in the scene that displays the schoolBoards according to the current state
     * of the game
     */
    private void schoolsRefresh(){
        if(afterFirstUpdate) {
            mySchools.refresh();
            gameStateView.refresh(client);
        }else{
            afterFirstUpdate = true;
        }
    }

    /**
     * method that resets the graphical assets in both the scene that displays the schoolBoards, and the scene that
     * displays the islands, according to the current state of the game
     */
    private void refreshAll(){
        if(afterFirstUpdate) {
            mySchools.refresh();
            islandView.refresh();
            gameStateView.refresh(client);
        }else{
            afterFirstUpdate = true;
        }
    }

    /**
     * viewControlMessages method sets a different scene for every message contained in the list of ControlMessages
     * @param msg the list of ControlMessages
     */
    @Override
    public void viewControlMessages(ArrayList<ControlMessages> msg) {
        for(ControlMessages message: msg) {
            switch (message) {
                case REQUESTINGNICK -> Platform.runLater(() -> primary.setScene(nickInputScene));
                case INSERTMODE -> Platform.runLater(() -> primary.setScene(gameSettingsScene));
                case GAMEISBEINGCREATED -> Platform.runLater(() -> primary.setScene(tryAgainLater));
                case RETRY -> Platform.runLater(() -> primary.setScene(tryAnother));
                case ASSIGNEDNICK -> {
                }
                case WAITINGFORPLAYERS -> Platform.runLater(() -> primary.setScene(waitingQueueScene));
                case SELECTMAGE ->
                    Platform.runLater(() -> {
                        setPickMageScene();
                        primary.setScene(pickMageScene);
                    });
                case MATCHMAKINGCOMPLETE ->
                    Platform.runLater(() -> {
                        setSchoolScene();
                        setIslandScene();
                        gameStateView.refresh(client);
                        activeViewContent.getChildren().add(mySchools);
                        primary.setScene(matchIsStartingScene);
                    });
                case GAMEISFULL -> Platform.runLater(() -> primary.setScene(gameIsFullScene));
                case PLAYASSISTANT -> Platform.runLater(() -> new AssistantSelectionWindow().displayScene(client));
                case DISCONNECTION, WINNER, LOSER ->
                    Platform.runLater(() -> {
                        setServerDownScene();
                        setEndMatchScene(message);
                        afterFirstUpdate = false;
                    });
                case SERVERUNREACHABLE ->
                    Platform.runLater(() ->
                            primary.setScene(serverDownScene)
                    );
                case ACCEPTED, INVALIDSELECTION, ACTIONCOMPLETED ->
                    Platform.runLater(() -> {
                        gameStateView.clearMessages();
                        gameStateView.addMessage(message.getMessage());
                    });
                default -> Platform.runLater(() -> {
                    gameStateView.addMessage(message.getMessage());
                    primary.setScene(activeScene);
                });
            }
        }
    }

    /**
     * method that sets the scene in which the player must input his name on the stage at the beginning of the game
     */
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

    /**
     * method that sets the scene in which the player must input the number of players and mode on the stage at the beginning of the game
     */
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

    /**
     * method that sets the scene that announces to the player that he must wait for the game to be set
     */
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

    /**
     * setIslandScene shows the scene that represents the part of the table with the islands, clouds and characters
     */
    private void setIslandScene(){
        activeViewContent.getChildren().remove(islandView);
        islandView = new IslandView(client);
        islandView.prefWidthProperty().bind(activeViewContent.widthProperty().multiply(0.8));
        HBox.setHgrow(islandView, Priority.NEVER);
    }

    /**
     * method that sets the scene that announces to the player that the game is starting, and he must wait for his turn
     */
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

    /**
     * method that sets the scene that announces to the player that the game has ended
     */
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

    /**
     * switchScene method is used to switch from the IslandView scene and the SchoolBoardView scene
     */
    public void switchScene(){
        Node active = activeViewContent.getChildren().remove(1);
        if(active==mySchools){
            activeViewContent.getChildren().add(islandView);

        }else if(active==islandView){
            activeViewContent.getChildren().add(mySchools);
        }
    }

    /**
     * setIslandScene shows the scene that represents the part of the table with the schoolBoards
     */
    private void setSchoolScene(){
        activeViewContent.getChildren().remove(mySchools);
        mySchools = new SchoolBoardView(client);
        mySchools.prefWidthProperty().bind(activeViewContent.widthProperty().multiply(0.8));
        HBox.setHgrow(mySchools, Priority.NEVER);
    }

    /**
     * setIslandScene shows the scene that represents every useful information about the game (number of the round, number
     * of the turn, messages shown to the player...)
     */
    private void setGameStateView(){
        gameStateView = new GameStateView();
        activeViewContent = new HBox();
        activeViewContent.setBackground(Background.fill(rgb(51,232,189)));
        activeViewContent.getChildren().add(gameStateView);
        gameStateView.prefWidthProperty().bind(activeViewContent.widthProperty().multiply(0.2));
        HBox.setHgrow(gameStateView, Priority.NEVER);
        activeScene = new Scene(activeViewContent, 800,600);
        gameStateView.getSwitcher().setOnAction(e-> Platform.runLater(this::switchScene));
    }

    /**
     * setWaitingQueueScene method creates a new scene in my window, used to signal that the name I chose was already chosen,
     * so I must select another
     */
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

    /**
     * setWaitingQueueScene method creates a new scene in my window, used to signal that there are other players logging in,
     * so I must wait
     */
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

    /**
     * setGameIsFullScene method creates a new scene in my window, used to signal that the current instance of the game
     * is already full
     */
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

    /**
     * setPickMageScene method creates a Mage selection scene in my window
     */
    private void setPickMageScene(){
        pickMageScene = new Scene(new MageSelectionPane(client), 500, 300);
    }

    /**
     * setServerDownScene method creates a new scene in my window, used to signal that the server has gone down, therefore
     * it's not possible to stay connected; it still gives the option to try and reconnect to server using a button
     */
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

    /**
     * method that represents the prompt used to show the serverDownScene
     */
    @Override
    public void connectionFailedPrompt(){
        Platform.runLater(()-> primary.setScene(serverDownScene)
        );
    }

    /**
     * showHelp method allows the player to visualize a guide
     * @param i the index of the help I need
     */
    @Override
    public void showHelp(int i) {

    }
}