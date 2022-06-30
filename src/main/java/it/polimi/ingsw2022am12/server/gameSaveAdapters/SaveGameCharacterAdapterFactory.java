package it.polimi.ingsw2022am12.server.gameSaveAdapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.adapter.StudentDiskCollectionAdapter;
import it.polimi.ingsw2022am12.server.model.CharacterCard;
import it.polimi.ingsw2022am12.server.model.StudentDiskCollection;
import it.polimi.ingsw2022am12.server.model.characters.*;

import java.io.IOException;

/**
 *  * Json adapter class of CharacterCard's class. Used to save the game on disk.
 */
public class SaveGameCharacterAdapterFactory implements TypeAdapterFactory {

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
            jsonWriter.name("WasUsed");
            jsonWriter.value(characterCard.getWasUsed());
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
                    jsonWriter.name("MovesDone");
                    jsonWriter.value(((CharacterJester)characterCard).getMovesDone());
                }
                case "CHARACTER_MONK" -> {
                    jsonWriter.name("Students");
                    gson.toJson(gson.toJsonTree(((CharacterMonk) characterCard).getStudents()), jsonWriter);
                }
                case "CHARACTER_PRINCESS" -> {
                    jsonWriter.name("Students");
                    gson.toJson(gson.toJsonTree(((CharacterPrincess) characterCard).getStudents()), jsonWriter);
                }
                case "CHARACTER_BARD" ->{
                    jsonWriter.name("SwapsDone");
                    jsonWriter.value(((CharacterBard)characterCard).getSwapsDone());
                }
                case "CHARACTER_MERCHANT" ->{
                    jsonWriter.name("Color");
                    DiskColor color = ((CharacterMerchant)characterCard).getColor();
                    if(color !=null){
                        jsonWriter.value(String.valueOf(color));
                    }else{
                        jsonWriter.value("null");
                    }

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
         * @throws IOException if there is a problem with my input
         */
        @Override
        public CharacterCard read(JsonReader in) throws IOException {
            in.beginObject();
            String fieldName = null;
            CharacterCard characterCard = null;
            boolean wasUsed=false, hasCoin=false;
            int cost=0, entryNum=0, entryId=0, swapsDone=0, movesDone=0;
            String name=null, color=null;
            StudentDiskCollection students = null;

            while (in.hasNext()) {
                JsonToken token = in.peek();

                if (token.equals(JsonToken.NAME)) {
                    fieldName = in.nextName();
                }
                if("HasCoin".equals(fieldName)){
                    in.peek();
                    hasCoin = in.nextBoolean();
                }
                if("CharacterName".equals(fieldName)) {
                    in.peek();
                    name = in.nextString();
                }
                if("Color".equals(fieldName)) {
                    in.peek();
                    color = in.nextString();
                }
                if("Cost".equals(fieldName)){
                    in.peek();
                    cost = in.nextInt();
                }
                if("MovesDone".equals(fieldName)){
                    in.peek();
                    movesDone = in.nextInt();
                }
                if("SwapsDone".equals(fieldName)){
                    in.peek();
                    swapsDone = in.nextInt();
                }
                if("WasUsed".equals(fieldName)){
                    in.peek();
                    wasUsed = in.nextBoolean();
                }
                if("EntryNum".equals(fieldName)){
                    in.peek();
                    entryNum = in.nextInt();
                }
                if("EntryId".equals(fieldName)){
                    in.peek();
                    entryId = in.nextInt();
                }
                if("Students".equals(fieldName)){
                    in.peek();
                    students = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new SaveGameStudentDiskCollectionAdapter()).create().fromJson(in, StudentDiskCollection.class);
                }

            }
            in.endObject();

            if(name!=null){
                switch (name) {
                    case "CHARACTER_HERBALIST" -> characterCard = new CharacterHerbalist(entryNum, entryId);
                    case "CHARACTER_JESTER" -> characterCard = new CharacterJester(students, movesDone);
                    case "CHARACTER_MONK" -> characterCard = new CharacterMonk(students);
                    case "CHARACTER_PRINCESS" -> characterCard = new CharacterPrincess(students);
                    case "CHARACTER_BARD" -> characterCard = new CharacterBard(swapsDone);
                    case "CHARACTER_HAG" -> characterCard = new CharacterHag();
                    case "CHARACTER_HERALD" -> characterCard = new CharacterHerald();
                    case "CHARACTER_HOST" -> characterCard = new CharacterHost();
                    case "CHARACTER_CENTAUR" -> characterCard = new CharacterCentaur();
                    case "CHARACTER_KNIGHT" -> characterCard = new CharacterKnight();
                    case "CHARACTER_MERCHANT" -> {
                        characterCard = new CharacterMerchant();
                        if(!color.equals("null")){
                            ((CharacterMerchant)characterCard).setColor(DiskColor.valueOf(color));
                        }
                    }
                    case "CHARACTER_BEGGAR" -> characterCard = new CharacterBeggar();
                    default -> {}
                }
            }

            if(characterCard!=null){
                characterCard.setCost(cost);
                characterCard.setUsage(wasUsed);
                if(hasCoin){
                    characterCard.setAdditionalCoins();
                }
                return characterCard;
            }else return null;

        }

    };

}

