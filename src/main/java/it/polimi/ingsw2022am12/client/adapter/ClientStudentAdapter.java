package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientStudent;

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
    public ClientStudent read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
