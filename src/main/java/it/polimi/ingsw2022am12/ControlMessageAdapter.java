package it.polimi.ingsw2022am12;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;

import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type ControlMessages.
 */
public class ControlMessageAdapter extends TypeAdapter<ControlMessages> {

    /**
     * Method "read" reads a JSON encoded ControlMessages as a stream of tokens
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return ControlMessages the ControlMessages created from the JSON values
     * @throws IOException if there is a problem with my input
     */
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

    /**
     * Method write receives an object of type ControlMessages and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param controlMessages the ControlMessages I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ControlMessages controlMessages) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("type");
        jsonWriter.value(controlMessages.name());
        jsonWriter.endObject();
    }
}
