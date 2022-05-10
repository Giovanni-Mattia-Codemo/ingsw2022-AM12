package it.polimi.ingsw2022am12.client;

import it.polimi.ingsw2022am12.client.CLI.CLIView;
import it.polimi.ingsw2022am12.client.model.ClientGame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Client class represents the component which sends requests to the server, waiting for a response
 */
public class Client {

    private final int port;
    private final String ip;
    private PrintWriter stdout;
    private PrintWriter out;
    private ClientGame clientGame;
    private ExecutorService executor;
    private Socket socket;
    private Scanner in;
    private Timer timer;
    private View view;

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
        executor = Executors.newCachedThreadPool();
        System.out.println("Enter GUI or CLI to pick a client mode");
        String sel;
        boolean correct;
        do {
            correct = true;
            sel = stdin.nextLine();


            switch (sel) {
                case "CLI":
                    ClientInputHandler clientInputHandler = new ClientInputHandler(stdin, this);
                    executor.submit(clientInputHandler);
                    view = new CLIView();
                    break;

                case "GUI":
                    break;

                default: correct = false;
                    break;
            }
        }while(!correct);
        stdout.println("Enter your nick for the game\n");
        stdout.flush();

        socket = new Socket(ip, port);
        System.out.println("Connected!");
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());

        ServerMessageHandler serverMessageHandler = new ServerMessageHandler(in, this);
        executor.submit(serverMessageHandler);
        timer = new Timer();
        ClientPingTimerTask pingTimerTask = new ClientPingTimerTask(this);
        timer.schedule(pingTimerTask, 3000, 3000);
    }

    /**
     * restartClient method reopens a new socket, and submits a new pool of threads
     */
    private void restartClient() throws IOException{
        socket = new Socket(ip, port);
        System.out.println("Connected!");
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream());
        executor.submit(new ServerMessageHandler(in,this));
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
     * @param clientGame the current game state
     */
    public void updateGameState(ClientGame clientGame){
        this.clientGame = clientGame;
        showServerMessage(clientGame.getBoardStringToView());
    }

    /**
     * disconnected method initiates the process of disconnection from the server, closing the socket and shutting down
     * the various client threads
     */
    public void disconnected(){
        System.out.println("disconnecting");
        in.close();
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer.cancel();

        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for(Thread t: threadSet){
            System.out.println(t.getName());
        }
/*
        try{
            restartClient();
        }catch(IOException e){
            e.printStackTrace();
        }
*/

    }
}
