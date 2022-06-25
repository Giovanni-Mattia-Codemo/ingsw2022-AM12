package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Objects;

/**
 * IslandListPane is the graphical component that represents the area around my schoolBoard (the schoolBoard itself, the tag with
 * the name of the player, the last played assistant and the assistants, deck...)
 *
 */
public class SchoolBoardContainer extends GridPane {

    private static HashMap<Integer, Image> assistantImages;
    private ImageView assistantView;
    private final String nick;
    private final Client client;
    private final Button deck;
    private final SchoolBoardPane school;
    private final Button lastPlayedAssistant;
    boolean mageIsUpToDate = false;

    /**
     * Constructor method of SchoolBoardContainer
     *
     * @param nick of the player
     * @param client that must interact with the schoolBoard
     */
    public SchoolBoardContainer(String nick, Client client){
        super();
        this.client = client;
        this.nick = nick;
        this.setBackground(Background.fill(rgb(51,232,189)));
        double schoolRatio = 0.4337708831;
        //super grid constraints
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.NEVER);
        cc.setFillWidth(false);
        cc.prefWidthProperty().bind(Bindings.min(this.widthProperty().multiply(0.7),this.heightProperty().divide(schoolRatio)));
        this.getColumnConstraints().add(cc);

        cc = new ColumnConstraints();
        cc.setHgrow(Priority.NEVER);
        cc.setFillWidth(true);
        cc.setPercentWidth(30);
        this.getColumnConstraints().add(cc);

        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.NEVER);
        rc.setFillHeight(true);
        rc.prefHeightProperty().bind(Bindings.min(this.widthProperty().multiply(0.7).multiply(schoolRatio), this.heightProperty()));
        this.getRowConstraints().add(rc);

        school = new SchoolBoardPane(nick, client);
        addColumn(0,school);


        GridPane assistantPanel = new GridPane();

        RowConstraints temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);

        assistantPanel.getRowConstraints().add(temp1);


        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.NEVER);
        temp1.setFillHeight(true);
        temp1.prefHeightProperty().bind(Bindings.min(this.heightProperty().multiply(0.3), this.widthProperty().multiply(0.33).multiply(1.44)));
        assistantPanel.getRowConstraints().add(temp1);


        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.NEVER);
        temp1.setFillHeight(true);
        temp1.prefHeightProperty().bind(Bindings.min(this.heightProperty().multiply(0.3), this.widthProperty().multiply(0.33).multiply(1.44)));

        assistantPanel.getRowConstraints().add(temp1);

        temp1 = new RowConstraints();
        temp1.setVgrow(Priority.ALWAYS);
        temp1.setFillHeight(true);

        assistantPanel.getRowConstraints().add(temp1);

        ColumnConstraints temp = new ColumnConstraints();
        temp.setHgrow(Priority.ALWAYS);
        temp.setFillWidth(true);

        assistantPanel.getColumnConstraints().add(temp);

        temp = new ColumnConstraints();
        temp.setHgrow(Priority.NEVER);
        temp.setFillWidth(true);
        temp.prefWidthProperty().bind(Bindings.min(this.widthProperty().multiply(0.33), this.heightProperty().multiply(0.3).divide(1.44)));
        assistantPanel.getColumnConstraints().add(temp);

        temp = new ColumnConstraints();
        temp.setHgrow(Priority.ALWAYS);
        temp.setFillWidth(true);

        assistantPanel.getColumnConstraints().add(temp);


        lastPlayedAssistant = new Button();
        lastPlayedAssistant.setVisible(false);
        lastPlayedAssistant.setMinSize(1.0, 1.0);
        lastPlayedAssistant.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        updatePlayedAssistant();
        assistantPanel.add(lastPlayedAssistant, 1, 1);


        assistantPanel.vgapProperty().bind(assistantPanel.heightProperty().multiply(0.05));



        deck = new Button();

        deck.setMinSize(1.0,1.0);
        deck.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        updateMage();
        assistantPanel.add(deck, 1,2);


        addColumn(1, assistantPanel);

        school.prefWidthProperty().bind(Bindings.min(this.widthProperty(),this.heightProperty().divide(schoolRatio)));

        school.prefHeightProperty().bind(Bindings.min(this.widthProperty().multiply(schoolRatio),this.heightProperty()));
    }

    public void refresh() {
        school.refresh();
        updatePlayedAssistant();
        if(!mageIsUpToDate){
            updateMage();
        }
    }

    /**
     * updateMage method updates the graphics of the mage according to the selection of the player
     */
    private void updateMage(){

        Image back;
        int mageNumber = client.getClientGame().getSchoolBoardByNick(nick).getMage();
        switch (mageNumber){
            case 0->back = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_back_1@3x.png")).toString());
            case 1->back = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_back_11@3x.png")).toString());
            case 2->back = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_back_21@3x.png")).toString());
            case 3->back = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/CarteTOT_back_31@3x.png")).toString());
            default -> {return;}
        }
        ImageView backView = new ImageView(back);
        backView.fitHeightProperty().bind(deck.heightProperty());
        backView.fitWidthProperty().bind(deck.widthProperty());
        deck.setGraphic(backView);
        mageIsUpToDate = true;
    }

    /**
     * updatePlayedAssistant method updates the graphics of the lastPlayedAssistant according to the current state of the game
     */
    private void updatePlayedAssistant(){
        Image assistant = null;

        int assistantNumber = client.getClientGame().getSchoolBoardByNick(nick).getPlayedAssistant();

        if(assistantImages==null) {
            assistantImages = new HashMap<>();
            for (int i= 1; i<=10;i++){

                switch (i) {
                    case 1 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (1).png")).toString());
                    case 2 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (2).png")).toString());
                    case 3 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (3).png")).toString());
                    case 4 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (4).png")).toString());
                    case 5 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (5).png")).toString());
                    case 6 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (6).png")).toString());
                    case 7 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (7).png")).toString());
                    case 8 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (8).png")).toString());
                    case 9 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (9).png")).toString());
                    case 10 -> assistant = new Image(Objects.requireNonNull(getClass().getResource("/it/polimi/ingsw2022am12/client/GUI/wooden_pieces/Assistente (10).png")).toString());
                    default -> {
                    }
                }

                assistantImages.put(i, assistant);
            }
        }


        if (assistantNumber != 0) {
            assistant = assistantImages.get(assistantNumber);

            if(assistantView ==null){
                assistantView = new ImageView(assistant);
                lastPlayedAssistant.setGraphic(assistantView);
                lastPlayedAssistant.setVisible(true);
                assistantView.fitHeightProperty().bind(lastPlayedAssistant.heightProperty());
                assistantView.fitWidthProperty().bind(lastPlayedAssistant.widthProperty());
            }else{
                assistantView.setImage(assistant);
            }



        }
    }
}
