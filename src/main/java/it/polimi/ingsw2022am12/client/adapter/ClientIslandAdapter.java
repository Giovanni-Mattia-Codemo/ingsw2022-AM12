package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientIsland;

import java.io.IOException;

public class ClientIslandAdapter extends TypeAdapter<ClientIsland> {

    @Override
    public void write(JsonWriter jsonWriter, ClientIsland clientIsland) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Island");
        jsonWriter.name("ID");
        jsonWriter.value(clientIsland.getID());
        jsonWriter.endObject();
    }

    @Override
    public ClientIsland read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
