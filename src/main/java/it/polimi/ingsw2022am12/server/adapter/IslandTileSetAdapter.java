package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.IslandTileSet;
import it.polimi.ingsw2022am12.server.model.Student;

import java.io.IOException;

public class IslandTileSetAdapter extends TypeAdapter<IslandTileSet> {

    Gson embedded = new Gson();

    @Override
    public void write(JsonWriter jsonWriter, IslandTileSet islandTileSet) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("ID");
        jsonWriter.value(islandTileSet.getID());
        jsonWriter.name("Students");
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Student.class, new StudentAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(islandTileSet.getStudents()), jsonWriter);
        jsonWriter.name("IslandNumber");
        jsonWriter.value(islandTileSet.getNumOfIslandsInThisSet());
        jsonWriter.name("Conqueror");
        if(islandTileSet.getOwningTeam()==null){
            jsonWriter.value("null");
        }else jsonWriter.value(islandTileSet.getOwningTeam().getSchoolBoardWithTowers().getNick());
        jsonWriter.endObject();
    }

    @Override
    public IslandTileSet read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
