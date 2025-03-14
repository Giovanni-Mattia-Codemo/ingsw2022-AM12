package it.polimi.ingsw2022am12.client.adapter;


import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.model.ClientCharacter;
import it.polimi.ingsw2022am12.client.model.ClientStudentCollection;
import java.io.IOException;

/**
 * Json adapter class of ClientCharacter's class
 */
public class ClientCharacterAdapter extends TypeAdapter<ClientCharacter> {

    /**
     * Method write receives an object of type ClientCharacter and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param clientCharacter the ClientCharacter I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientCharacter clientCharacter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Character");
        jsonWriter.name("CharacterName");
        jsonWriter.value(clientCharacter.getName());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded ClientCharacter as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return ClientCharacter the ClientCharacter created from the JSON values
     * @throws IOException if there is a problem with my input
     */
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
