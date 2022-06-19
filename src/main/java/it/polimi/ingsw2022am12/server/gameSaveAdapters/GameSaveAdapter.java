package it.polimi.ingsw2022am12.server.gameSaveAdapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type GAME  to save them as JSON encoded data
 */
public class GameSaveAdapter extends TypeAdapter<Game> {

    Gson embedded;

    /**
     * Method write receives an object of type Game and serializes it in the JSON format
     *
     * @param writer the writer which will generate my JSON data
     * @param myGame the Game I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter writer, Game myGame) throws IOException {
        writer.beginObject();
        writer.name("CharacterMode");
        writer.value(myGame.isExpertMode());
        writer.name("FreeCoins");
        writer.value(myGame.getFreeCoins().size());
        writer.name("Round");
        writer.value(myGame.getRound());
        writer.name("Turn");
        writer.value(myGame.getTurn());
        writer.name("TurnOrder");
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(SchoolBoard.class, new SaveGameSchoolBoardAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(myGame.getTurnOrder()), writer);
        writer.name("IslandList");
        builder.registerTypeAdapter(IslandTileSet.class, new SaveGameIslandAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(myGame.getIslandList().getIslandsAsSelectable()), writer);
        writer.name("MotherNature");
        writer.value(myGame.getIslandList().getMotherNatureIndex());
        writer.name("Clouds");
        builder = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(myGame.getClouds()), writer);
        for(int i=0; i<5; i++){
            writer.name("Professor"+ i);
            if(myGame.getProfessors()[i]!=null){
                writer.value(myGame.getProfessors()[i].getNick());
            }else writer.value("null");
        }
        writer.name("Nicks");
        writer.beginArray();
        for(String nick:myGame.getPlayerNicks()){
            writer.value(nick);
        }
        writer.endArray();
        writer.name("Bag");
        builder = new GsonBuilder().registerTypeAdapter(Bag.class, new SaveGameStudentDiskCollectionAdapter());
        embedded = builder.create();
        embedded.toJson(embedded.toJsonTree(myGame.getBag()), writer);
        writer.name("DisksMovedThisTurn");
        writer.value(myGame.getDisksMovedThisTurn());
        writer.name("HasMovedMotherNature");
        writer.value(myGame.hasMovedMotherNature());

        writer.name("Teams");
        writer.beginArray();
        embedded = new GsonBuilder().registerTypeAdapter(Team.class, new SaveGameTeamAdapter()).create();
        for(Team t : myGame.getTeams()){
            embedded.toJson(embedded.toJsonTree(t), writer);
        }
        writer.endArray();
        writer.name("Phase");
        writer.value(myGame.getCurrentStrategy().getName());
        writer.name("LastRoundFlag");
        writer.value(myGame.getIsLastRoundFlag());
        writer.name("Characters");
        embedded = new GsonBuilder().registerTypeAdapterFactory(new SaveGameCharacterAdapterFactory()).create();
        embedded.toJson(embedded.toJsonTree(myGame.getAvailableCharacters()),writer);
        writer.name("ActiveCharacter");
        if(myGame.getActiveCharacterCard()!=null){
            writer.value(myGame.getActiveCharacterCard().getName().toString());
        }else writer.value("null");
        writer.endObject();
    }

    /**
     * Method "read" reads a JSON encoded Game as a stream of tokens
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return the Game created from the JSON values
     * @throws IOException if there is a problem with my input
     */
    @Override
    public Game read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String fieldName = null;
        boolean mode = false, hasMovedMN=false, lastRound=false;
        int freeCoins=0, round=0, turn=0, motherNature=0, diskMoved=0;
        ArrayList<CharacterCard> characters=null;
        ArrayList<SchoolBoard> turnOrder=null;
        ArrayList<IslandTileSet> islands=null;
        ArrayList<String> playerNicks = null;
        ArrayList<Team> teams = null;
        ArrayList<StudentDiskCollection> clouds=null;
        String[] professors = new String[5];
        String phase=null, activeCharacter=null;
        Bag bag=null;
        StudentDiskCollection temp;
        while(jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();
            if(token.equals(JsonToken.NAME)){
                fieldName = jsonReader.nextName();
            }
            if("Characters".equals(fieldName)){
                jsonReader.peek();
                characters = new ArrayList<>();
                jsonReader.beginArray();
                while(jsonReader.hasNext()){
                    CharacterCard character = new GsonBuilder().registerTypeAdapterFactory( new SaveGameCharacterAdapterFactory()).create().fromJson(jsonReader, CharacterCard.class);
                    characters.add(character);
                }
                jsonReader.endArray();
            }
            if("CharacterMode".equals(fieldName)){
                jsonReader.peek();
                mode = jsonReader.nextBoolean();
            }

            if("Teams".equals(fieldName)){
                jsonReader.peek();
                teams = new ArrayList<>();
                embedded = new GsonBuilder().registerTypeAdapter(Team.class, new SaveGameTeamAdapter()).create();
                jsonReader.beginArray();

                while (jsonReader.hasNext()){
                    Team t = embedded.fromJson(jsonReader, Team.class);
                    teams.add(t);
                }

                jsonReader.endArray();

            }

            if("Nicks".equals(fieldName)){
                jsonReader.peek();
                playerNicks = new ArrayList<>();
                jsonReader.beginArray();
                while (jsonReader.hasNext()){
                    String nick = jsonReader.nextString();
                    playerNicks.add(nick);
                }
                jsonReader.endArray();
            }

            if("FreeCoins".equals(fieldName)){
                jsonReader.peek();
                freeCoins = jsonReader.nextInt();
            }
            if("HasMovedMotherNature".equals(fieldName)){
                jsonReader.peek();
                hasMovedMN = jsonReader.nextBoolean();
            }
            if("LastRoundFlag".equals(fieldName)){
                jsonReader.peek();
                lastRound = jsonReader.nextBoolean();
            }
            if("Round".equals(fieldName)){
                jsonReader.peek();
                round = jsonReader.nextInt();
            }
            if("Turn".equals(fieldName)){
                jsonReader.peek();
                turn = jsonReader.nextInt();
            }
            if("DisksMovedThisTurn".equals(fieldName)){
                jsonReader.peek();
                diskMoved = jsonReader.nextInt();
            }
            if("Phase".equals(fieldName)){
                jsonReader.peek();
                phase = jsonReader.nextString();
            }
            if("ActiveCharacter".equals(fieldName)){
                jsonReader.peek();
                activeCharacter = jsonReader.nextString();
            }
            if("TurnOrder".equals(fieldName)){
                jsonReader.peek();
                turnOrder = new ArrayList<>();
                jsonReader.beginArray();
                while(jsonReader.hasNext()){
                    SchoolBoard school = new GsonBuilder().registerTypeAdapter(SchoolBoard.class, new SaveGameSchoolBoardAdapter()).create().fromJson(jsonReader, SchoolBoard.class);
                    turnOrder.add(school);
                }
                jsonReader.endArray();
            }
            if("IslandList".equals(fieldName)){
                jsonReader.peek();
                islands = new ArrayList<>();
                jsonReader.beginArray();
                while(jsonReader.hasNext()){
                    IslandTileSet island = new GsonBuilder().registerTypeAdapter(IslandTileSet.class, new SaveGameIslandAdapter()).create().fromJson(jsonReader, IslandTileSet.class);
                    islands.add(island);
                }
                jsonReader.endArray();
            }
            if("MotherNature".equals(fieldName)){
                jsonReader.peek();
                motherNature = jsonReader.nextInt();
            }
            if("Clouds".equals(fieldName)){
                jsonReader.peek();
                clouds = new ArrayList<>();
                jsonReader.beginArray();
                while(jsonReader.hasNext()){
                    StudentDiskCollection cloud = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter()).create().fromJson(jsonReader, StudentDiskCollection.class);
                    clouds.add(cloud);
                }
                jsonReader.endArray();
            }
            if ("Professor0".equals(fieldName)) {
                jsonReader.peek();
                String res = jsonReader.nextString();
                professors[0]=res;
            }
            if ("Professor1".equals(fieldName)) {
                jsonReader.peek();
                String res = jsonReader.nextString();
                professors[1]=res;
            }
            if ("Professor2".equals(fieldName)) {
                jsonReader.peek();
                String res = jsonReader.nextString();
                professors[2]=res;
            }
            if ("Professor3".equals(fieldName)) {
                jsonReader.peek();
                String res = jsonReader.nextString();
                professors[3]=res;
            }
            if ("Professor4".equals(fieldName)) {
                jsonReader.peek();
                String res = jsonReader.nextString();
                professors[4]=res;
            }
            if("Bag".equals(fieldName)){
                jsonReader.peek();
                temp = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter()).create().fromJson(jsonReader, StudentDiskCollection.class);
                bag = new Bag(temp);
            }

        }
        jsonReader.endObject();
        Game game = new Game(playerNicks, mode, bag, freeCoins, turnOrder, islands, motherNature, clouds, teams, professors, characters);
        game.setTurn(turn);
        game.setRound(round);
        game.setActiveCharacterCard(activeCharacter);
        game.setLastRoundFlag(lastRound);
        game.setDisksMovedThisTurn(diskMoved);
        game.setHasMovedMotherNature(hasMovedMN);
        game.setCurrentStrategy(phase);
        return game;
    }
}
