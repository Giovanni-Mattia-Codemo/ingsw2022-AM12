package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.model.ClientCharacter;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * CharacterListPane represents the graphic of the list of Characters in the tabletop scene (the one with the islands)
 */
public class CharacterListPane extends HBox {

    private final ArrayList<CharacterPane> characters;

    public CharacterListPane(Client client){
        super();
        double ratio = 3.0/2;
        this.characters = new ArrayList<>();
        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setBackground(Background.fill(Color.CYAN));
        for(ClientCharacter c : client.getClientGame().getCharacters()){
            CharacterPane character = new CharacterPane(c.getName(), client);
            characters.add(character);
            getChildren().add(character);
            HBox.setHgrow(character, Priority.ALWAYS);
            character.prefHeightProperty().bind(character.widthProperty().multiply(ratio));
            character.prefWidthProperty().bind(this.widthProperty().divide(3));
        }
    }

    public void refresh(){
        for(CharacterPane p:characters){
            p.refresh();
        }
    }
}
