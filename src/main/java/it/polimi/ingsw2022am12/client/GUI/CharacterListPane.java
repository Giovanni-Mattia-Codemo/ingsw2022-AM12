package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.model.ClientCharacter;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * CharacterListPane represents the graphic of the list of Characters in the tabletop scene (the one with the islands)
 */
public class CharacterListPane extends HBox {

    private final ArrayList<CharacterPane> characters;

    /**
     * Constructor method of CharacterListPane
     *
     * @param client the client visualizing the Characters' list
     */
    public CharacterListPane(Client client){
        //HBox settings
        super();
        this.characters = new ArrayList<>();
        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setBackground(Background.fill(Color.CYAN));
        //Character visual divider
        Pane blank = new Pane();
        blank.setMinSize(1.0, 1.0);
        blank.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(blank, Priority.ALWAYS);
        getChildren().add(blank);
        //Creates character panes and sets their position in the CharacterListPane
        for(ClientCharacter c : client.getClientGame().getCharacters()){
            CharacterPane character = new CharacterPane(c.getName(), client);
            characters.add(character);
            getChildren().add(character);
            HBox.setHgrow(character, Priority.NEVER);
            setFillHeight(false);

            double characterImageRatio = 3.0;
            character.prefHeightProperty().bind(character.widthProperty().multiply(characterImageRatio));
            character.prefWidthProperty().bind(this.widthProperty().divide(6));

            //Inserts a divider
            blank = new Pane();
            blank.setMinSize(1.0, 1.0);
            blank.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            HBox.setHgrow(blank, Priority.ALWAYS);
            getChildren().add(blank);
        }
    }

    /**
     * refresh resets all the graphics for the objects placed on the various characters in the list according to the
     * current state of the game
     */
    public void refresh(){
        for(CharacterPane p:characters){
            p.refresh();
        }
    }
}
