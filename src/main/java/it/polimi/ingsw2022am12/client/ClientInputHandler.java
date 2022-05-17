package it.polimi.ingsw2022am12.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.DiskColor;
import it.polimi.ingsw2022am12.client.model.*;
import it.polimi.ingsw2022am12.communication.InputMode;
import it.polimi.ingsw2022am12.communication.InputModeAdapter;
import it.polimi.ingsw2022am12.client.adapter.*;
import java.util.Scanner;


/**
 * ClientInputHandler class represents the component that handles the data in input from the client's side
 */
public class ClientInputHandler implements  Runnable {

    private final Scanner in;
    private final Client client;

    /**
     * ClientInputHandler class constructor
     *
     * @param in scanner in input
     * @param client my client
     */
    public ClientInputHandler(Scanner in, Client client){
        this.in = in;
        this.client = client;
    }

    /**
     * run method makes the client able to accept different inputs
     */
    @Override
    public void run() {
        String input;
        while(true){
            try{
                input = in.nextLine();
                handle(input, client);
            }catch (RuntimeException e){
                break;
            }

        }
    }

    /**
     * handle method deals with string inputs that represent a certain object (considering tokens[0]), and creates a JSON
     * format data though a switch case that changes based on the input string
     *
     * @param input the string that represents my object in input
     * @param client the client that is receiving the message
     */
    private void handle(String input, Client client){
        String result;
        String[]tokens = input.split(" ", -1);
        Gson gson;
        switch (tokens[0]) {
            case "Nick" -> {
                NickInput nickInput = new NickInput(tokens[1]);
                gson = new GsonBuilder().registerTypeAdapter(NickInput.class, new NickInputAdapter()).create();
                result = gson.toJson(nickInput);
                client.forwardJson(result);
            }
            case "Student" -> {
                ClientStudent clientStudent = new ClientStudent(DiskColor.valueOf(tokens[1]), Integer.parseInt(tokens[2]));
                gson = new GsonBuilder().registerTypeAdapter(ClientStudent.class, new ClientStudentAdapter()).create();
                result = gson.toJson(clientStudent);
                client.forwardJson(result);
            }
            case "Assistant" -> {
                ClientAssistant clientAssistant = new ClientAssistant(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientAssistant.class, new ClientAssistantAdapter()).create();
                result = gson.toJson(clientAssistant);
                client.forwardJson(result);
            }
            case "Mage" -> {
                ClientMage clientMage = new ClientMage(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientMage.class, new ClientMageAdapter()).create();
                result = gson.toJson(clientMage);
                client.forwardJson(result);
            }
            case "Island" -> {
                ClientIsland clientIsland = new ClientIsland(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientIsland.class, new ClientIslandAdapter()).create();
                result = gson.toJson(clientIsland);
                client.forwardJson(result);
            }
            case "Cloud" -> {
                ClientStudentCollection cloud = new ClientStudentCollection(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create();
                result = gson.toJson(cloud);
                client.forwardJson(result);
            }
            case "DiningRoom" -> {
                ClientStudentCollection diningRoom = new ClientStudentCollection(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create();
                result = gson.toJson(diningRoom);
                client.forwardJson(result);
            }
            case "NoEntry" -> {
                ClientNoEntry clientNoEntry = new ClientNoEntry(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientNoEntry.class, new ClientNoEntryAdapter()).create();
                result = gson.toJson(clientNoEntry);
                client.forwardJson(result);
            }
            case "Character" -> {
                ClientCharacter clientCharacter = new ClientCharacter(tokens[1]);
                gson = new GsonBuilder().registerTypeAdapter(ClientCharacter.class, new ClientCharacterAdapter()).create();
                result = gson.toJson(clientCharacter);
                client.forwardJson(result);
            }
            case "GameSettings" -> {
                InputMode inputMode = new InputMode(Integer.parseInt(tokens[1]), Boolean.parseBoolean(tokens[2]));
                gson = new GsonBuilder().registerTypeAdapter(InputMode.class, new InputModeAdapter()).create();
                result = gson.toJson(inputMode);
                client.forwardJson(result);
            }
        }
    }



}
