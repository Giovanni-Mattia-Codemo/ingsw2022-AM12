package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.DiskColor;
import it.polimi.ingsw2022am12.server.model.Student;
import java.io.IOException;

public class StudentAdapter extends TypeAdapter<Student> {

    @Override
    public void write(JsonWriter writer, Student foo) throws IOException {
        writer.beginObject();
        writer.name("color");
        writer.value(String.valueOf(foo.getColor()));
        writer.name("positionID");
        writer.value(foo.getPositionID());
        writer.endObject();
    }

    @Override
    public Student read(JsonReader reader) throws IOException {
        Student student = new Student();
        reader.beginObject();
        String fieldname = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                //get the current token
                fieldname = reader.nextName();
            }



            if("positionID".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                student.setPositionID(reader.nextInt());
            }
            if ("color".equals(fieldname)) {
                //move to next token
                token = reader.peek();
                student.setColor(DiskColor.valueOf(reader.nextString()));
            }


        }
        reader.endObject();
        return student;
    }
}
