package it.polimi.ingsw2022am12;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class InputModeAdapter extends TypeAdapter<InputMode> {

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

    @Override
    public InputMode read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
