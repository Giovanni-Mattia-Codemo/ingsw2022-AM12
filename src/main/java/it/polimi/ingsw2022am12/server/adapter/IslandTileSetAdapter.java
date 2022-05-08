package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;

import java.io.IOException;

public class IslandTileSetAdapter extends TypeAdapter<IslandTileSet> {

    Gson embedded = new Gson();

    @Override
    public void write(JsonWriter jsonWriter, IslandTileSet islandTileSet) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("ID");
        jsonWriter.value(islandTileSet.getID());
        jsonWriter.name("Students");
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(islandTileSet.getStudentCollection()),jsonWriter);
        jsonWriter.name("IslandNumber");
        jsonWriter.value(islandTileSet.getNumOfIslandsInThisSet());
        jsonWriter.name("Conqueror");
        if(islandTileSet.getOwningTeam()==null){
            jsonWriter.value("null");
        }else jsonWriter.value(islandTileSet.getOwningTeam().getSchoolBoardWithTowers().getNick());
        jsonWriter.endObject();
    }

    @Override
    public IslandTileSet read(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldName = null;
        IslandTileSet island = null;
        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {

                fieldName = reader.nextName();
            }

            if("ID".equals(fieldName)) {

                reader.peek();
                island = new IslandTileSet(reader.nextInt());
            }



        }
        reader.endObject();
        return island;
    }
}
