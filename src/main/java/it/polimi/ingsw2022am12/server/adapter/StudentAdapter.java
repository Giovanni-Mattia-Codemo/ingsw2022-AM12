package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.model.Student;
import java.io.IOException;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type STUDENT.
 */
public class StudentAdapter extends TypeAdapter<Student> {

    /**
     * Method write receives an object of type Student and serializes it in the JSON format
     *
     * @param writer the writer which will generate my JSON data
     * @param foo the Student I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter writer, Student foo) throws IOException {
        writer.beginObject();
        writer.name("color");
        writer.value(String.valueOf(foo.getColor()));
        writer.endObject();
    }

    /**
     * Method "read" reads a JSON encoded Student as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return Student the Student created from the JSON values
     * @throws IOException if there is a problem with my input
     */
    @Override
    public Student read(JsonReader reader) throws IOException {
        Student student = new Student();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }
            if("positionID".equals(fieldName)) {
                reader.peek();
                student.setPositionID(reader.nextInt());
            }
            if ("color".equals(fieldName)) {
                reader.peek();
                student.setColor(DiskColor.valueOf(reader.nextString()));
            }
        }
        reader.endObject();
        return student;
    }
}
