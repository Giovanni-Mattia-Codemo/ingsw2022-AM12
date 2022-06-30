package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.model.ClientTeam;
import java.io.IOException;

/**
 * Json adapter class of ClientTeam's class
 */
public class ClientTeamAdapter extends TypeAdapter<ClientTeam> {

    /**
     * Method write receives an object of type ClientTeam and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param team the ClientTeam I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientTeam team) throws IOException {
    }

    /**
     * Method "read" reads a JSON encoded ClientTeam as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return ClientTeam the ClientTeam created from the JSON values
     * @throws IOException if there is a problem with my input
     */
    @Override
    public ClientTeam read(JsonReader reader) throws IOException {

        ClientTeam team = new ClientTeam();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if("TeamName".equals(fieldName)) {
                reader.peek();
                team.setPlayer1(reader.nextString());
            }

            if ("Player2".equals(fieldName)) {
                reader.peek();
                team.setPlayer2(reader.nextString());
            }
        }
        reader.endObject();
        return team;
    }
}
