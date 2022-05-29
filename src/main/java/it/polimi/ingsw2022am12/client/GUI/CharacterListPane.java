package it.polimi.ingsw2022am12.client.GUI;

import it.polimi.ingsw2022am12.client.Client;
import it.polimi.ingsw2022am12.client.model.ClientCharacter;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CharacterListPane extends CircularPane {

    private final ArrayList<CharacterPane> characters;

    public CharacterListPane(Client client){
        super();
        this.characters = new ArrayList<>();
        setMinSize(1.0, 1.0);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setBackground(Background.fill(Color.CYAN));
        for(ClientCharacter c : client.getClientGame().getCharacters()){
            CharacterPane character = new CharacterPane(c.getName(), client);
            characters.add(character);
            getChildren().add(character);
            character.maxHeightProperty().bind(this.heightProperty().multiply(0.3));
            character.maxWidthProperty().bind(this.widthProperty().multiply(0.3));
        }
    }

    public void refresh(){
        for(CharacterPane p:characters){
            p.refresh();
        }
    }
}
