package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;

import java.io.IOException;

public class StudentDiskCollectionAdapter extends TypeAdapter<StudentDiskCollection> {

     Gson embedded = new Gson();

    @Override
    public void write(JsonWriter jsonWriter, StudentDiskCollection studentDiskCollection) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("ID");
        jsonWriter.value(studentDiskCollection.getID());
        jsonWriter.name("Students");
        GsonBuilder build = new GsonBuilder();
        build.registerTypeAdapter(Student.class, new StudentAdapter());
        embedded = build.create();
        embedded.toJson(embedded.toJsonTree(studentDiskCollection.getAllStudents()), jsonWriter);
        jsonWriter.endObject();
    }

    @Override
    public StudentDiskCollection read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        String fieldName = null;
        StudentDiskCollection studentDiskCollection = null;

        while(jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();
            if(token.equals(JsonToken.NAME)){
                fieldName = jsonReader.nextName();
            }

            if("ID".equals(fieldName)){
                studentDiskCollection = new StudentDiskCollection(jsonReader.nextInt());
            }

        }
        jsonReader.endObject();
        return studentDiskCollection;
    }
}
