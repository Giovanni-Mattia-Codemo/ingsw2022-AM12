package it.polimi.ingsw2022am12.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.CharacterName;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.NickInput;
import it.polimi.ingsw2022am12.NickInputAdapter;
import it.polimi.ingsw2022am12.client.model.*;
import it.polimi.ingsw2022am12.communication.InputMode;
import it.polimi.ingsw2022am12.communication.InputModeAdapter;
import it.polimi.ingsw2022am12.client.adapter.*;
import it.polimi.ingsw2022am12.server.adapter.ColorSelectionAdapter;
import it.polimi.ingsw2022am12.server.model.ColorSelection;

/**
 * ClientInputHandler class represents the component that handles the data in input from the client's side
 */
public class ClientInputHandler {


    /**
     * Default constructor of the ClientInputHandler
     */
    public ClientInputHandler(){

    }

    /**
     * handle method deals with string inputs that represent a certain object (considering tokens[0]), and creates a JSON
     * format data through a switch case that changes based on the input string
     *
     * @param input the string that represents my object in input
     * @param client the client that is receiving the message
     */
    public static void handle(String input, Client client){
        String result;
        String[]tokens = input.split(" ", -1);
        Gson gson;
        switch (tokens[0]) {
            case "Nick" -> {
                if(tokens.length==1||tokens[1].equals("")){
                    System.out.println("Message incomplete, retry");
                    break;
                }
                NickInput nickInput = new NickInput(tokens[1]);
                gson = new GsonBuilder().registerTypeAdapter(NickInput.class, new NickInputAdapter()).create();
                result = gson.toJson(nickInput);
                client.forwardJson(result);
            }
            case "Student" -> {
                if(tokens.length==1||tokens[1].equals("")||tokens.length == 2||tokens[2].equals("")){
                    System.out.println("Message incomplete, retry");
                    break;
                }
                DiskColor color;
                try{
                    color = DiskColor.valueOf(tokens[1].toUpperCase());
                }catch(Exception e){
                    System.out.println("Not a color, retry");
                    break;
                }
                int id;
                String name = client.getThisClientNick();
                if(tokens[2].equals("Entrance")){
                    id = client.getClientGame().getSchoolBoardByNick(name).getEntrance().getID();

                }else if(tokens[2].equals("DiningRoom")){
                    id = client.getClientGame().getSchoolBoardByNick(name).getDiningRooms().getID();
                }else{

                    try{
                        id = Integer.parseInt(tokens[2]);
                    }catch(Exception e){
                        System.out.println("Not a number, retry");
                        break;
                    }

                }

                ClientStudent clientStudent = new ClientStudent(color, id);
                gson = new GsonBuilder().registerTypeAdapter(ClientStudent.class, new ClientStudentAdapter()).create();
                result = gson.toJson(clientStudent);
                client.forwardJson(result);
            }

            case "Assistant" -> {
                if(tokens.length==1||tokens[1].equals("")){
                    System.out.println("Message incomplete, retry");
                    break;
                }
                int id;
                try{
                    id = Integer.parseInt(tokens[1]);
                }catch(Exception e){
                    System.out.println("Not a number, retry");
                    break;
                }
                ClientAssistant clientAssistant = new ClientAssistant(id);
                gson = new GsonBuilder().registerTypeAdapter(ClientAssistant.class, new ClientAssistantAdapter()).create();
                result = gson.toJson(clientAssistant);
                client.forwardJson(result);
            }
            case "Mage" -> {
                if(tokens.length==1||tokens[1].equals("")){
                    System.out.println("Message incomplete, retry");
                    break;
                }
                int id;
                try{
                    id = Integer.parseInt(tokens[1]);
                }catch(Exception e){
                    System.out.println("Not a number, retry");
                    break;
                }
                ClientMage clientMage = new ClientMage(id);
                gson = new GsonBuilder().registerTypeAdapter(ClientMage.class, new ClientMageAdapter()).create();
                result = gson.toJson(clientMage);
                client.forwardJson(result);
            }
            case "Island" -> {
                if(tokens.length==1||tokens[1].equals("")){
                    System.out.println("Message incomplete, retry");
                    break;
                }
                int id;
                try{
                    id = Integer.parseInt(tokens[1]);
                }catch(Exception e){
                    System.out.println("Not a number, retry");
                    break;
                }
                ClientIsland clientIsland = new ClientIsland(id);
                gson = new GsonBuilder().registerTypeAdapter(ClientIsland.class, new ClientIslandAdapter()).create();
                result = gson.toJson(clientIsland);
                client.forwardJson(result);
            }
            case "Cloud" -> {
                if(tokens.length==1||tokens[1].equals("")){
                    System.out.println("Message incomplete, retry");
                    break;
                }
                int id;
                try{
                    id = Integer.parseInt(tokens[1]);
                }catch(Exception e){
                    System.out.println("Not a number, retry");
                    break;
                }
                ClientStudentCollection collection = new ClientStudentCollection(id);
                gson = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create();
                result = gson.toJson(collection);
                client.forwardJson(result);
            }
            case "DiningRoom" -> {
                String name = client.getThisClientNick();
                int id = client.getClientGame().getSchoolBoardByNick(name).getDiningRooms().getID();
                ClientStudentCollection collection = new ClientStudentCollection(id);
                gson = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create();
                result = gson.toJson(collection);
                client.forwardJson(result);
            }
            case "Character" -> {
                if(tokens.length==1||tokens[1].equals("")){
                    System.out.println("Message incomplete, retry");
                    break;
                }
                boolean found = false;
                for(CharacterName n: CharacterName.values()){
                    if (n.name().equals(tokens[1])) {
                        found = true;
                        break;
                    }
                }
                if(!found){
                    System.out.println("Typed wrong name, retry");
                    break;
                }
                ClientCharacter clientCharacter = new ClientCharacter(tokens[1]);
                gson = new GsonBuilder().registerTypeAdapter(ClientCharacter.class, new ClientCharacterAdapter()).create();
                result = gson.toJson(clientCharacter);
                client.forwardJson(result);
            }
            case "GameSettings" -> {
                if(tokens.length==1||tokens.length==2||tokens[1].equals("")||tokens[2].equals("")){
                    System.out.println("Message incomplete, retry");
                    break;
                }
                int id;
                try{
                    id = Integer.parseInt(tokens[1]);
                }catch(Exception e){
                    System.out.println("Not a number, retry");
                    break;
                }
                InputMode inputMode = new InputMode(id, Boolean.parseBoolean(tokens[2].toLowerCase()));
                gson = new GsonBuilder().registerTypeAdapter(InputMode.class, new InputModeAdapter()).create();
                result = gson.toJson(inputMode);
                client.forwardJson(result);
            }
            case "Color" ->{
                ColorSelection sel = new ColorSelection(DiskColor.valueOf(tokens[1].toUpperCase()));
                gson = new GsonBuilder().registerTypeAdapter(ColorSelection.class, new ColorSelectionAdapter()).create();
                result = gson.toJson(sel);
                client.forwardJson(result);
            }
            case "Help"->{
                client.showHelp(0);
            }
            case "CardsList"->{
                client.showHelp(1);
            }
            case "RETRY" -> client.connect();
            default -> System.out.println("Unrecognized input");

        }
    }
}
