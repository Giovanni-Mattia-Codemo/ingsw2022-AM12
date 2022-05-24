package it.polimi.ingsw2022am12;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;

import java.io.IOException;

public class ControlMessageAdapter extends TypeAdapter<ControlMessages> {

    @Override
    public ControlMessages read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String name = null;
        String fieldName = null;
        while(jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();
            if(token.equals(JsonToken.NAME)){
                fieldName = jsonReader.nextName();
            }
            if("type".equals(fieldName)){
                jsonReader.peek();
                name = jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        return ControlMessages.valueOf(name);
    }

    @Override
    public void write(JsonWriter jsonWriter, ControlMessages controlMessages) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("type");
        jsonWriter.value(controlMessages.name());
        jsonWriter.endObject();
    }
}
