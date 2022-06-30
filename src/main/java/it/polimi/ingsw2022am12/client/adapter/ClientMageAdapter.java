package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.model.ClientMage;

import java.io.IOException;

/**
 * Json adapter class of ClientMage's class
 */
public class ClientMageAdapter extends TypeAdapter<ClientMage> {

    /**
     * Method write receives an object of type ClientMage and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param clientMage the ClientMage I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientMage clientMage) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Mage");
        jsonWriter.name("ID");
        jsonWriter.value(clientMage.getID());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded ClientMage as a stream of tokens
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return ClientMage as a null value
     * @throws IOException if there is a problem with my input
     */
    @Override
    public ClientMage read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
