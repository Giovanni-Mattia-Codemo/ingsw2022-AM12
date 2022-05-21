package it.polimi.ingsw2022am12.server.virtualview;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.server.adapter.*;
import it.polimi.ingsw2022am12.server.controller.Controller;
import it.polimi.ingsw2022am12.server.model.*;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

/**
 * Class that represents the MessagesParser for our VirtualView
 */
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

    /**
     * method run receives a JSON value in input from the scanner (which can be an encoded student, nickname... etc. etc.)
     * and forwards a corresponding message to the VirtualView after parsing the JSON data into an actual object
     */
    @Override
    public void run() {

        Timer tim = new Timer(true);
        PingTimerTask pingTimerTask = new PingTimerTask(virtualView);
        tim.schedule(pingTimerTask, 15000, 6000);
        String line;
        while (true) {
            try {
                while (in.hasNextLine()) {

                    line = in.nextLine();

                    Gson gson = new Gson();
                    Map map = gson.fromJson(line, Map.class);
                    String tag = (String) map.get("tag");
                    map.remove("tag");
                    String res = gson.toJson(map);

                    switch (tag) {
                        case "Nick" -> {
                            String nick = (String) map.get("nick");
                            myController.selectUsername(nick, virtualView);

                        }
                        case "Student" -> {
                            GsonBuilder builder = new GsonBuilder();
                            builder.registerTypeAdapter(Student.class, new StudentAdapter());
                            gson = builder.create();
                            Student student = gson.fromJson(res, Student.class);
                            myController.send(virtualView, student);
                        }
                        case "StudentDiskCollection" -> {
                            gson = new GsonBuilder().registerTypeAdapter(StudentDiskCollection.class, new StudentDiskCollectionAdapter()).create();
                            StudentDiskCollection studentDiskCollection = gson.fromJson(res, StudentDiskCollection.class);
                            myController.send(virtualView, studentDiskCollection);
                        }
                        case "NoEntry" -> {
                            gson = new GsonBuilder().registerTypeAdapter(NoEntry.class, new NoEntryAdapter()).create();
                            NoEntry noEntry = gson.fromJson(res, NoEntry.class);
                            myController.send(virtualView, noEntry);
                        }
                        case "Character" -> {
                            gson = new GsonBuilder().registerTypeAdapterFactory(new CharacterAdapterFactory()).create();
                            CharacterCard characterCard = gson.fromJson(res, CharacterCard.class);
                            myController.send(virtualView, characterCard);
                        }
                        case "Island" -> {
                            gson = new GsonBuilder().registerTypeAdapter(IslandTileSet.class, new IslandTileSetAdapter()).create();
                            IslandTileSet islandTileSet = gson.fromJson(res, IslandTileSet.class);
                            myController.send(virtualView, islandTileSet);
                        }
                        case "Color" -> {
                            DiskColor color = (DiskColor) map.get("color");
                            myController.send(virtualView, new ColorSelection(color));
                        }
                        case "InputMode" -> {
                            int num = (int) Math.round((double) map.get("number"));
                            boolean b = (boolean) map.get("mode");
                            myController.setGameMode(virtualView, num, b);
                        }
                        case "Mage" -> {
                            int id = (int) Math.round((double) map.get("ID"));
                            Mage mage = new Mage(id);
                            myController.send(virtualView, mage);
                        }
                        case "Assistant" -> {
                            int turnPower = (int) Math.round((double) map.get("TurnPower"));
                            Assistant assistant = AssistantCreator.createAssistant(turnPower);
                            myController.send(virtualView, assistant);
                        }
                        case "Ping" -> {
                            pingTimerTask.ping();
                            virtualView.forwardMsg("Pong");
                        }
                        default -> virtualView.forwardMsg("Unrecognized input" + "\n");
                    }
                    break;

                }
            } catch (RuntimeException e) {
                tim.cancel();
                break;
            }
        }
    }
}
