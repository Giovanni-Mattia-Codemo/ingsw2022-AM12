package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.SchoolBoard;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;

import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type SchoolBoard.
 */
public class SchoolBoardAdapter extends TypeAdapter<SchoolBoard> {

    Gson embedded = new Gson();

    /**
     * Method write receives an object of type SchoolBoard and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param schoolBoard the SchoolBoard I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, SchoolBoard schoolBoard) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("Nick");
        jsonWriter.value(schoolBoard.getNick());
        jsonWriter.name("Entrance");
        GsonBuilder build = new GsonBuilder();
        build.registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter());
        embedded = build.create();
        embedded.toJson(embedded.toJsonTree(schoolBoard.getEntrance()), jsonWriter);
        jsonWriter.name("DiningRoom");
        embedded.toJson(embedded.toJsonTree(schoolBoard.getDiningRoom()), jsonWriter);
        jsonWriter.name("Coins");
        jsonWriter.value(String.valueOf(schoolBoard.getNumOfCoins()) );
        jsonWriter.name("Mage");
        if(schoolBoard.getMage()!=null){
            jsonWriter.value(schoolBoard.getMage().getID());
        }else jsonWriter.value(-1);

        jsonWriter.name("Towers");
        jsonWriter.value(schoolBoard.getTowersNumber());
        jsonWriter.name("LastPlayedAssistant");

        jsonWriter.value(schoolBoard.getLastPlayedAssistantPower());
        jsonWriter.name("Assistants");
        build.registerTypeAdapter(Assistant.class, new AssistantAdapter());
        embedded = build.create();
        embedded.toJson(embedded.toJsonTree(schoolBoard.getPlayableAssistants()), jsonWriter);

        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded SchoolBoard
     *
     * @return SchoolBoard as a null value
     * @throws IOException if there is a problem with my input
     */
    @Override
    public SchoolBoard read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
