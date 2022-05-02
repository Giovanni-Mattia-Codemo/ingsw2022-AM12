package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientAssistant;

import java.io.IOException;

public class ClientAssistantAdapter extends TypeAdapter<ClientAssistant> {

    @Override
    public void write(JsonWriter jsonWriter, ClientAssistant clientAssistant) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Assistant");
        jsonWriter.name("TurnPower");
        jsonWriter.value(clientAssistant.getTurnPower());
        jsonWriter.endObject();
    }

    @Override
    public ClientAssistant read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
