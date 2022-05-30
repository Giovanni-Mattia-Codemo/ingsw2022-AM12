package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.ClientInputHandler;
import it.polimi.ingsw2022am12.client.model.ClientStudent;
import it.polimi.ingsw2022am12.client.model.NoEntryImage;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.Objects;

public class CharacterPane extends StackPane {

    private final String characterName;
    private final Client client;
    private final GridPane grid;
    private final Button coin;
    private final ArrayList<StudentButton> students;
    private final ArrayList<NoEntryImage> noEntryImages;

    public CharacterPane(String name, Client client){
        super();
        this.characterName = name;
        this.client = client;
        this.students = new ArrayList<>();
        this.noEntryImages = new ArrayList<>();
        grid = new GridPane();
        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Button characterButton = new Button();
        characterButton.setAlignment(Pos.CENTER);
        characterButton.setMinWidth(1.0);
        characterButton.setMinHeight(1.0);
        characterButton.setMaxHeight(Double.MAX_VALUE);
        characterButton.setMaxWidth(Double.MAX_VALUE);
        characterButton.setBackground(Background.EMPTY);
        characterButton.setOnAction(e -> ClientInputHandler.handle("Character "+characterName, client));
        characterButton.prefHeightProperty().bind(characterButton.heightProperty());
        characterButton.prefWidthProperty().bind(characterButton.widthProperty());


        Image characterImg = null;
        switch (characterName){
            case "CHARACTER_MONK" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front.jpg")).toString());
            case "CHARACTER_BEGGAR" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front3.jpg")).toString());
            case "CHARACTER_PRINCESS" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front10.jpg")).toString());
            case "CHARACTER_HERBALIST" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front4.jpg")).toString());
            case "CHARACTER_HERALD" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front2.jpg")).toString());
            case "CHARACTER_BARD" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front9.jpg")).toString());
            case "CHARACTER_HAG" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front11.jpg")).toString());
            case "CHARACTER_HOST" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front12.jpg")).toString());
            case "CHARACTER_CENTAUR" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front5.jpg")).toString());
            case "CHARACTER_KNIGHT" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front7.jpg")).toString());
            case "CHARACTER_JESTER" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front6.jpg")).toString());
            case "CHARACTER_MERCHANT" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front8.jpg")).toString());
            default -> {}
        }
        ImageView characterImgView = new ImageView(characterImg);
        characterImgView.fitWidthProperty().bind(this.widthProperty());
        characterImgView.fitHeightProperty().bind(this.heightProperty());

        getChildren().add(characterImgView);
        getChildren().add(characterButton);
        getChildren().add(anchor);

        grid.setMinSize(1.0, 1.0);
        grid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        coin = new Button();
        ImageView coinImg = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/coin_sprite.png")).toString()));
        coin.setGraphic(coinImg);
        coinImg.fitHeightProperty().bind(coin.heightProperty());
        coinImg.fitWidthProperty().bind(coin.widthProperty());
        coin.setVisible(false);
        coin.setMinWidth(1.0);
        coin.setMinHeight(1.0);
        coin.setMaxHeight(Double.MAX_VALUE);
        coin.setMaxWidth(Double.MAX_VALUE);
        coin.setBackground(Background.EMPTY);
        coin.prefHeightProperty().bind(heightProperty().multiply(0.1));
        coin.prefWidthProperty().bind(coin.heightProperty());
        //grid.add(coin, 0, 0);
        anchor.getChildren().add(coin);
        anchor.setFillWidth(false);
        VBox.setVgrow(coin, Priority.NEVER);
        Pane blank = new Pane();
        blank.setPickOnBounds(false);
        blank.prefHeightProperty().bind(heightProperty().multiply(0.4));
        anchor.getChildren().add(blank);
        VBox.setVgrow(blank, Priority.NEVER);




        if(client.getClientGame().getCharacterByName(characterName).isHasCoin()){
            coin.setVisible(true);
        }

        int numOfNoEntries = client.getClientGame().getCharacterByName(characterName).getNumberOfNoEntries();
        if(numOfNoEntries!=0){
            for(int i=0; i<numOfNoEntries; i++){
                NoEntryImage noEntry = new NoEntryImage();
                grid.add(noEntry, i%2, i/2);
                GridPane.setVgrow(noEntry, Priority.NEVER);
                GridPane.setHgrow(noEntry, Priority.NEVER);
                GridPane.setFillWidth(noEntry, false);
                GridPane.setFillHeight(noEntry, false);
                noEntry.prefWidthProperty().bind(widthProperty().multiply(0.25));
                noEntry.prefHeightProperty().bind(noEntry.widthProperty());
                noEntryImages.add(noEntry);
            }
        }

        if(client.getClientGame().getCharacterByName(characterName).getStudents()!=null){
            int numOfStudents = client.getClientGame().getCharacterByName(characterName).getStudents().getStudents().size();
            for(int i=0; i<numOfStudents; i++){
                ClientStudent student = client.getClientGame().getCharacterByName(characterName).getStudents().getStudents().get(i);
                StudentButton studentButton = new StudentButton(student, client);
                grid.add(studentButton, i%2, i/2);
                GridPane.setVgrow(studentButton, Priority.NEVER);
                GridPane.setHgrow(studentButton, Priority.NEVER);
                GridPane.setFillWidth(studentButton, false);
                GridPane.setFillHeight(studentButton, false);

                studentButton.prefHeightProperty().bind(studentButton.widthProperty());
                studentButton.prefWidthProperty().bind(widthProperty().multiply(0.25));
                students.add(studentButton);
            }

        }

        getChildren().add(grid);
        grid.prefHeightProperty().bind(this.heightProperty());
        grid.prefWidthProperty().bind(this.widthProperty());
        grid.setAlignment(Pos.BOTTOM_CENTER);
        grid.setPickOnBounds(false);
        anchor.prefHeightProperty().bind(heightProperty());
        anchor.prefWidthProperty().bind(widthProperty());
        anchor.setMinSize(1.0,1.0);
        anchor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        anchor.getChildren().add(grid);
        VBox.setVgrow(grid, Priority.NEVER);

        //AnchorPane.setBottomAnchor(grid, 0.0);
        //setAlignment(grid, Pos.BOTTOM_CENTER);
    }

    public void refresh(){
        if(client.getClientGame().getCharacterByName(characterName).isHasCoin()){
            coin.setVisible(true);
        }
        if(client.getClientGame().getCharacterByName(characterName).getStudents()!=null){
            fillStudents(client.getClientGame().getCharacterByName(characterName).getStudents().getStudents());
        }

        for(StudentButton s:students){
            s.refresh();
        }

        fillNoEntries(client.getClientGame().getCharacterByName(characterName).getNumberOfNoEntries());


        for(NoEntryImage noEntry : noEntryImages){
            noEntry.setVisible(false);
        }
        for(int i=0; i<client.getClientGame().getCharacterByName(characterName).getNumberOfNoEntries(); i++){
            noEntryImages.get(i).setVisible(true);
        }

    }

    private void fillStudents(ArrayList<ClientStudent> newStudents){
        int difference = newStudents.size()-students.size();
        for(int i=0; i<difference; i++){
            for(int j=0; j<6; j++){
                if(getNodeByCoordinate((j/2), j%2)==null){
                    ClientStudent std = newStudents.get(newStudents.size()-i-1);
                    StudentButton stdButton = new StudentButton(std, client);
                    GridPane.setVgrow(stdButton, Priority.NEVER);
                    GridPane.setHgrow(stdButton, Priority.NEVER);
                    GridPane.setFillWidth(stdButton, false);
                    GridPane.setFillHeight(stdButton, false);
                    stdButton.prefHeightProperty().bind(stdButton.widthProperty());
                    stdButton.prefWidthProperty().bind(widthProperty().multiply(0.25));
                    grid.add(stdButton, j%2,(j/2));
                }
            }
        }

    }

    private void fillNoEntries(int noEntries){
        int difference = noEntries - noEntryImages.size();
        if(difference>0){
            for(int i=0; i<difference; i++){
                for(int j=0; j<4; j++){
                    if(getNodeByCoordinate((j/2), j%2)==null){
                        NoEntryImage noEntry = new NoEntryImage();
                        grid.add(noEntry, j%2,(j/2));
                        GridPane.setVgrow(noEntry, Priority.NEVER);
                        GridPane.setHgrow(noEntry, Priority.NEVER);
                        GridPane.setFillWidth(noEntry, false);
                        GridPane.setFillHeight(noEntry, false);
                        noEntry.prefWidthProperty().bind(widthProperty().multiply(0.25));
                        noEntry.prefHeightProperty().bind(noEntry.widthProperty());
                        noEntryImages.add(noEntry);
                    }
                }
            }
        }
    }

    /**
     * getNodeByCoordinates iterates on the children of the GridPane, and then returns the node located in the chosen
     * coordinates
     *
     * @param row index of the row of the grid
     * @param column index of the column of the grid
     * @return the Node in those coordinates
     */
    private Node getNodeByCoordinate(int row, int column) {
        for (Node node : grid.getChildren()) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }
}
