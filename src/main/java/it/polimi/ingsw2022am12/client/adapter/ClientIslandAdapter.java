package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientIsland;
import it.polimi.ingsw2022am12.client.ClientStudentCollection;
import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type ClientISLAND.
 */
public class ClientIslandAdapter extends TypeAdapter<ClientIsland> {

    @Override
    public void write(JsonWriter jsonWriter, ClientIsland clientIsland) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Island");
        jsonWriter.name("ID");
        jsonWriter.value(clientIsland.getID());
        jsonWriter.endObject();
    }

    @Override
    public ClientIsland read(JsonReader reader) throws IOException {
        ClientIsland island = new ClientIsland();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if("ID".equals(fieldName)) {
                reader.peek();
                island.setID(reader.nextInt());
            }
            if ("Students".equals(fieldName)) {
                reader.peek();

                ClientStudentCollection students = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create().fromJson(reader, ClientStudentCollection.class);
                island.setStudents(students);
            }
            if("IslandNumber".equals(fieldName)) {
                reader.peek();
                island.setNumber(reader.nextInt());
            }
            if("Conqueror".equals(fieldName)) {
                reader.peek();
                island.setConqueror(reader.nextString());
            }

        }
        reader.endObject();
        return island;

    }
}
