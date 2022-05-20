package it.polimi.ingsw2022am12;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;





public class UpdateFlagAdapterFactory implements TypeAdapterFactory {

    /**
     * Custom creation of a TypeAdapter
     * @param gson my Gson object
     * @param typeToken my type token
     * @param <T> generic type for the TypeAdapter
     * @return custom type adapter
     */
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if(UpdateFlag.class.isAssignableFrom(typeToken.getRawType())) {
            return (TypeAdapter<T>) customTypeAdapter;
        }
        return null;
    }

    private final TypeAdapter<UpdateFlag> customTypeAdapter = new TypeAdapter<>() {

   //     private final TypeAdapter<UpdateFlag> customTypeAdapter = new  TypeAdapter<>{

        /**
         * Method write receives an object of type CharacterCard and serializes it in the JSON format
         *
         * @param jsonWriter the writer which will generate my JSON data
         * @param flag to be serialized
         * @throws IOException if there is a problem with my input
         */
        @Override
        public void write(JsonWriter jsonWriter, UpdateFlag flag) throws IOException {
            System.out.println("inside the write");
            jsonWriter.beginObject();
            jsonWriter.name("tag");
            jsonWriter.value("UpdateFlag");
            jsonWriter.name("FlagName");
            jsonWriter.value(flag.getFlag().name());
            System.out.println("switching on name");
            switch (flag.getFlag().name()) {
                case "SCHOOL" -> {
                    System.out.println("serializing a school flag");
                    jsonWriter.name("School");
                    jsonWriter.value(((UpdateFlagSchool)flag).getNick());
                }
                case "CHARACTERS" -> {
                    jsonWriter.name("Characters");
                    jsonWriter.value(((UpdateFlagCharacter)flag).getNick());
                }
                default -> {
                }
            }
            System.out.println("done");
            jsonWriter.endObject();
        }

    /**
     * Method "read" reads a JSON encoded UpdateFlag as a stream of tokens
     *
     * @param reader the reader which will receive my JSON data
     * @return UpdateFlag read from the JSON text
     * @throws IOException if there is a problem with my input
     */
    @Override
    public UpdateFlag read(JsonReader reader) throws IOException {
        System.out.println("reading an update flag");
        String name = null;
        String playerNick = null;
        String characterName = null;
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if("FlagName".equals(fieldName)) {
                reader.peek();
                name = (reader.nextString());
            }
            if("School".equals(fieldName)){
                reader.peek();
                playerNick = reader.nextString();
            }
            if("Characters".equals(fieldName)){
                reader.peek();
                characterName = reader.nextString();
            }

        }
        reader.endObject();
        System.out.println("received name:"+name);
        switch (Objects.requireNonNull(name)){
            case "FULLGAME" -> {
                System.out.println("In FULLGAME flag");
                return new UpdateFlag(Flag.FULLGAME);
            }
            case "ISLANDS" -> {
                return new UpdateFlag(Flag.ISLANDS);
            }
            case "CLOUDS" -> {
                return new UpdateFlag(Flag.CLOUDS);
            }
            case "SCHOOL" -> {
                return new UpdateFlagSchool(playerNick);
            }
            case "CHARACTERS" -> {
                return new UpdateFlagCharacter(characterName);
            }
            default -> {
                return null;
            }
        }
    }

};
}
