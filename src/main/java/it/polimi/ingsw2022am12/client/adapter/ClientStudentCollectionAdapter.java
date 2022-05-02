package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.ClientStudentCollection;

import java.io.IOException;

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
    public ClientStudentCollection read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
