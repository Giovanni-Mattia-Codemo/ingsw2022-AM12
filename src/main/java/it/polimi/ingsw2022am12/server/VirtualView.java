package it.polimi.ingsw2022am12.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import it.polimi.ingsw2022am12.server.adapter.StudentAdapter;
import it.polimi.ingsw2022am12.server.controller.Controller;
import it.polimi.ingsw2022am12.server.model.Selectable;
import it.polimi.ingsw2022am12.server.model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Map;
import java.util.Scanner;

public class VirtualView implements Runnable{
    private final Socket socket;
    private final Controller myController;

    public VirtualView(Socket socket, Controller newController){
        this.socket = socket;
        this.myController = newController;
    }

    public void forwardMsg(JsonElement gameState){
        try {
            PrintWriter out1 = new PrintWriter(socket.getOutputStream());
            out1.println(gameState);
            out1.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{

           // gsonBuilder = new GsonBuilder(Selectable.class, new SelectableAdapter());

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while (true){
                String line = in.next();

                Gson gson = new Gson();
                Map map = gson.fromJson(line, Map.class);
                String tag =(String) map.get("tag");
                map.remove("tag");
                String res = gson.toJson(map);
                switch(tag){
                    case "Nick":
                        String nick = (String) map.get("nick");

                        break;
                    case "Student":
                        GsonBuilder builder = new GsonBuilder();
                        builder.registerTypeAdapter(Student.class, new StudentAdapter());
                        gson = builder.create();
                        Student student = gson.fromJson(res, Student.class);
                        myController.send(this, student);
                        break;
                }
                //{color=RED, positionID=1.0}
               // Selectable selectable = gson.fromJson(line, Selectable.class);
               // myController.send(selectable);
            }

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
