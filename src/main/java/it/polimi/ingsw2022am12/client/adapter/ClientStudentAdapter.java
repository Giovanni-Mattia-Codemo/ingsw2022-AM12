package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.client.model.ClientStudent;
import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type ClientSTUDENT.
 */
public class ClientStudentAdapter extends TypeAdapter<ClientStudent> {

    /**
     * Method write receives an object of type ClientStudent and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param clientStudent the ClientStudent I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientStudent clientStudent) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Student");
        jsonWriter.name("color");
        jsonWriter.value(String.valueOf(clientStudent.getColor()));
        jsonWriter.name("positionID");
        jsonWriter.value(clientStudent.getID());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded ClientStudent as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return ClientStudent the ClientStudent created from the JSON values
     * @throws IOException if there is a problem with my input
     */
    @Override
    public ClientStudent read(JsonReader reader) throws IOException {

        ClientStudent student = null;
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }
            if ("color".equals(fieldName)) {
                reader.peek();
                student = new ClientStudent();
                student.setColor(DiskColor.valueOf(reader.nextString()));
            }
        }
        reader.endObject();
        return student;
    }
}
