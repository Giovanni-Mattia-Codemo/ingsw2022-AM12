package it.polimi.ingsw2022am12.server.gameSaveAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.SchoolBoard;
import it.polimi.ingsw2022am12.server.model.Team;

import java.io.IOException;

public class SaveGameTeamAdapter extends TypeAdapter<Team> {

    @Override
    public void write(JsonWriter writer, Team team) throws IOException {
        writer.beginObject();
        int i = 0;
        for (SchoolBoard s : team.getSchoolBoards()) {
            writer.name("Player" + i);
            writer.value(s.getNick());
            i++;
        }
        writer.endObject();
    }

    @Override
    public Team read(JsonReader reader) throws IOException {

        Team t = new Team();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldName = reader.nextName();
            }

            if ("Player0".equals(fieldName)) {
                reader.peek();
                t.addNick(reader.nextString());
            }
            if ("Player1".equals(fieldName)) {
                reader.peek();
                t.addNick(reader.nextString());
            }


        }
        reader.endObject();
        return t;

    }
}
