package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientAssistant;
import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type ClientASSISTANT.
 */
public class ClientAssistantAdapter extends TypeAdapter<ClientAssistant> {

    /**
     * Method write receives an object of type ClientAssistant and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param clientAssistant the ClientAssistant I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientAssistant clientAssistant) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Assistant");
        jsonWriter.name("TurnPower");
        jsonWriter.value(clientAssistant.getTurnPower());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded ClientAssistant as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return ClientAssistant the ClientAssistant created from the JSON values
     * @throws IOException if there is a problem with my input
     */
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
