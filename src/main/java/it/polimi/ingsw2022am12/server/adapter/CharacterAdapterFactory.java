package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import it.polimi.ingsw2022am12.server.model.characters.*;

import java.io.IOException;

public class CharacterAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if(CharacterCard.class.isAssignableFrom(typeToken.getRawType())) {
            return (TypeAdapter<T>) customTypeAdapter;
        }
        return null;
    }

    private TypeAdapter<CharacterCard> customTypeAdapter = new TypeAdapter<CharacterCard>() {

        @Override
        public void write(JsonWriter jsonWriter, CharacterCard characterCard) throws IOException {
            jsonWriter.beginObject();
            jsonWriter.name("CharacterName");
            jsonWriter.value(characterCard.getName().toString());
            jsonWriter.name("HasCoin");

            if(characterCard.getCoin()){
                jsonWriter.value("true");
            }else jsonWriter.value("false");
            jsonWriter.name("Cost");
            jsonWriter.value(characterCard.getCost());
            Gson gson = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter()).create();

            switch (characterCard.getName().toString()) {
                case "CHARACTER_HERBALIST" -> {
                    jsonWriter.name("EntryNum");
                    jsonWriter.value(((CharacterHerbalist) characterCard).getNoEntryCollection().noEntriesSize());
                    jsonWriter.name("EntryId");
                    jsonWriter.value(((CharacterHerbalist) characterCard).getNoEntryCollection().getMyId());
                }
                case "CHARACTER_JESTER" -> {
                    jsonWriter.name("Students");
                    gson.toJson(gson.toJsonTree(((CharacterJester) characterCard).getStudents()), jsonWriter);
                }
                case "CHARACTER_MONK" -> {
                    jsonWriter.name("Students");
                    gson.toJson(gson.toJsonTree(((CharacterMonk) characterCard).getStudents()), jsonWriter);
                }
                case "CHARACTER_PRINCESS" -> {
                    jsonWriter.name("Students");
                    gson.toJson(gson.toJsonTree(((CharacterPrincess) characterCard).getStudents()), jsonWriter);
                }
                default -> {
                }
            }

            jsonWriter.endObject();
        }

        @Override
        public CharacterCard read(JsonReader in) throws IOException {
            in.beginObject();
            String fieldName = null;
            CharacterCard characterCard = null;

            while (in.hasNext()) {
                JsonToken token = in.peek();

                if (token.equals(JsonToken.NAME)) {
                    fieldName = in.nextName();
                }

                if("CharacterName".equals(fieldName)) {
                    token = in.peek();
                    String name = in.nextString();
                    switch (name) {
                        case "CHARACTER_HERBALIST" -> characterCard = new CharacterHerbalist();
                        case "CHARACTER_JESTER" -> characterCard = new CharacterJester();
                        case "CHARACTER_MONK" -> characterCard = new CharacterMonk();
                        case "CHARACTER_PRINCESS" -> characterCard = new CharacterPrincess();
                        case "CHARACTER_BARD" -> characterCard = new CharacterBard();
                        case "CHARACTER_HAG" -> characterCard = new CharacterHag();
                        case "CHARACTER_HERALD" -> characterCard = new CharacterHerald();
                        case "CHARACTER_HOST" -> characterCard = new CharacterHost();
                        case "CHARACTER_CENTAUR" -> characterCard = new CharacterCentaur();
                        case "CHARACTER_KNIGHT" -> characterCard = new CharacterKnight();
                        case "CHARACTER_MERCHANT" -> characterCard = new CharacterMerchant();
                        case "CHARACTER_BEGGAR" -> characterCard = new CharacterBeggar();
                        default -> {}
                    }
                }

            }
            in.endObject();
            return characterCard;
        }

    };

}
