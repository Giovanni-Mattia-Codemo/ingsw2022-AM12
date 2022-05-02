package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.NoEntry;

import java.io.IOException;

public class NoEntryAdapter extends TypeAdapter<NoEntry> {

    @Override
    public void write(JsonWriter jsonWriter, NoEntry noEntry) throws IOException {

    }

    @Override
    public NoEntry read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
