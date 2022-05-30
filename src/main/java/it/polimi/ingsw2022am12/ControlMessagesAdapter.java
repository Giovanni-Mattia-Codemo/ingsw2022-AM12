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

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle more objects of the type ControlMessages, as a list of messages.
 */
public class ControlMessagesAdapter extends TypeAdapter<ArrayList<ControlMessages>> {

    Gson embedded = new Gson();

    /**
     * Method "read" reads a JSON encoded ArrayList of ControlMessages as a stream of tokens
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return ArrayList<ControlMessages> the list of ControlMessages created from the JSON values
     * @throws IOException if there is a problem with my input
     */
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

    /**
     * Method write receives an object of type ArrayList<ControlMessages> and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param controlMessages the list of ControlMessages I want to serialize
     * @throws IOException if there is a problem with my input
     */
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
