package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.Assistant;


import java.io.IOException;

public class AssistantAdapter extends TypeAdapter<Assistant> {

    @Override
    public void write(JsonWriter jsonWriter, Assistant assistant) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("AssistantTurnPower");
        jsonWriter.value(assistant.getTurnPower());
        jsonWriter.endObject();
    }

    @Override
    public Assistant read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
