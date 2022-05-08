package it.polimi.ingsw2022am12.client.adapter;


import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientCharacter;
import it.polimi.ingsw2022am12.client.ClientStudentCollection;
import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type ClientCHARACTER.
 */
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
                fieldName = reader.nextName();
            }
            if("CharacterName".equals(fieldName)) {
                reader.peek();
                student.setName(reader.nextString());
            }
            if ("HasCoin".equals(fieldName)) {
                reader.peek();
                student.setHasCoin(reader.nextBoolean());
            }
            if ("Cost".equals(fieldName)) {
                reader.peek();
                student.setCost(reader.nextInt());
            }
            if ("EntryNum".equals(fieldName)) {
                reader.peek();
                student.setNumberOfNoEntries(reader.nextInt());
            }
            if ("EntryId".equals(fieldName)) {
                reader.peek();
                student.setNoEntryCollectionID(reader.nextInt());
            }
            if ("Students".equals(fieldName)) {
                reader.peek();
                ClientStudentCollection studentCollection = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create().fromJson(reader, ClientStudentCollection.class);
                student.setStudents(studentCollection);
            }

        }
        reader.endObject();
        return student;

    }
}
