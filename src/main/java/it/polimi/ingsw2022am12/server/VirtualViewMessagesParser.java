package it.polimi.ingsw2022am12.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.server.adapter.*;
import it.polimi.ingsw2022am12.server.controller.Controller;
import it.polimi.ingsw2022am12.server.model.*;
import java.util.Map;
import java.util.Scanner;

public class VirtualViewMessagesParser implements Runnable{

    private final VirtualView virtualView;
    private final Scanner in;
    private final Controller myController;

    /**
     * Constructor method of VirtualViewMessagesParser
     * @param v my virtualView
     * @param in scanner of values in input
     * @param controller my controller
     */
    public VirtualViewMessagesParser(VirtualView v, Scanner in, Controller controller){
        virtualView= v;
        this.in = in;
        myController = controller;
    }

    @Override
    public void run() {

        while (true){
            String line = in.next();
            Gson gson = new Gson();
            Map map = gson.fromJson(line, Map.class);
            String tag =(String) map.get("tag");
            map.remove("tag");
            String res = gson.toJson(map);
            String msg = "";
            switch (tag) {
                case "Nick" -> {
                    String nick = (String) map.get("nick");
                    ControlMessages messages = myController.selectUsername(nick, virtualView);
                    msg = msg.concat(messages.getMessage());
                }
                case "Student" -> {
                    GsonBuilder builder = new GsonBuilder();
                    builder.registerTypeAdapter(Student.class, new StudentAdapter());
                    gson = builder.create();
                    Student student = gson.fromJson(res, Student.class);
                    msg = myController.send(virtualView, student);
                }
                case "StudentDiskCollection" -> {
                    gson = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter()).create();
                    StudentDiskCollection studentDiskCollection = gson.fromJson(res, StudentDiskCollection.class);
                    msg = myController.send(virtualView, studentDiskCollection);
                }
                case "NoEntry" -> {
                    gson = new GsonBuilder().registerTypeAdapter(NoEntry.class, new NoEntryAdapter()).create();
                    NoEntry noEntry = gson.fromJson(res, NoEntry.class);
                    msg = myController.send(virtualView, noEntry);
                }
                case "Character" -> {
                    gson = new GsonBuilder().registerTypeAdapterFactory(new CharacterAdapterFactory()).create();
                    CharacterCard characterCard = gson.fromJson(res, CharacterCard.class);
                    msg = myController.send(virtualView, characterCard);
                }
                case "Island" -> {
                    gson = new GsonBuilder().registerTypeAdapter(IslandTileSet.class, new IslandTileSetAdapter()).create();
                    IslandTileSet islandTileSet = gson.fromJson(res, IslandTileSet.class);
                    msg = myController.send(virtualView, islandTileSet);
                }
                case "Color" -> {
                    DiskColor color = (DiskColor) map.get("color");
                    msg = myController.send(virtualView, new ColorSelection(color));
                }
                case "InputMode" -> {
                    int num = (int) Math.round((double) map.get("number"));
                    boolean b = (boolean) map.get("mode");
                    msg = (myController.setGameMode(virtualView, num, b)).getMessage();
                }
                case "Mage" -> {
                    int id = (int) Math.round((double) map.get("ID"));
                    Mage mage = new Mage(id);
                    msg = (myController.send(virtualView, mage));
                }
                case "Assistant" -> {
                    int turnPower = (int) Math.round((double) map.get("TurnPower"));
                    Assistant assistant = AssistantCreator.createAssistant(turnPower);
                    msg = (myController.send(virtualView, assistant));
                }
                case "Ping" ->{
                    pingTimerTask.ping();
                    msg = "Pong";
                }
                default -> msg = "Unrecognized input" + "\n";
            }
            virtualView.forwardMsg(msg);
        }
    }
}
