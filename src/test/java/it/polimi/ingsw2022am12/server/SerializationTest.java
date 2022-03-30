package it.polimi.ingsw2022am12.server;
import it.polimi.ingsw2022am12.server.model.DiskColor;
import it.polimi.ingsw2022am12.server.model.Student;
import it.polimi.ingsw2022am12.server.adapter.StudentAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.google.gson.*;

public class SerializationTest {



    @Test
    public void test(){


        Student foo = new Student();
        foo.setColor(DiskColor.RED);
        foo.setPositionID(1);

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Student.class, new StudentAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String jsonString;
        jsonString = gson.toJson(foo);
        System.out.println(jsonString);

        Student foo2= gson.fromJson(jsonString, Student.class);
        System.out.println(foo2);
        System.out.println(foo);

        System.out.println("step 2...");

        foo.setPositionID(4);
        jsonString = gson.toJson(foo);
        System.out.println(jsonString);



        Assertions.assertTrue(foo2 instanceof Student);
    }

}