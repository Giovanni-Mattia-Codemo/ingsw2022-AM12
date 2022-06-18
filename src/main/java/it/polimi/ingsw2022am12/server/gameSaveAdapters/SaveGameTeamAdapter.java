package it.polimi.ingsw2022am12.server.gameSaveAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.SchoolBoard;
import it.polimi.ingsw2022am12.server.model.Team;
import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type TEAM to save them as JSON encoded data
 */
public class SaveGameTeamAdapter extends TypeAdapter<Team> {

    /**
     * Method write receives an object of type Team and serializes it in the JSON format
     *
     * @param writer the writer which will generate my JSON data
     * @param team the Team I want to serialize
     * @throws IOException if there is a problem with my input
     */
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

    /**
     * Method "read" reads a JSON encoded Team as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return the Team created from the JSON values
     * @throws IOException if there is a problem with my input
     */
    @Override
    public Team read(JsonReader reader) throws IOException {

        Team t = new Team();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
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
