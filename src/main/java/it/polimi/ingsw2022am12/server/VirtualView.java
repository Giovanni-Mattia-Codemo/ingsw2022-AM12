package it.polimi.ingsw2022am12.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.server.adapter.CharacterAdapterFactory;
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

/**
 * VirtualView class represents the layer that transforms information that come from the client and sends them to the
 * controller inside the server
 */
public class VirtualView implements Runnable{
    private final Socket socket;
    private final Controller myController;

    /**
     * constructor method of VirtualView class
     * @param socket the socket associated to my VirtualView
     * @param newController the controller associated to my virtualView
     */
    public VirtualView(Socket socket, Controller newController){
        this.socket = socket;
        this.myController = newController;
    }

    /**
     * forwardMsg puts the gameState string as a message in the output stream of the socket, then flushes it
     * @param gameState the state of the game coded as a string
     *
     */
    public void forwardMsg(String gameState){
        try {
            PrintWriter out1 = new PrintWriter(socket.getOutputStream());
            out1.println(gameState);
            out1.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * void run is the method executed by the various threads; it receives information from the client, and deserializes it,
     * sending it to the Controller
     */
    public void run(){
        try{

           // gsonBuilder = new GsonBuilder(Selectable.class, new SelectableAdapter());

            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            System.out.println("Im alive");
            while (true){
                String line = in.next();
                System.out.println("working on this input:"+line);

                Gson gson = new Gson();
                Map map = gson.fromJson(line, Map.class);
                System.out.println("managed to extract a map");
                String tag =(String) map.get("tag");
                System.out.println("    and got the tag");
                map.remove("tag");
                String res = gson.toJson(map);
                System.out.println(" and removed it: "+res);
                String msg = "";
                switch(tag){
                    case "Nick":
                        System.out.println("t'was indeed a nick");
                        String nick = (String) map.get("nick");
                        ControlMessages messages = myController.selectUsername(nick, this);
                        msg = msg.concat(messages.getMessage());
                        System.out.println("i handled and answered the player username");
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
                        gson = new GsonBuilder().registerTypeAdapterFactory(new CharacterAdapterFactory()).create();
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
                        System.out.println("inside input mode");
                        int num = (int) Math.round((double)map.get("number"));
                        System.out.println("lanimaccia");
                        boolean b = (boolean) map.get("mode");
                        System.out.println("after mapping");
                        msg = (myController.setGameMode(this, num, b)).getMessage();
                        System.out.println("set the game");
                        break;

                    default:
                        msg = "Unrecognized input"+"\n";
                        break;
                }
                out.println(msg);
                out.flush();
            }

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
