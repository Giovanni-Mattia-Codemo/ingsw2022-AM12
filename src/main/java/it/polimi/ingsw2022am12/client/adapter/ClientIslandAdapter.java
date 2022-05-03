package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientIsland;
import it.polimi.ingsw2022am12.client.ClientStudent;
import it.polimi.ingsw2022am12.client.ClientStudentCollection;
import it.polimi.ingsw2022am12.server.model.DiskColor;

import java.io.IOException;
import java.util.ArrayList;

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
                //get the current token
                fieldName = reader.nextName();
            }

            if("ID".equals(fieldName)) {
                //move to next token
                token = reader.peek();
                island.setID(reader.nextInt());
            }
            if ("Students".equals(fieldName)) {
                //move to next token
                token = reader.peek();


                ClientStudentCollection students = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create().fromJson(reader, ClientStudentCollection.class);


                island.setStudents(students);
            }
            if("IslandNumber".equals(fieldName)) {
                token = reader.peek();
                island.setNumber(reader.nextInt());
            }
            if("Conqueror".equals(fieldName)) {
                token = reader.peek();
                island.setConqueror(reader.nextString());
            }


        }
        reader.endObject();
        return island;

    }
}
