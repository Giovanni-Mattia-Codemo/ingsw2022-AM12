package it.polimi.ingsw2022am12.client.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.client.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClientSchoolBoardAdapter extends TypeAdapter<ClientSchoolBoard> {

    @Override
    public void write(JsonWriter jsonWriter, ClientSchoolBoard clientSchoolBoard) throws IOException {

    }

    @Override
    public ClientSchoolBoard read(JsonReader reader) throws IOException {
        ClientSchoolBoard clientSchoolBoard = new ClientSchoolBoard();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if("Nick".equals(fieldName)) {
                reader.peek();
                clientSchoolBoard.setNick(reader.nextString());
            }
            if ("Entrance".equals(fieldName)) {
                reader.peek();
                Gson gson = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create();
                ClientStudentCollection tmp = gson.fromJson(reader, ClientStudentCollection.class);
                clientSchoolBoard.setEntrance(tmp);
            }
            if ("DiningRoom".equals(fieldName)) {
                reader.peek();
                Gson gson = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create();
                ClientStudentCollection tmp = gson.fromJson(reader, ClientStudentCollection.class);
                clientSchoolBoard.setDiningRooms(tmp);
            }
            if ("Coins".equals(fieldName)) {
                reader.peek();

                clientSchoolBoard.setCoins(reader.nextInt());
            }
            if ("Towers".equals(fieldName)) {
                reader.peek();

                clientSchoolBoard.setTowers(reader.nextInt());
            }
            if ("Mage".equals(fieldName)) {
                reader.peek();
                clientSchoolBoard.setMage(reader.nextInt());
            }
            if ("LastPlayedAssistant".equals(fieldName)) {
                reader.peek();
                clientSchoolBoard.setPlayedAssistant(reader.nextInt());
            }
            if ("Assistants".equals(fieldName)) {
                reader.peek();

                ArrayList<ClientAssistant> assistants = new ArrayList<>();
                reader.beginArray();
                while(reader.hasNext()){
                    ClientAssistant assistant = new GsonBuilder().registerTypeAdapter(ClientAssistant.class, new ClientAssistantAdapter()).create().fromJson(reader, ClientAssistant.class);
                    assistants.add(assistant);
                }
                reader.endArray();

                clientSchoolBoard.setAssistants(assistants);
            }
        }
        reader.endObject();
        return clientSchoolBoard;
    }
}
