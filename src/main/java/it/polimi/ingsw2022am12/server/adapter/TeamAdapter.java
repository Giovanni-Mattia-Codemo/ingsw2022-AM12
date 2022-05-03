package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.Team;

import java.io.IOException;

public class TeamAdapter extends TypeAdapter<Team> {

    @Override
    public Team read(JsonReader jsonReader) throws IOException {
        return null;
    }

    @Override
    public void write(JsonWriter jsonWriter, Team team) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("TeamName");
        jsonWriter.value(team.getSchoolBoardWithTowers().getNick());
        if(team.getSchoolBoards().size()>1){
            jsonWriter.name("Player2");
            jsonWriter.value(team.getSchoolBoards().get(1).getNick());
        }
        jsonWriter.endObject();

    }
}
