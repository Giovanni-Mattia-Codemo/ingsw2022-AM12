package it.polimi.ingsw2022am12.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import it.polimi.ingsw2022am12.server.adapter.CharacterAdapter;
import it.polimi.ingsw2022am12.server.adapter.NoEntryAdapter;
import it.polimi.ingsw2022am12.server.adapter.StudentAdapter;
import it.polimi.ingsw2022am12.server.adapter.StudentDiskCollectionAdapter;
import it.polimi.ingsw2022am12.server.controller.Controller;
import it.polimi.ingsw2022am12.server.model.*;

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
                String msg = "";
                switch(tag){
                    case "Nick":
                        String nick = (String) map.get("nick");
                        ControlMessages messages = myController.selectUsername(nick, this);
                        msg = msg.concat(messages.getMessage());
                        break;
                    case "Student":
                        GsonBuilder builder = new GsonBuilder();
                        builder.registerTypeAdapter(Student.class, new StudentAdapter());
                        gson = builder.create();
                        Student student = gson.fromJson(res, Student.class);
                        msg = myController.send(this, student);
                        break;
                    case "StudentDiskCollection":
                        gson = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter()).create();
                        StudentDiskCollection studentDiskCollection = gson.fromJson(res, StudentDiskCollection.class);
                        msg = myController.send(this, studentDiskCollection);
                        break;

                    case "NoEntry":
                        gson = new GsonBuilder().registerTypeAdapter(NoEntry.class, new NoEntryAdapter()).create();
                        NoEntry noEntry = gson.fromJson(res, NoEntry.class);
                        msg = myController.send(this, noEntry);
                        break;
                    case "Character":
                        gson = new GsonBuilder().registerTypeAdapter(CharacterCard.class, new CharacterAdapter()).create();
                        CharacterCard characterCard= gson.fromJson(res, CharacterCard.class);
                        msg = myController.send(this, characterCard);
                        break;

                    case "Island":
                        gson = new GsonBuilder().registerTypeAdapter(NoEntry.class, new NoEntryAdapter()).create();
                        IslandTileSet islandTileSet = gson.fromJson(res, IslandTileSet.class);
                        msg = myController.send(this, islandTileSet);
                        break;

                    case "Color":
                        DiskColor color = (DiskColor) map.get("color");
                        msg = myController.send(this, new ColorSelection(color));

                        break;

                    case "InputMode":
                        int num = (int)map.get("number");
                        boolean b = (boolean) map.get("mode");
                        msg = (myController.setGameMode(this, num, b)).getMessage();

                        break;

                    default:
                        msg = "Unrecognized input"+"\n";
                        break;
                }
                out.print(msg);
                out.flush();
            }

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
