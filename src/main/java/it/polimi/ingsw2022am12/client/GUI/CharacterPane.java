package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.DiskColor;
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
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 * CharacterPane class is the graphic component that represents the layout of the Character card itself
 */
public class CharacterPane extends VBox {

    private final String characterName;
    private final Client client;
    private final GridPane grid;
    private final Button coin;
    private final ArrayList<StudentButton> students;
    private final ArrayList<NoEntryImage> noEntryImages;

    /**
     * Constructor method for CharacterPane class
     *
     * @param name of the character
     * @param client that uses the card
     */
    public CharacterPane(String name, Client client){
        //VBox settings
        super();
        this.characterName = name;
        this.client = client;
        this.students = new ArrayList<>();
        this.noEntryImages = new ArrayList<>();

        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //Character button settings
        Button characterButton = new Button();
        characterButton.setAlignment(Pos.CENTER);
        characterButton.setMinWidth(1.0);
        characterButton.setMinHeight(1.0);
        characterButton.setMaxHeight(Double.MAX_VALUE);
        characterButton.setMaxWidth(Double.MAX_VALUE);
        characterButton.setBackground(Background.EMPTY);
        //Sets characterButton action
        characterButton.setOnAction(e -> ClientInputHandler.handle("Character "+characterName, client));
        //Loading image for character button
        Image characterImg = null;
        switch (CharacterName.valueOf(characterName)){
            case CHARACTER_MONK -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front.jpg")).toString());
            case CHARACTER_BEGGAR -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front3.jpg")).toString());
            case CHARACTER_PRINCESS -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front10.jpg")).toString());
            case CHARACTER_HERBALIST -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front4.jpg")).toString());
            case CHARACTER_HERALD -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front2.jpg")).toString());
            case CHARACTER_BARD -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front9.jpg")).toString());
            case CHARACTER_HAG -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front11.jpg")).toString());
            case CHARACTER_HOST -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front12.jpg")).toString());
            case CHARACTER_CENTAUR -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front5.jpg")).toString());
            case CHARACTER_KNIGHT -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front7.jpg")).toString());
            case CHARACTER_JESTER -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front6.jpg")).toString());
            case CHARACTER_MERCHANT -> characterImg = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_front8.jpg")).toString());
            default -> {}
        }
        ImageView characterImgView = new ImageView(characterImg);
        //Creates stack pane to put image and button of the character and the coin associated to it
        StackPane imgCoinPane = new StackPane();
        imgCoinPane.setMinSize(1.0, 1.0);
        imgCoinPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        imgCoinPane.getChildren().add(characterImgView);
        imgCoinPane.getChildren().add(characterButton);
        //Binding image and button to pane
        characterImgView.fitWidthProperty().bind(imgCoinPane.widthProperty());
        characterImgView.fitHeightProperty().bind(imgCoinPane.heightProperty());
        characterButton.prefHeightProperty().bind(imgCoinPane.heightProperty());
        characterButton.prefWidthProperty().bind(imgCoinPane.widthProperty());
        //Creating the coin button and setting it
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
        coin.maxHeightProperty().bind(imgCoinPane.heightProperty().multiply(0.1));
        coin.maxWidthProperty().bind(coin.heightProperty());
        //Adding coin to pane
        imgCoinPane.getChildren().add(coin);
        //Stack pane settings
        imgCoinPane.setAlignment(Pos.TOP_CENTER);
        imgCoinPane.prefHeightProperty().bind(heightProperty().multiply(0.5));
        imgCoinPane.prefWidthProperty().bind(widthProperty());
        //Shows coin only if the character was used once
        if(client.getClientGame().getCharacterByName(characterName).isHasCoin()){
            coin.setVisible(true);
        }

        //Creating grid pane to put either students or noEntries on the character
        grid = new GridPane();
        grid.setMinSize(1.0, 1.0);
        grid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Insert noEntries if the character has some
        int numOfNoEntries = client.getClientGame().getCharacterByName(characterName).getNumberOfNoEntries();
        if(numOfNoEntries!=0){
            fillNoEntries(numOfNoEntries);
        }
        //Insert students if the character has some
        if(client.getClientGame().getCharacterByName(characterName).getStudents()!=null){
            fillStudents(client.getClientGame().getCharacterByName(characterName).getStudents().getStudents());
        }
        //Grid settings
        grid.prefHeightProperty().bind(heightProperty().multiply(0.5));
        grid.prefWidthProperty().bind(this.widthProperty());
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPickOnBounds(false);
        grid.setGridLinesVisible(true);
        //Adding children to CharacterPane
        getChildren().add(imgCoinPane);
        getChildren().add(grid);
        //Special characters that requires box with colors to select
        if(characterName.equals(CharacterName.CHARACTER_MERCHANT.toString())||characterName.equals(CharacterName.CHARACTER_HAG.toString())){
            VBox colors = new VBox();
            colors.setFillWidth(true);
            colors.prefWidthProperty().bind(grid.widthProperty());
            colors.prefHeightProperty().bind(grid.heightProperty());
            colors.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            colors.setMinSize(1.0,1.0);
            for(DiskColor c: DiskColor.values()){
                Button button = newColorSelection(c);
                VBox.setVgrow(button, Priority.ALWAYS);
                colors.getChildren().add(button);
            }
            grid.add(colors, 0 ,0);
        }
        //CharacterPane VBox settings
        VBox.setVgrow(grid, Priority.NEVER);
        VBox.setVgrow(imgCoinPane, Priority.NEVER);
        setFillWidth(false);
    }

    /**
     * refresh resets all the graphics for the objects placed on the character
     */
    public void refresh(){
        //Sets the character coin visible if it has it
        if(client.getClientGame().getCharacterByName(characterName).isHasCoin()){
            coin.setVisible(true);
        }
        //Updates the students on the character
        if(client.getClientGame().getCharacterByName(characterName).getStudents()!=null){
            fillStudents(client.getClientGame().getCharacterByName(characterName).getStudents().getStudents());
        }
        //Updates students on the card (if there are some)
        for(StudentButton s:students){
            s.refresh();
        }
        //Updates the noEntries on the card
        fillNoEntries(client.getClientGame().getCharacterByName(characterName).getNumberOfNoEntries());
        //Sets all the noEntries to not visible
        for(NoEntryImage noEntry : noEntryImages){
            noEntry.setVisible(false);
        }
        //Sets the right noEntries to visible
        for(int i=0; i<client.getClientGame().getCharacterByName(characterName).getNumberOfNoEntries(); i++){
            noEntryImages.get(i).setVisible(true);
        }
    }

    /**
     * fillStudents fills with students the cells of the character's grid that are reserved for the students
     *
     * @param newStudents updated
     */
    private void fillStudents(ArrayList<ClientStudent> newStudents){
        int difference = newStudents.size()-students.size();
        for(int i=0; i<difference; i++){
            for(int j=0; j<6; j++){
                if(getNodeByCoordinate((j/2), j%2)==null){
                    if(grid.getRowConstraints().size()<=j/2){
                        RowConstraints rc = new RowConstraints();
                        rc.setVgrow(Priority.NEVER);
                        rc.setFillHeight(true);
                        rc.prefHeightProperty().bind(grid.widthProperty().divide(2));
                        grid.getRowConstraints().add(rc);
                    }
                    if(grid.getColumnConstraints().size()<=j%2){
                        ColumnConstraints cc = new ColumnConstraints();
                        cc.setHgrow(Priority.NEVER);
                        cc.setFillWidth(true);
                        cc.prefWidthProperty().bind(grid.widthProperty().divide(2));
                        grid.getColumnConstraints().add(cc);
                    }
                    ClientStudent std = newStudents.get(newStudents.size()-i-1);
                    StudentButton stdButton = new StudentButton(std, client);
                    stdButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    stdButton.setMinSize(1.0, 1.0);
                    grid.add(stdButton, j%2,(j/2));
                    students.add(stdButton);
                    break;
                }
            }
        }
    }

    /**
     * fillNoEntries fills with noEntries the cells of the character's grid that are reserved for the noEntries
     *
     * @param noEntries number of noEntries needed
     */
    private void fillNoEntries(int noEntries){
        int difference = noEntries - noEntryImages.size();
        if(difference>0){
            for(int i=0; i<difference; i++){
                for(int j=0; j<4; j++){
                    if(getNodeByCoordinate((j/2), j%2)==null){
                        if(grid.getRowConstraints().size()<=j/2){
                            RowConstraints rc = new RowConstraints();
                            rc.setVgrow(Priority.NEVER);
                            rc.setFillHeight(true);
                            rc.prefHeightProperty().bind(grid.widthProperty().divide(2));
                            grid.getRowConstraints().add(rc);
                        }
                        if(grid.getColumnConstraints().size()<=j%2){
                            ColumnConstraints cc = new ColumnConstraints();
                            cc.setHgrow(Priority.NEVER);
                            cc.setFillWidth(true);
                            cc.prefWidthProperty().bind(grid.widthProperty().divide(2));
                            grid.getColumnConstraints().add(cc);
                        }
                        NoEntryImage noEntry = new NoEntryImage();
                        grid.add(noEntry, j%2,(j/2));
                        noEntryImages.add(noEntry);
                        break;
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


    /**
     * newColorSelection creates a colored button based on the new color I selected
     *
     * @param d the color I want to select
     * @return the new button
     */
    private Button newColorSelection(DiskColor d){
        Button colorSel = new Button();
        switch (d){
            case GREEN -> colorSel.setBackground(Background.fill(Color.GREEN));
            case BLUE -> colorSel.setBackground(Background.fill(Color.BLUE));
            case PINK -> colorSel.setBackground(Background.fill(Color.PINK));
            case YELLOW -> colorSel.setBackground(Background.fill(Color.YELLOW));
            case RED -> colorSel.setBackground(Background.fill(Color.RED));
        }
        colorSel.setOnAction(e->{
            System.out.println(d.name());
            ClientInputHandler.handle("Color "+d.name(), client);
        });
        colorSel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        colorSel.setMinSize(1.0,1.0);
        return colorSel;
    }
}
