package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
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
    public ClientAssistant read(JsonReader reader) throws IOException {

        ClientAssistant clientAssistant = new ClientAssistant();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if("AssistantTurnPower".equals(fieldName)) {
                reader.peek();
                clientAssistant.setTurnPower(reader.nextInt());
            }
        }
        reader.endObject();
        return clientAssistant;
    }
}
