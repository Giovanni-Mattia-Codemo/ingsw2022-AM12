package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.InputMode;

import java.io.IOException;

public class MessageAdapter extends TypeAdapter<InputMode> {


    @Override
    public void write(JsonWriter jsonWriter, InputMode o) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("InputMode");
        jsonWriter.name("number");
        jsonWriter.value(o.getNumber());
        jsonWriter.name("mode");
        jsonWriter.value(o.getMode());
        jsonWriter.endObject();
    }

    @Override
    public InputMode read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
