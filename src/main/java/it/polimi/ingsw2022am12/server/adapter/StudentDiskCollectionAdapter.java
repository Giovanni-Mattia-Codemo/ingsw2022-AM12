package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
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
        return null;
    }
}
