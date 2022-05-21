package it.polimi.ingsw2022am12;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type NickInput.
 */
public class NickInputAdapter extends TypeAdapter<NickInput> {

    /**
     * Method write receives an object of type NickInput and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param nickInput the NickInput I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, NickInput nickInput) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Nick");

        jsonWriter.name("nick");
        jsonWriter.value(nickInput.getNick());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded NickInput as a stream of tokens
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return NickInput the NickInput created from the JSON values
     * @throws IOException if there is a problem with my input
     */
    @Override
    public NickInput read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
