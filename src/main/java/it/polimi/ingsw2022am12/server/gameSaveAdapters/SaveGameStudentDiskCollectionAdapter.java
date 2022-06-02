package it.polimi.ingsw2022am12.server.gameSaveAdapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.adapter.StudentAdapter;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;

import java.io.IOException;
import java.util.ArrayList;

public class SaveGameStudentDiskCollectionAdapter extends TypeAdapter<StudentDiskCollection> {

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
        ArrayList<Student> students = null;
        int id = 0;

        while(jsonReader.hasNext()){
            JsonToken token = jsonReader.peek();
            if(token.equals(JsonToken.NAME)){
                fieldName = jsonReader.nextName();
            }

            if("ID".equals(fieldName)){
                id = jsonReader.nextInt();
            }
            if("Students".equals(fieldName)){
                jsonReader.peek();
                students = new ArrayList<>();
                jsonReader.beginArray();
                while (jsonReader.hasNext()){
                    Student st = new GsonBuilder().registerTypeAdapter(Student.class, new StudentAdapter()).create().fromJson(jsonReader, Student.class);
                    st.setPositionID(id);
                    students.add(st);

                }
                jsonReader.endArray();
            }

        }
        jsonReader.endObject();
        return new StudentDiskCollection(students,id);
    }
}