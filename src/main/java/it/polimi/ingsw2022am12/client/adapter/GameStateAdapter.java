package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.model.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type ClientGAME.
 */
public class GameStateAdapter extends TypeAdapter<ClientGame> {

    /**
     * Method write receives an object of type clientGame and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param clientGame the state of the game
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientGame clientGame) throws IOException {

    }

    /**
     * Method "read" reads a JSON encoded clientGame as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return clientGame the clientGame created from the JSON values
     * @throws IOException if there is a problem with my input
     */
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
                reader.peek();
                clientGame.setFreeCoins(reader.nextInt());
            }
            if ("Turn".equals(fieldName)) {
                reader.peek();
                clientGame.setTurn(reader.nextInt());
            }
            if ("Round".equals(fieldName)) {
                reader.peek();
                clientGame.setRound(reader.nextInt());
            }
            if ("LastRoundFlag".equals(fieldName)) {
                reader.peek();
                clientGame.setLastRound(reader.nextBoolean());
            }
            if ("Mode".equals(fieldName)) {
                reader.peek();
                clientGame.setMode(reader.nextBoolean());
            }
            if ("MotherNature".equals(fieldName)) {
                reader.peek();
                clientGame.setMotherNatureIndex(reader.nextInt());
            }
            if ("Phase".equals(fieldName)) {
                reader.peek();
                clientGame.setPhase(reader.nextString());
            }
            if ("ActiveCharacter".equals(fieldName)) {
                reader.peek();
                clientGame.setActiveCharacter(reader.nextString());
            }
            if ("Characters".equals(fieldName)) {
                reader.peek();
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
                reader.peek();
                ArrayList<ClientSchoolBoard> schoolBoards = new ArrayList<>();
                ArrayList<String> nicks = new ArrayList<>();

                reader.beginArray();
                while(reader.hasNext()){
                    ClientSchoolBoard school = (new GsonBuilder().registerTypeAdapter(ClientSchoolBoard.class, new ClientSchoolBoardAdapter()).create().fromJson(reader, ClientSchoolBoard.class));
                    schoolBoards.add(school);
                    nicks.add(school.getNick());

                }
                reader.endArray();
                clientGame.setSchoolBoards(schoolBoards);
                clientGame.setOrderedNicks(nicks);
            }

            if ("IslandList".equals(fieldName)) {
                reader.peek();
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
                reader.peek();
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
                reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(0, res);
            }
            if ("Professor1".equals(fieldName)) {
                reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(1, res);
            }
            if ("Professor2".equals(fieldName)) {
                reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(2, res);
            }
            if ("Professor3".equals(fieldName)) {
                reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(3, res);
            }
            if ("Professor4".equals(fieldName)) {
                reader.peek();
                String res = reader.nextString();
                clientGame.setProfessor(4, res);
            }


            if ("Teams".equals(fieldName)) {
                reader.peek();
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
