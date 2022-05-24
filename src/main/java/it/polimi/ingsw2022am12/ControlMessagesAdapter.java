package it.polimi.ingsw2022am12;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;

import java.io.IOException;
import java.util.ArrayList;

public class ControlMessagesAdapter extends TypeAdapter<ArrayList<ControlMessages>> {

    Gson embedded = new Gson();

    @Override
    public ArrayList<ControlMessages> read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        ArrayList<ControlMessages> messages = new ArrayList<>();
        String fieldName = null;
        while(jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();
            if(token.equals(JsonToken.NAME)){
                fieldName = jsonReader.nextName();
            }
            if("Messages".equals(fieldName)){
                jsonReader.peek();

                jsonReader.beginArray();
                while (jsonReader.hasNext()){
                    ControlMessages msg = new GsonBuilder().registerTypeAdapter(ControlMessages.class, new ControlMessageAdapter()).create().fromJson(jsonReader, ControlMessages.class);
                    messages.add(msg);
                }
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        return messages;
    }

    @Override
    public void write(JsonWriter jsonWriter, ArrayList<ControlMessages> controlMessages) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("ControlMessages");
        jsonWriter.name("Messages");
        GsonBuilder build = new GsonBuilder();
        build.registerTypeAdapter(ControlMessages.class, new ControlMessageAdapter());
        embedded = build.create();
        embedded.toJson(embedded.toJsonTree(controlMessages), jsonWriter);
        jsonWriter.endObject();
    }
}
