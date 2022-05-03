package it.polimi.ingsw2022am12.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.server.adapter.*;
import it.polimi.ingsw2022am12.server.controller.Controller;
import it.polimi.ingsw2022am12.server.model.*;

import java.util.Map;
import java.util.Scanner;

public class VirtualViewMessagesParser implements Runnable{

    private VirtualView virtualView;
    private Scanner in;
    private Controller myController;

    public VirtualViewMessagesParser(VirtualView v, Scanner in, Controller controller){
        virtualView= v;
        this.in = in;
        myController = controller;
    }

    @Override
    public void run() {



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
                    ControlMessages messages = myController.selectUsername(nick, virtualView);
                    msg = msg.concat(messages.getMessage());
                    System.out.println("i handled and answered the player username");
                    break;
                case "Student":
                    GsonBuilder builder = new GsonBuilder();
                    builder.registerTypeAdapter(Student.class, new StudentAdapter());
                    gson = builder.create();
                    Student student = gson.fromJson(res, Student.class);
                    msg = myController.send(virtualView, student);
                    break;
                case "StudentDiskCollection":
                    gson = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter()).create();
                    StudentDiskCollection studentDiskCollection = gson.fromJson(res, StudentDiskCollection.class);
                    msg = myController.send(virtualView, studentDiskCollection);
                    break;

                case "NoEntry":
                    gson = new GsonBuilder().registerTypeAdapter(NoEntry.class, new NoEntryAdapter()).create();
                    NoEntry noEntry = gson.fromJson(res, NoEntry.class);
                    msg = myController.send(virtualView, noEntry);
                    break;

                case "Character":
                    gson = new GsonBuilder().registerTypeAdapterFactory(new CharacterAdapterFactory()).create();
                    CharacterCard characterCard= gson.fromJson(res, CharacterCard.class);
                    msg = myController.send(virtualView, characterCard);
                    break;

                case "Island":
                    gson = new GsonBuilder().registerTypeAdapter(IslandTileSet.class, new IslandTileSetAdapter()).create();
                    IslandTileSet islandTileSet = gson.fromJson(res, IslandTileSet.class);
                    msg = myController.send(virtualView, islandTileSet);
                    break;

                case "Color":
                    DiskColor color = (DiskColor) map.get("color");
                    msg = myController.send(virtualView, new ColorSelection(color));

                    break;

                case "InputMode":
                    System.out.println("inside input mode");
                    int num = (int) Math.round((double)map.get("number"));
                    System.out.println("lanimaccia");
                    boolean b = (boolean) map.get("mode");
                    System.out.println("after mapping");
                    msg = (myController.setGameMode(virtualView, num, b)).getMessage();
                    System.out.println("set the game");
                    break;

                case "Mage":
                    int id = (int) Math.round((double)map.get("ID"));
                    Mage mage = new Mage(id);
                    msg = (myController.send(virtualView, mage));
                    break;

                case "Assistant":
                    int turnPower = (int) Math.round((double)map.get("TurnPower"));
                    Assistant assistant =  AssistantCreator.createAssistant(turnPower);
                    msg = (myController.send(virtualView, assistant));
                    break;

                default:
                    msg = "Unrecognized input"+"\n";
                    break;
            }
            virtualView.forwardMsg(msg);
        }
    }
}
