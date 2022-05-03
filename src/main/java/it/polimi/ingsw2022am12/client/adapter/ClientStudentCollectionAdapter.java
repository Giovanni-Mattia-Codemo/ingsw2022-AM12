package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientStudentCollection;

import java.io.IOException;
import java.util.ArrayList;

public class ClientStudentCollectionAdapter extends TypeAdapter<ClientStudentCollection> {

    @Override
    public void write(JsonWriter jsonWriter, ClientStudentCollection clientStudentCollection) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("StudentDiskCollection");
        jsonWriter.name("ID");
        jsonWriter.value(clientStudentCollection.getID());
        jsonWriter.endObject();
    }

    @Override
    public ClientStudentCollection read(JsonReader reader) throws IOException {
        ClientStudentCollection student = new ClientStudentCollection();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if("ID".equals(fieldName)) {
                token = reader.peek();
                student.setID(reader.nextInt());
            }
            if ("Students".equals(fieldName)) {
                token = reader.peek();

                ArrayList<ClientStudent> students = new ArrayList<>();
                reader.beginArray();
                while (reader.hasNext()){
                    ClientStudent st = new GsonBuilder().registerTypeAdapter(ClientStudent.class, new ClientStudentAdapter()).create().fromJson(reader, ClientStudent.class);
                    students.add(st);

                }
                reader.endArray();
                student.setStudents(students);
            }


        }
        reader.endObject();
        return student;
    }
}
