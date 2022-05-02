package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientMage;

import java.io.IOException;

public class ClientMageAdapter extends TypeAdapter<ClientMage> {

    @Override
    public void write(JsonWriter jsonWriter, ClientMage clientMage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Mage");
        jsonWriter.name("ID");
        jsonWriter.value(clientMage.getID());
        jsonWriter.endObject();
    }

    @Override
    public ClientMage read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
