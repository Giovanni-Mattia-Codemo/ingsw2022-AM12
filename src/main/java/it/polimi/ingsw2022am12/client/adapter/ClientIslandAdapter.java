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

    /**
     * Method write receives an object of type ClientIsland and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param clientIsland the ClientIsland I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientIsland clientIsland) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Island");
        jsonWriter.name("ID");
        jsonWriter.value(clientIsland.getID());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded ClientIsland as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return ClientIsland the ClientIsland created from the JSON values
     * @throws IOException if there is a problem with my input
     */
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
