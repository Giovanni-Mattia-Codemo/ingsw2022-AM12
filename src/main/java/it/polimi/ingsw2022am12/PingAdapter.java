package it.polimi.ingsw2022am12;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type PING.
 */
public class PingAdapter extends TypeAdapter<Ping> {

    /**
     * Method write receives an object of type Ping and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param ping the Ping I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, Ping ping) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Ping");
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded Ping
     *
     * @param reader the reader which will receive my JSON data
     * @return Ping as a null value
     * @throws IOException if there is a problem with my input
     */
    @Override
    public Ping read(JsonReader reader) throws IOException {
        return null;
    }
}
