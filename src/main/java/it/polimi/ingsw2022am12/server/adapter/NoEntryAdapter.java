package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.NoEntry;
import it.polimi.ingsw2022am12.server.model.NoEntryCollection;

import java.io.IOException;

public class NoEntryAdapter extends TypeAdapter<NoEntry> {

    @Override
    public void write(JsonWriter jsonWriter, NoEntry noEntry) throws IOException {

    }

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

                token = reader.peek();
                NoEntryCollection noEntryCollection = new NoEntryCollection(reader.nextInt());
                
                noEntry = new NoEntry(noEntryCollection);
            }



        }
        reader.endObject();
        return noEntry;
    }
}
