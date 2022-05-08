package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.NoEntry;
import it.polimi.ingsw2022am12.server.model.NoEntryCollection;

import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type NoEntry.
 */
public class NoEntryAdapter extends TypeAdapter<NoEntry> {

    /**
     * Method write receives an object of type NoEntry and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param noEntry the NoEntry I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, NoEntry noEntry) throws IOException {

    }

    /**
     * Method "read" reads a JSON encoded NoEntry
     *
     * @param reader the reader which will receive my JSON data
     * @return NoEntry translated from the JSON format data
     * @throws IOException if there is a problem with my input
     */
    @Override
    public NoEntry read(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldName = null;
        NoEntry noEntry = null;
        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {

                fieldName = reader.nextName();
            }

            if("ID".equals(fieldName)) {

                reader.peek();
                NoEntryCollection noEntryCollection = new NoEntryCollection(reader.nextInt());
                
                noEntry = new NoEntry(noEntryCollection);
            }



        }
        reader.endObject();
        return noEntry;
    }
}
