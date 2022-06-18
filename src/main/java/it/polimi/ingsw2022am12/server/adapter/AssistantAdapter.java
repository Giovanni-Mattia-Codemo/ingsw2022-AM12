package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.AssistantCreator;
import java.io.IOException;

/**
 * Class used to simplify the handling of a AssistantCard object
 */
public class AssistantAdapter extends TypeAdapter<Assistant> {

    /**
     * Method write receives an object of type Assistant and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param assistant the Assistant I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, Assistant assistant) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("AssistantTurnPower");
        jsonWriter.value(assistant.getTurnPower());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded Assistant as a stream of tokens
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return Assistant the Assistant created from the JSON values
     * @throws IOException if there is a problem with my input
     */
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
                jsonReader.peek();
                assistant = AssistantCreator.createAssistant(jsonReader.nextInt());
            }
        }
        jsonReader.endObject();
        
        return assistant;
    }
}
