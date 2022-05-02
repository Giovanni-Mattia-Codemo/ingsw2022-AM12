package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.NickInput;

import java.io.IOException;

public class NickInputAdapter extends TypeAdapter<NickInput> {

    @Override
    public void write(JsonWriter jsonWriter, NickInput nickInput) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Nick");

        jsonWriter.name("nick");
        jsonWriter.value(nickInput.getNick());
        jsonWriter.endObject();
    }

    @Override
    public NickInput read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
