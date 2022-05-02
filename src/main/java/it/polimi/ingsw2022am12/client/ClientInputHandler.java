package it.polimi.ingsw2022am12.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.client.adapter.*;
import java.util.Scanner;

public class ClientInputHandler implements Runnable {

    private Scanner in;
    private Client client;

    public ClientInputHandler(Scanner in, Client client){
        this.in = in;
        this.client = client;
    }

    @Override
    public void run() {
        String input = null;
        while(true){
            input = in.nextLine();
            handle(input, client);
        }
    }

    private void handle(String input, Client client){
        String result ="";
        String[]tokens = input.split(" ", -1);
        System.out.println("input was split");
        Gson gson = new Gson();
        switch (tokens[0]){
            case "Nick":
                System.out.println("and recognized as a nick");
                NickInput nickInput= new NickInput(tokens[1]);
                gson = new GsonBuilder().registerTypeAdapter(NickInput.class, new NickInputAdapter()).create();
                result = gson.toJson(nickInput);
                client.forwardJson(result);
                break;

            case "Student":
                ClientStudent clientStudent = new ClientStudent(tokens[1], Integer.parseInt(tokens[2]));
                gson = new GsonBuilder().registerTypeAdapter(ClientStudent.class, new ClientStudentAdapter()).create();
                result = gson.toJson(clientStudent);
                client.forwardJson(result);
                break;

            case"Assistant":
                ClientAssistant clientAssistant = new ClientAssistant(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientAssistant.class, new ClientAssistantAdapter()).create();
                result = gson.toJson(clientAssistant);
                client.forwardJson(result);
                break;

            case "Mage":
                ClientMage clientMage = new ClientMage(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientMage.class, new ClientMageAdapter()).create();
                result = gson.toJson(clientMage);
                client.forwardJson(result);
                break;

            case "Island":
                ClientIsland clientIsland = new ClientIsland(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientIsland.class, new ClientIslandAdapter()).create();
                result=gson.toJson(clientIsland);
                client.forwardJson(result);
                break;

            case "Cloud":
                ClientStudentCollection cloud = new ClientStudentCollection(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create();
                result = gson.toJson(cloud);
                client.forwardJson(result);
                break;

            case "DiningRoom":
                ClientStudentCollection diningRoom = new ClientStudentCollection(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientStudentCollection.class, new ClientStudentCollectionAdapter()).create();
                result = gson.toJson(diningRoom);
                client.forwardJson(result);
                break;

            case "NoEntry":
                ClientNoEntry clientNoEntry = new ClientNoEntry(Integer.parseInt(tokens[1]));
                gson = new GsonBuilder().registerTypeAdapter(ClientNoEntry.class, new ClientNoEntryAdapter()).create();
                result = gson.toJson(clientNoEntry);
                client.forwardJson(result);
                break;

            case "Character":
                ClientCharacter clientCharacter = new ClientCharacter(tokens[1]);
                gson = new GsonBuilder().registerTypeAdapter(ClientCharacter.class, new ClientCharacterAdapter()).create();
                result = gson.toJson(clientCharacter);
                client.forwardJson(result);
                break;

            case "GameSettings":
                InputMode inputMode = new InputMode(Integer.parseInt(tokens[1]), Boolean.getBoolean(tokens[2]));
                gson = new GsonBuilder().registerTypeAdapter(InputMode.class, new InputModeAdapter()).create();
                result = gson.toJson(inputMode);
                client.forwardJson(result);
                break;

        }
    }



}
