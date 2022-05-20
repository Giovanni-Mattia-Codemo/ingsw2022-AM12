package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.model.ClientStudent;
import it.polimi.ingsw2022am12.client.model.ClientStudentCollection;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to simplify the handling of a certain event.
 * In this case,it helps to handle objects of the type ClientSTUDENTCOLLECTION.
 */
public class ClientStudentCollectionAdapter extends TypeAdapter<ClientStudentCollection> {

    /**
     * Method write receives an object of type ClientStudentCollection and serializes it in the JSON format
     *
     * @param jsonWriter the writer which will generate my JSON data
     * @param clientStudentCollection the ClientStudentCollection I want to serialize
     * @throws IOException if there is a problem with my input
     */
    @Override
    public void write(JsonWriter jsonWriter, ClientStudentCollection clientStudentCollection) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("tag");
        jsonWriter.value("StudentDiskCollection");
        jsonWriter.name("ID");
        jsonWriter.value(clientStudentCollection.getID());
        jsonWriter.endObject();
    }

    /**
     * Method "read" reads a JSON encoded ClientStudentCollection as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return ClientStudentCollection the ClientStudentCollection created from the JSON values
     * @throws IOException if there is a problem with my input
     */
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
                reader.peek();
                student.setID(reader.nextInt());

            }
            if ("Students".equals(fieldName)) {
                reader.peek();

                ArrayList<ClientStudent> students = new ArrayList<>();
                reader.beginArray();
                while (reader.hasNext()){
                    ClientStudent st = new GsonBuilder().registerTypeAdapter(ClientStudent.class, new ClientStudentAdapter()).create().fromJson(reader, ClientStudent.class);
                    st.setID(student.getID());
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
