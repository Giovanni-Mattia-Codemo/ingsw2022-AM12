package it.polimi.ingsw2022am12.server.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import it.polimi.ingsw2022am12.server.model.characters.*;

import java.io.IOException;

/**
 *
 * Json factory adapter class of CharacterCard's class
 *
 */
public class CharacterAdapterFactory implements TypeAdapterFactory {

    /**
     * Custom creation of a TypeAdapter
     * @param gson my Gson object
     * @param typeToken my type token
     * @param <T> generic type for the TypeAdapter
     * @return custom type adapter
     */
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if(CharacterCard.class.isAssignableFrom(typeToken.getRawType())) {
            return (TypeAdapter<T>) customTypeAdapter;
        }
        return null;
    }

    private final TypeAdapter<CharacterCard> customTypeAdapter = new TypeAdapter<>() {

        /**
         * Method write receives an object of type CharacterCard and serializes it in the JSON format
         *
         * @param jsonWriter the writer which will generate my JSON data
         * @param characterCard the CharacterCard I want to serialize
         * @throws IOException if there is a problem with my input
         */
        @Override
        public void write(JsonWriter jsonWriter, CharacterCard characterCard) throws IOException {
            jsonWriter.beginObject();
            jsonWriter.name("CharacterName");
            jsonWriter.value(characterCard.getName().toString());
            jsonWriter.name("HasCoin");
            jsonWriter.value(characterCard.getCoin());
            jsonWriter.name("Cost");
            jsonWriter.value(characterCard.getCost());
            Gson gson = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter()).create();

            switch (characterCard.getName()) {
                case CHARACTER_HERBALIST -> {
                    jsonWriter.name("EntryNum");
                    jsonWriter.value(((CharacterHerbalist) characterCard).getNoEntryCollection().noEntriesSize());
                    jsonWriter.name("EntryId");
                    jsonWriter.value(((CharacterHerbalist) characterCard).getNoEntryCollection().getMyId());
                }
                case CHARACTER_JESTER -> {
                    jsonWriter.name("Students");
                    gson.toJson(gson.toJsonTree(((CharacterJester) characterCard).getStudents()), jsonWriter);
                }
                case CHARACTER_MONK -> {
                    jsonWriter.name("Students");
                    gson.toJson(gson.toJsonTree(((CharacterMonk) characterCard).getStudents()), jsonWriter);
                }
                case CHARACTER_PRINCESS -> {
                    jsonWriter.name("Students");
                    gson.toJson(gson.toJsonTree(((CharacterPrincess) characterCard).getStudents()), jsonWriter);
                }
                default -> {
                }
            }

            jsonWriter.endObject();
        }

        /**
         * Method "read" reads a JSON encoded CharacterCard as a stream of tokens, and uses a switch on the enum
         * CHARACTERNAME
         *
         * @param in the reader which will receive my JSON data
         * @return the CharacterCard created from the JSON values
         * @throws IOException the reader which will receive my JSON data
         */
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
                    in.peek();
                    String name = in.nextString();

                    switch (CharacterName.valueOf(name)) {
                        case CHARACTER_HERBALIST -> characterCard = new CharacterHerbalist();
                        case CHARACTER_JESTER -> characterCard = new CharacterJester();
                        case CHARACTER_MONK -> characterCard = new CharacterMonk();
                        case CHARACTER_PRINCESS -> characterCard = new CharacterPrincess();
                        case CHARACTER_BARD -> characterCard = new CharacterBard();
                        case CHARACTER_HAG -> characterCard = new CharacterHag();
                        case CHARACTER_HERALD -> characterCard = new CharacterHerald();
                        case CHARACTER_HOST -> characterCard = new CharacterHost();
                        case CHARACTER_CENTAUR -> characterCard = new CharacterCentaur();
                        case CHARACTER_KNIGHT -> characterCard = new CharacterKnight();
                        case CHARACTER_MERCHANT -> characterCard = new CharacterMerchant();
                        case CHARACTER_BEGGAR -> characterCard = new CharacterBeggar();
                        default -> {}
                    }
                }

            }
            in.endObject();

            return characterCard;
        }

    };

}
