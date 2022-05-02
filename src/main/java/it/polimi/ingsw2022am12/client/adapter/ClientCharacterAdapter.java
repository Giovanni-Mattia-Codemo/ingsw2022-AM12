package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientCharacter;

import java.io.IOException;

public class ClientCharacterAdapter extends TypeAdapter<ClientCharacter> {

    @Override
    public void write(JsonWriter jsonWriter, ClientCharacter clientCharacter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Character");
        jsonWriter.name("Name");
        jsonWriter.value(clientCharacter.getName());
        jsonWriter.endObject();
    }

    @Override
    public ClientCharacter read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
