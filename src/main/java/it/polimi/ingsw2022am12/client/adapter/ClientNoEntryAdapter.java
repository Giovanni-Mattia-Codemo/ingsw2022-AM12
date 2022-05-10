package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.model.ClientNoEntry;

import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type ClientNOENTRY.
 */
public class ClientNoEntryAdapter extends TypeAdapter<ClientNoEntry> {

    /**
     * Method write receives an object of type ClientNoEntry and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param clientNoEntry the ClientNoEntry I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientNoEntry clientNoEntry) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("NoEntry");
        jsonWriter.name("ID");
        jsonWriter.value(clientNoEntry.getID());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded ClientNoEntry as a stream of tokens
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return ClientNoEntry as a null value
     * @throws IOException if there is a problem with my input
     */
    @Override
    public ClientNoEntry read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
