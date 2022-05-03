package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.AssistantCreator;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;


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
        
        jsonReader.beginObject();
        Assistant assistant = null;
        String fieldName = null;

        while (jsonReader.hasNext()) {
            JsonToken token = jsonReader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = jsonReader.nextName();
            }

            if("AssistantTurnPower".equals(fieldName)) {

                token = jsonReader.peek();
                assistant = AssistantCreator.createAssistant(jsonReader.nextInt());
            }

        }
        jsonReader.endObject();
        
        return assistant;
    }
}
