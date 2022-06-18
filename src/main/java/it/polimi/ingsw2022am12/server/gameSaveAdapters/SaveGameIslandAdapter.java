package it.polimi.ingsw2022am12.server.gameSaveAdapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.*;
import java.io.IOException;

public class SaveGameIslandAdapter extends TypeAdapter<IslandTileSet> {

    Gson embedded;

    @Override
    public void write(JsonWriter jsonWriter, IslandTileSet islandTileSet) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("ID");
        jsonWriter.value(islandTileSet.getID());
        jsonWriter.name("NumOfIslandsInThisSet");
        jsonWriter.value(islandTileSet.getNumOfIslandsInThisSet());
        jsonWriter.name("NumOfNoEntries");
        jsonWriter.value(islandTileSet.getNoEntries().size());
        jsonWriter.name("NoEntryCollectionID");
        jsonWriter.value(islandTileSet.getNoEntryID());
        jsonWriter.name("Towers");
        jsonWriter.value(islandTileSet.getTowers().size());
        jsonWriter.name("Owner");
        jsonWriter.value(islandTileSet.getOwningTeam()!=null?islandTileSet.getOwningTeam().getSchoolBoardWithTowers().getNick():"null");
        jsonWriter.name("Students");
        GsonBuilder build = new GsonBuilder();
        build.registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter());
        embedded = build.create();
        embedded.toJson(embedded.toJsonTree(islandTileSet.getStudentCollection()), jsonWriter);
        jsonWriter.endObject();
    }

    @Override
    public IslandTileSet read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String fieldName = null;
        StudentDiskCollection students=null;
        String owner = null;
        int id=0, numOfIslands=0, towers=0, numOfNoEntries=0, noEntryID=0;


        while(jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();
            if(token.equals(JsonToken.NAME)){
                fieldName = jsonReader.nextName();
            }

            if("ID".equals(fieldName)){
                jsonReader.peek();
                id = jsonReader.nextInt();
            }
            if("NumOfIslandsInThisSet".equals(fieldName)){
                jsonReader.peek();
                numOfIslands = jsonReader.nextInt();
            }
            if("NumOfNoEntries".equals(fieldName)){
                jsonReader.peek();
                numOfNoEntries = jsonReader.nextInt();
            }
            if("NoEntryCollectionID".equals(fieldName)){
                jsonReader.peek();
                noEntryID = jsonReader.nextInt();
            }
            if("Owner".equals(fieldName)){
                jsonReader.peek();
                owner = jsonReader.nextString();
            }

            if("Towers".equals(fieldName)){
                jsonReader.peek();
                towers = jsonReader.nextInt();
            }
            if("Students".equals(fieldName)){
                jsonReader.peek();
                students = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter()).create().fromJson(jsonReader, StudentDiskCollection.class);
            }

        }
        jsonReader.endObject();
        return new IslandTileSet(id, students, noEntryID, numOfNoEntries, numOfIslands,towers, owner);
        }
    }

