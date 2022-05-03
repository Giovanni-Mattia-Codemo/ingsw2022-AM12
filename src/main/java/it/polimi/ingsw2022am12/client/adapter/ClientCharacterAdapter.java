package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientCharacter;
import it.polimi.ingsw2022am12.client.ClientStudent;
import it.polimi.ingsw2022am12.client.ClientStudentCollection;
import it.polimi.ingsw2022am12.server.model.DiskColor;
import it.polimi.ingsw2022am12.server.model.Student;

import java.io.IOException;
import java.util.ArrayList;

public class ClientCharacterAdapter extends TypeAdapter<ClientCharacter> {

    @Override
    public void write(JsonWriter jsonWriter, ClientCharacter clientCharacter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Character");
        jsonWriter.name("Name");
        jsonWriter.value(clientCharacter.getName());
        jsonWriter.endObject();
    }

    @Override
    public ClientCharacter read(JsonReader reader) throws IOException {
        ClientCharacter student = new ClientCharacter();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldName = reader.nextName();
            }

            if("CharacterName".equals(fieldName)) {
                //move to next token
                token = reader.peek();
                student.setName(reader.nextString());

            }
            if ("HasCoin".equals(fieldName)) {
                token = reader.peek();
                student.setHasCoin(reader.nextBoolean());
            }
            if ("Cost".equals(fieldName)) {
                token = reader.peek();
                student.setCost(reader.nextInt());
            }
            if ("EntryNum".equals(fieldName)) {
                token = reader.peek();
                student.setNumberOfNoEntries(reader.nextInt());
            }
            if ("EntryId".equals(fieldName)) {
                token = reader.peek();
                student.setNoEntryCollectionID(reader.nextInt());
            }
            if ("Students".equals(fieldName)) {
                token = reader.peek();
                ClientStudentCollection studentCollection = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create().fromJson(reader, ClientStudentCollection.class);
                student.setStudents(studentCollection);
            }



        }
        reader.endObject();
        return student;

    }
}
