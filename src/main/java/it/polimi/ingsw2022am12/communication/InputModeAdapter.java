package it.polimi.ingsw2022am12.communication;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.communication.InputMode;

import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type InputMode.
 */
public class InputModeAdapter extends TypeAdapter<InputMode> {

    /**
     * Method write receives an object of type InputMode and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param inputMode the inputMode I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, InputMode inputMode) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("InputMode");
        jsonWriter.name("number");
        jsonWriter.value(inputMode.getNumber());
        jsonWriter.name("mode");
        jsonWriter.value(inputMode.getMode());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded InputMode
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return InputMode as a null value
     * @throws IOException if there is a problem with my input
     */
    @Override
    public InputMode read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
