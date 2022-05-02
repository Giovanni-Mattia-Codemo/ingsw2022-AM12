package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.CharacterCard;

import java.io.IOException;

public class CharacterAdapter extends TypeAdapter<CharacterCard> {

    @Override
    public void write(JsonWriter jsonWriter, CharacterCard characterCard) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("CharacterName");
        jsonWriter.value(characterCard.getName().toString());
        jsonWriter.name("HasCoin");
        if(characterCard.getCoin()){
            jsonWriter.value("true");
        }else jsonWriter.value("false");
        //to be continued

        jsonWriter.endObject();
    }

    @Override
    public CharacterCard read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
