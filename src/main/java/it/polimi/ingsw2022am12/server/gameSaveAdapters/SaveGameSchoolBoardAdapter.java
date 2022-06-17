package it.polimi.ingsw2022am12.server.gameSaveAdapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.adapter.AssistantAdapter;
import it.polimi.ingsw2022am12.server.model.Assistant;
import it.polimi.ingsw2022am12.server.model.SchoolBoard;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type SCHOOLBOARD to save them as JSON encoded data
 */
public class SaveGameSchoolBoardAdapter extends TypeAdapter<SchoolBoard> {

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
        build.registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter());
        embedded = build.create();
        embedded.toJson(embedded.toJsonTree(schoolBoard.getEntrance()), jsonWriter);
        jsonWriter.name("DiningRoom");
        embedded.toJson(embedded.toJsonTree(schoolBoard.getDiningRoom()), jsonWriter);
        jsonWriter.name("Coins");
        jsonWriter.value(String.valueOf(schoolBoard.getNumOfCoins()));
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
        embedded.toJson(embedded.toJsonTree(schoolBoard.getRemainingAssistants()), jsonWriter);
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
        jsonReader.beginObject();
        String fieldName = null;
        String nick = null;
        StudentDiskCollection entrance=null, dining=null;
        int coins=0, mage=0, towers=0, lastPlayedAssistant=0;
        ArrayList<Assistant> assistants = null;


        while(jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();
            if(token.equals(JsonToken.NAME)){
                fieldName = jsonReader.nextName();
            }

            if("Nick".equals(fieldName)){
                jsonReader.peek();
                nick = jsonReader.nextString();
            }
            if("Entrance".equals(fieldName)){
                jsonReader.peek();
                entrance = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter()).create().fromJson(jsonReader, StudentDiskCollection.class);
            }
            if("DiningRoom".equals(fieldName)){
                jsonReader.peek();
                dining = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter()).create().fromJson(jsonReader, StudentDiskCollection.class);
            }
            if("Coins".equals(fieldName)){
                jsonReader.peek();
                coins = jsonReader.nextInt();
            }
            if("Mage".equals(fieldName)){
                jsonReader.peek();
                mage = jsonReader.nextInt();
            }
            if("Towers".equals(fieldName)){
                jsonReader.peek();
                towers = jsonReader.nextInt();
            }
            if("LastPlayedAssistant".equals(fieldName)){
                jsonReader.peek();
                lastPlayedAssistant = jsonReader.nextInt();
            }
            if("Assistants".equals(fieldName)){
                jsonReader.peek();
                assistants = new ArrayList<>();
                jsonReader.beginArray();
                while(jsonReader.hasNext()){
                    Assistant assistant = new GsonBuilder().registerTypeAdapter(Assistant.class, new AssistantAdapter()).create().fromJson(jsonReader, Assistant.class);
                    assistants.add(assistant);
                }
                jsonReader.endArray();
            }

    }
        jsonReader.endObject();
        return new SchoolBoard(nick, mage, coins, assistants, entrance, dining, towers, lastPlayedAssistant);
}   }

