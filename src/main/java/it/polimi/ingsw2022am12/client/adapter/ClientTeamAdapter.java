package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientTeam;

import java.io.IOException;

public class ClientTeamAdapter extends TypeAdapter<ClientTeam> {

    @Override
    public void write(JsonWriter jsonWriter, ClientTeam team) throws IOException {
        return;
    }

    @Override
    public ClientTeam read(JsonReader reader) throws IOException {

        ClientTeam team = new ClientTeam();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldname = reader.nextName();
            }

            if("TeamName".equals(fieldname)) {
                token = reader.peek();
                team.setPlayer1(reader.nextString());
            }

            if ("Player2".equals(fieldname)) {
                token = reader.peek();
                team.setPlayer2(reader.nextString());
            }






        }
        reader.endObject();
        return team;

    }
}
