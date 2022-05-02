package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientNoEntry;

import java.io.IOException;

public class ClientNoEntryAdapter extends TypeAdapter<ClientNoEntry> {

    @Override
    public void write(JsonWriter jsonWriter, ClientNoEntry clientNoEntry) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("NoEntry");
        jsonWriter.name("ID");
        jsonWriter.value(clientNoEntry.getID());
        jsonWriter.endObject();
    }

    @Override
    public ClientNoEntry read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
