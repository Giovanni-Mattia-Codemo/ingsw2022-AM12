package it.polimi.ingsw2022am12.client;

import it.polimi.ingsw2022am12.client.GUI.GUIView;
import it.polimi.ingsw2022am12.updateFlag.UpdateFlag;
import it.polimi.ingsw2022am12.client.CLI.CLIView;
import it.polimi.ingsw2022am12.client.model.ClientGame;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;

/**
 * Client class represents the component which sends requests to the server, waiting for a response
 */
public class Client {

    private final int port;
    private final String ip;
    private PrintWriter stdout;
    private PrintWriter out;
    private ClientGame clientGame, newClientGame;
    private Socket socket;
    private Scanner in;
    private Timer timer;
    private View view;
    private Thread serverMsg, clientInput;
    private String thisClientNick;

    /**
     * Constructor method of the Client class
     * @param ip the client's ip
     * @param port the client's port
     */
    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    /**
     * Main method for the Client
     *
     * @param args array of parameters written as strings
     */
    public static void main(String[] args){
        Client client = new Client("localhost", 1344);
        try{
            client.startClient();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * The method that starts the client
     */
    public void startClient() throws IOException{

        Scanner stdin = new Scanner(System.in);
        stdout = new PrintWriter(System.out);

        System.out.println("Enter GUI or CLI to pick a client mode");
        String sel;
        boolean correct;
        do {
            correct = true;
            sel = stdin.nextLine();

            switch (sel) {
                case "CLI":
                    CLIHandler cliHandler = new CLIHandler(stdin, this);
                    clientInput = new Thread(cliHandler);
                    clientInput.start();
                    view = new CLIView();
                    break;

                case "GUI":
                    break;

                default: correct = false;
                    System.out.println("Wrong input, retry");
                    break;
            }
        }while(!correct);

        socket = new Socket(ip, port);
        System.out.println("Connected!");
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        ServerMessageHandler serverMessageHandler = new ServerMessageHandler(in, this);
        serverMsg = new Thread(serverMessageHandler);
        serverMsg.start();
        timer = new Timer();
        ClientPingTimerTask pingTimerTask = new ClientPingTimerTask(this);
        timer.schedule(pingTimerTask, 3000, 3000);
    }

    /**
     * showServerMessage prints the message from the server, then flushes the output
     *
     * @param message from the server
     */
    public void showServerMessage(String message){
        view.viewMessage(message);
    }

    /**
     * forwardJson prints the Json string, then flushes the output
     *
     * @param s string that represents my data
     */
    public void forwardJson(String s){
        out.println(s);
        out.flush();
    }

    /**
     * updateGameState deals with updating the game state for each client
     *
     * @param flag fields to be updated
     */
    public void updateGameState(UpdateFlag flag){
        if(clientGame==null){
            clientGame=newClientGame;
        }else clientGame.updateFromGame(newClientGame, flag);
        view.updateGameView(clientGame, flag);

    }

    public void updateLastSavedGame(ClientGame newGame){
        newClientGame = newGame;
    }

    public ClientGame getClientGame() {
        return clientGame;
    }

    /**
     * disconnected method initiates the process of disconnection from the server, closing the socket and shutting down
     * the various client threads
     */
    public void disconnected(){
        System.out.println("disconnecting");
        serverMsg.interrupt();
        in.close();
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer.cancel();

    }

    public void setThisClientNick(String thisClientNick) {
        this.thisClientNick = thisClientNick;
    }

    public String getThisClientNick() {
        return thisClientNick;
    }
}
