package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.*;
import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type GAME.
 */
public class GameAdapter extends TypeAdapter<Game> {
    Gson embedded = new Gson();

    public GameAdapter(){
        super();
    }
    /**
     * Method write receives an object of type Game and serializes it in the JSON format
     *
     * @param writer the writer which will generate my JSON data
     * @param myGame to be serialized
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter writer, Game myGame) throws IOException {
        writer.beginObject();
        writer.name("tag");
        writer.value("GameState");
        writer.name("FreeCoins");
        writer.value(myGame.getFreeCoins().size());
        writer.name("Round");
        writer.value(myGame.getRound());
        writer.name("Turn");
        writer.value(myGame.getTurn());
        writer.name("turnOrder");
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(SchoolBoard.class, new SchoolBoardAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(myGame.getTurnOrder()), writer);
        writer.name("IslandList");
        builder.registerTypeAdapter(IslandTileSet.class, new IslandTileSetAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(myGame.getIslandList().getIslandsAsSelectable()), writer);
        writer.name("MotherNature");
        writer.value(myGame.getIslandList().getMotherNatureIndex());
        writer.name("Clouds");
        builder = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(myGame.getClouds()), writer);
        for(int i=0; i<5; i++){
            writer.name("Professor"+ i);
            if(myGame.getProfessors()[i]!=null){
                writer.value(myGame.getProfessors()[i].getNick());
            }else writer.value("null");

        }
        writer.name("Teams");
        embedded= new GsonBuilder().registerTypeAdapter(Team.class, new TeamAdapter()).create();
        embedded.toJson(embedded.toJsonTree(myGame.getTeams()), writer);
        writer.name("Phase");
        writer.value(myGame.getCurrentStrategy().getName());
        writer.name("LastRoundFlag");
        writer.value(myGame.getIsLastRoundFlag());
        writer.name("Mode");
        writer.value(myGame.isExpertMode());
        writer.name("Characters");
        embedded = new GsonBuilder().registerTypeAdapterFactory(new CharacterAdapterFactory()).create();
        embedded.toJson(embedded.toJsonTree(myGame.getAvailableCharacters()),writer);
        writer.name("ActiveCharacter");
        if(myGame.getActiveCharacterCard()!=null){
            writer.value(myGame.getActiveCharacterCard().getName().toString());
        }else writer.value("null");
        writer.endObject();
    }

    /**
     * Method "read" reads a JSON encoded Game
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return null value
     * @throws IOException if there is a problem with my input
     */
    @Override
    public Game read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
