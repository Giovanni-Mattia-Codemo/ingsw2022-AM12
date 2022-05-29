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
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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

        Button characterButton = new Button("Activate");
        characterButton.setAlignment(Pos.CENTER);
        characterButton.setMinWidth(1.0);
        characterButton.setMinHeight(1.0);
        characterButton.setMaxHeight(Double.MAX_VALUE);
        characterButton.setMaxWidth(Double.MAX_VALUE);
        characterButton.setBackground(Background.fill(Color.WHEAT));
        characterButton.setOnAction(e -> ClientInputHandler.handle("Character "+characterName, client));

        Image characterImg = null;
        switch (characterName){
            case "CHARACTER_MONK" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front.jpg")).toString());
            case "CHARACTER_BEGGAR" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front3.jpg")).toString());
            case "CHARACTER_PRINCESS" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front10.jpg")).toString());
            case "CHARACTER_HERBALIST" -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front4.jpg")).toString());
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
        characterImgView.setPreserveRatio(true);
        characterImgView.fitWidthProperty().bind(this.widthProperty());
        characterImgView.fitHeightProperty().bind(this.heightProperty());

        getChildren().add(characterImgView);

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
        coin.prefHeightProperty().bind(coin.widthProperty());
        coin.prefWidthProperty().bind(grid.widthProperty().divide(5));
        grid.add(coin, 0, 0);

        characterButton.prefHeightProperty().bind(characterButton.widthProperty());
        characterButton.prefWidthProperty().bind(grid.widthProperty().divide(5));
        grid.add(characterButton, 1, 0);

        if(client.getClientGame().getCharacterByName(characterName).isHasCoin()){
            coin.setVisible(true);
        }

        int numOfNoEntries = client.getClientGame().getCharacterByName(characterName).getNumberOfNoEntries();
        if(numOfNoEntries!=0){
            for(int i=0; i<numOfNoEntries; i++){
                NoEntryImage noEntry = new NoEntryImage();
                grid.add(noEntry, i%2, 1+i/2);
                noEntry.prefWidthProperty().bind(grid.widthProperty().divide(5));
                noEntryImages.add(noEntry);
            }
        }

        if(client.getClientGame().getCharacterByName(characterName).getStudents()!=null){
            int numOfStudents = client.getClientGame().getCharacterByName(characterName).getStudents().getStudents().size();
            for(int i=0; i<numOfStudents; i++){
                ClientStudent student = client.getClientGame().getCharacterByName(characterName).getStudents().getStudents().get(i);
                StudentButton studentButton = new StudentButton(student, client);
                grid.add(studentButton, i%2, 1+i/2);
                studentButton.prefHeightProperty().bind(studentButton.widthProperty());
                studentButton.prefWidthProperty().bind(grid.widthProperty().divide(5));
                students.add(studentButton);
            }

        }

        getChildren().add(grid);
        grid.prefHeightProperty().bind(this.heightProperty());
        grid.prefWidthProperty().bind(this.widthProperty());
        grid.setAlignment(Pos.CENTER);

        setAlignment(grid, Pos.CENTER);
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
                if(getNodeByCoordinate((j/2)+1, j%2)==null){
                    ClientStudent std = newStudents.get(newStudents.size()-i-1);
                    StudentButton stdButton = new StudentButton(std, client);
                    grid.add(stdButton, j%2,(j/2)+1);
                }
            }
        }

    }

    private void fillNoEntries(int noEntries){
        int difference = noEntries - noEntryImages.size();
        if(difference>0){
            for(int i=0; i<difference; i++){
                for(int j=0; j<4; j++){
                    if(getNodeByCoordinate((j/2)+1, j%2)==null){
                        NoEntryImage noEntry = new NoEntryImage();
                        grid.add(noEntry, j%2,(j/2)+1);
                        noEntryImages.add(noEntry);
                    }
                }
            }
        }
    }



    Node getNodeByCoordinate(int row, int column) {
        for (Node node : grid.getChildren()) {
            if(GridPane.getColumnIndex(node) == row && GridPane.getColumnIndex(node) == column){
                return node;
            }
        }
        return null;
    }
}
