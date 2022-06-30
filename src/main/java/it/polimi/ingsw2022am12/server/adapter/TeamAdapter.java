package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.Team;
import java.io.IOException;

/**
 * Json adapter class of Team's class
 */
public class TeamAdapter extends TypeAdapter<Team> {

    /**
     * Method "read" reads a JSON encoded Team
     *
     * @param jsonReader the reader which will receive my JSON data
     * @return null value
     * @throws IOException if there is a problem with my input
     */
    @Override
    public Team read(JsonReader jsonReader) throws IOException {
        return null;
    }

    /**
     * Method write receives an object of type Team and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param team the Team I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, Team team) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("TeamName");
        jsonWriter.value(team.getSchoolBoardWithTowers().getNick());
        jsonWriter.name("Player2");
        if(team.getSchoolBoards().size()>1){
            jsonWriter.value(team.getSchoolBoards().get(1).getNick());
        }else jsonWriter.value("null");
        jsonWriter.endObject();
    }
}
