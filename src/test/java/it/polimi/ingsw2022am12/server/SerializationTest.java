package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.InputMode;
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
        System.out.println(map);


        Gson gson2 = new Gson();
        Map map2 = gson2.fromJson(jsonString, Map.class);
        map2.put("tag", "Student");
        map2.remove("tag");
        String line = gson.toJson(map2);
        System.out.println("\n "+ map2);

        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapter(Student.class, new StudentAdapter());
        gson = b.create();
        System.out.println("Ciao");
        Student student = gson.fromJson(line, Student.class);
        System.out.println("Ciao2");
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

        InputMode inputMode = new InputMode();
        //Gson gson1234 = new GsonBuilder().registerTypeAdapter(InputMode.class, new MessageAdapter()).create();
        //String outpu = gson1234.toJson(inputMode);
        //System.out.println(outpu);

        //Map myMap = gson1234.fromJson(outpu, Map.class);
        //String number =  myMap.get("number").toString();
        //System.out.println(number);




        /*
        StudentDiskCollection studentDiskCollection = new StudentDiskCollection();
        Student newStudent = new Student(DiskColor.RED);

        studentDiskCollection.insertElement(newStudent);
        newStudent = new Student(DiskColor.BLUE);
        studentDiskCollection.insertElement(newStudent);
        build.registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter());
        newGson = build.create();
        String output = newGson.toJson(studentDiskCollection, StudentDiskCollection.class);
        System.out.println(output);




        /*
        String x= (String) map.get("color");
        System.out.println(x);
        System.out.println(foo2);
        System.out.println(foo);
        System.out.println("step 2...");
        foo.setPositionID(4);
        jsonString = gson.toJson(foo);
        System.out.println(jsonString);

         */

    }



}