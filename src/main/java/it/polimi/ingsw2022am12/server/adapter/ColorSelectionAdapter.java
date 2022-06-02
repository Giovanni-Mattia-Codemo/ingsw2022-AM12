package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.ColorSelection;
import it.polimi.ingsw2022am12.server.model.Student;

import java.io.IOException;

public class ColorSelectionAdapter extends TypeAdapter<ColorSelection> {

    @Override
    public void write(JsonWriter jsonWriter, ColorSelection c) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Color");
        jsonWriter.name("Color");
        jsonWriter.value(String.valueOf(c.getColor()));
        jsonWriter.endObject();
    }

    @Override
    public ColorSelection read(JsonReader reader) throws IOException {
        DiskColor color = null;

        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldName = reader.nextName();
            }

            if("Color".equals(fieldName)) {
                reader.peek();
                color = DiskColor.valueOf(reader.nextString());
            }



        }
        reader.endObject();
        return new ColorSelection(color);
    }



}
