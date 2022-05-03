package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientStudent;
import it.polimi.ingsw2022am12.server.model.DiskColor;
import it.polimi.ingsw2022am12.server.model.Student;

import java.io.IOException;

public class ClientStudentAdapter extends TypeAdapter<ClientStudent> {

    @Override
    public void write(JsonWriter jsonWriter, ClientStudent clientStudent) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("Student");
        jsonWriter.name("color");
        jsonWriter.value(clientStudent.getColor());
        jsonWriter.name("positionID");
        jsonWriter.value(clientStudent.getID());
        jsonWriter.endObject();
    }

    @Override
    public ClientStudent read(JsonReader reader) throws IOException {

        ClientStudent student = null;
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldName = reader.nextName();
            }

            if ("color".equals(fieldName)) {
                //move to next token
                token = reader.peek();
                student = new ClientStudent();
                student.setColor(reader.nextString());
            }


        }
        reader.endObject();
        return student;
    }
}
