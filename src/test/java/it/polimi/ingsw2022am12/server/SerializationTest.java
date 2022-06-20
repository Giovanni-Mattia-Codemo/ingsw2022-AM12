package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.communication.InputMode;
import it.polimi.ingsw2022am12.server.adapter.GameAdapter;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.adapter.StudentAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.google.gson.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * SerializationTest is a class made to try out the methods in the serialization libraries
 */
public class SerializationTest {

    /**
     *test() method is mainly used to test the main features of GSON and JSON and use them in a setup of a hypothetical game
     */
    @Test
    public void test(){

        Student foo = new Student();
        foo.setColor(DiskColor.RED);
        foo.setPositionID(1);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentAdapter());
        //builder.setPrettyPrinting();
        Gson gson = builder.create();

        String jsonString;
        jsonString = gson.toJson(foo);
        //System.out.println(jsonString);System.out.println(jsonString);
        Student foo2= gson.fromJson(jsonString, Student.class);

        Map map= gson.fromJson(jsonString, Map.class);


        Gson gson2 = new Gson();
        Map map2 = gson2.fromJson(jsonString, Map.class);
        map2.put("tag", "Student");
        map2.remove("tag");
        String line = gson.toJson(map2);


        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapter(Student.class, new StudentAdapter());
        gson = b.create();
        Student student = gson.fromJson(line, Student.class);
        Assertions.assertEquals(DiskColor.RED ,student.getColor());



        String s = " m";
        String t = " t";
        ArrayList<String> input = new ArrayList<>();
        input.add(s);
        input.add(t);

        Game newGame = new Game(input, true);

        newGame.setUp();

        newGame.getCurrentSchoolBoard().setMage(newGame.getAvailableMages().get(0));
        newGame.getTurnOrder().get(1).setMage(newGame.getAvailableMages().get(1));

        newGame.getCurrentSchoolBoard().playAssistant(2);
        newGame.getTurnOrder().get(1).playAssistant(1);

        GsonBuilder build = new GsonBuilder();
        build.registerTypeAdapter(Game.class, new GameAdapter());
        Gson newGson = build.serializeNulls().create();
        String output = newGson.toJson(newGame, Game.class);
        System.out.println(output);

    }



}