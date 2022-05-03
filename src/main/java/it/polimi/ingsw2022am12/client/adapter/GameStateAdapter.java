package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameStateAdapter extends TypeAdapter<ClientGame> {


    @Override
    public void write(JsonWriter jsonWriter, ClientGame clientGame) throws IOException {

    }

    @Override
    public ClientGame read(JsonReader reader) throws IOException {
        ClientGame clientGame = new ClientGame();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if("FreeCoins".equals(fieldName)) {
                token = reader.peek();
                clientGame.setFreeCoins(reader.nextInt());
            }
            if ("Turn".equals(fieldName)) {
                token = reader.peek();
                clientGame.setTurn(reader.nextInt());
            }
            if ("Round".equals(fieldName)) {
                token = reader.peek();
                clientGame.setRound(reader.nextInt());
            }
            if ("LastRoundFlag".equals(fieldName)) {
                token = reader.peek();
                clientGame.setLastRound(reader.nextBoolean());
            }
            if ("Mode".equals(fieldName)) {
                token = reader.peek();
                clientGame.setMode(reader.nextBoolean());
            }
            if ("MotherNature".equals(fieldName)) {
                token = reader.peek();
                clientGame.setMotherNatureIndex(reader.nextInt());
            }
            if ("Phase".equals(fieldName)) {
                token = reader.peek();
                clientGame.setPhase(reader.nextString());
            }
            if ("ActiveCharacter".equals(fieldName)) {
                token = reader.peek();
                clientGame.setActiveCharacter(reader.nextString());
            }
            if ("Characters".equals(fieldName)) {
                token = reader.peek();
                ArrayList<ClientCharacter> characters = new ArrayList<>();

                reader.beginArray();
                while(reader.hasNext()){
                    ClientCharacter character = (new GsonBuilder().registerTypeAdapter(ClientCharacter.class, new ClientCharacterAdapter()).create().fromJson(reader, ClientCharacter.class));
                    characters.add(character);

                }
                reader.endArray();
                clientGame.setCharacters(characters);

            }
            if ("turnOrder".equals(fieldName)) {
                token = reader.peek();
                ArrayList<ClientSchoolBoard> schoolBoards = new ArrayList<>();

                reader.beginArray();
                while(reader.hasNext()){
                    ClientSchoolBoard school = (new GsonBuilder().registerTypeAdapter(ClientSchoolBoard.class, new ClientSchoolBoardAdapter()).create().fromJson(reader, ClientSchoolBoard.class));
                    schoolBoards.add(school);

                }
                reader.endArray();
                clientGame.setSchoolBoards(schoolBoards);
            }

            if ("IslandList".equals(fieldName)) {
                token = reader.peek();
                ArrayList<ClientIsland> islands = new ArrayList<>();

                reader.beginArray();
                while(reader.hasNext()){
                    ClientIsland island = (new GsonBuilder().registerTypeAdapter(ClientIsland.class, new ClientIslandAdapter()).create().fromJson(reader, ClientIsland.class));
                    islands.add(island);

                }
                reader.endArray();
                clientGame.setIslands(islands);
            }
            if ("Clouds".equals(fieldName)) {
                token = reader.peek();
                ArrayList<ClientStudentCollection> clouds = new ArrayList<>();

                reader.beginArray();
                while(reader.hasNext()){
                    ClientStudentCollection cloud = (new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create().fromJson(reader, ClientStudentCollection.class));
                    clouds.add(cloud);

                }
                reader.endArray();
                clientGame.setClouds(clouds);
            }


            if ("Professor0".equals(fieldName)) {
                token = reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(0, res);
            }
            if ("Professor1".equals(fieldName)) {
                token = reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(1, res);
            }
            if ("Professor2".equals(fieldName)) {
                token = reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(2, res);
            }
            if ("Professor3".equals(fieldName)) {
                token = reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(3, res);
            }
            if ("Professor4".equals(fieldName)) {
                token = reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(4, res);
            }


            if ("Teams".equals(fieldName)) {
                token = reader.peek();
                ArrayList<ClientTeam> teams = new ArrayList<>();

                reader.beginArray();
                while(reader.hasNext()){
                    ClientTeam team = (new GsonBuilder().registerTypeAdapter(ClientTeam.class, new ClientTeamAdapter()).create().fromJson(reader, ClientTeam.class));
                    teams.add(team);

                }
                reader.endArray();

                clientGame.setTeams(teams);
            }

        }
        reader.endObject();
        return clientGame;
    }
}
