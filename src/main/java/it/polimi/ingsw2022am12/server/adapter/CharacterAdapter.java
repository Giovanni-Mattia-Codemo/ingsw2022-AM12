package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.model.characters.CharacterHerbalist;
import it.polimi.ingsw2022am12.server.model.characters.CharacterJester;
import it.polimi.ingsw2022am12.server.model.characters.CharacterMonk;
import it.polimi.ingsw2022am12.server.model.characters.CharacterPrincess;

import java.io.IOException;

public class CharacterAdapter extends TypeAdapter<CharacterCard> {

    @Override
    public void write(JsonWriter jsonWriter, CharacterCard characterCard) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("CharacterName");
        jsonWriter.value(characterCard.getName().toString());
        jsonWriter.name("HasCoin");
        if(characterCard.getCoin()){
            jsonWriter.value("true");
        }else jsonWriter.value("false");
        //to be continued

        Gson gson = new GsonBuilder().registerTypeAdapter(Student.class, new StudentAdapter()).create();

        switch(characterCard.getName().toString()){
            case "Herbalist":
                jsonWriter.name("NoEntries");
                jsonWriter.value(((CharacterHerbalist)characterCard).getNoEntryCollection().noEntriesSize());
                break;

            case "Jester":
                jsonWriter.name("Students");

                gson.toJson(gson.toJsonTree(((CharacterJester)characterCard).getStudents()),jsonWriter);

                break;

            case "Monk":
                jsonWriter.name("Students");
                gson.toJson(gson.toJsonTree(((CharacterMonk)characterCard).getStudents()));
                break;

            case "Princess":
                jsonWriter.name("Students");
                gson.toJson(gson.toJsonTree(((CharacterPrincess)characterCard).getStudents()));
                break;

            default:
                break;
        }




        jsonWriter.endObject();
    }

    @Override
    public CharacterCard read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
