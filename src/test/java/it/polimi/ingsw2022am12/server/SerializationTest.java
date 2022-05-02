package it.polimi.ingsw2022am12.server;
import it.polimi.ingsw2022am12.server.model.*;
import it.polimi.ingsw2022am12.server.adapter.StudentAdapter;
import org.junit.jupiter.api.Test;
import com.google.gson.*;

import java.util.Map;

/**
 * SerializationTest is a class made to try out the methods in the serialization libraries
 */
public class SerializationTest {

/*
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

        /*
        String x= (String) map.get("color");
        System.out.println(x);
        System.out.println(foo2);
        System.out.println(foo);
        System.out.println("step 2...");
        foo.setPositionID(4);
        jsonString = gson.toJson(foo);
        System.out.println(jsonString);

    }

    */

}