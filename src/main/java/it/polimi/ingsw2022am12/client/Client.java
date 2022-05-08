package it.polimi.ingsw2022am12.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
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
    ExecutorService executor;
    Socket socket;

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
        executor = Executors.newCachedThreadPool();
        socket = new Socket(ip, port);
        System.out.println("Connected!");
        Scanner in = new Scanner(socket.getInputStream());
         out = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);

        stdout = new PrintWriter(System.out);
        System.out.println("Enter nick \n");
        stdout.println("Enter your nick for the game\n");
        stdout.flush();
        ClientInputHandler clientInputHandler = new ClientInputHandler(stdin, this);
        executor.submit(clientInputHandler);
        ServerMessageHandler serverMessageHandler = new ServerMessageHandler(in, this);
        executor.submit(serverMessageHandler);
        Timer timer = new Timer();
        ClientPingTimerTask pingTimerTask = new ClientPingTimerTask(this);
        timer.schedule(pingTimerTask, 3000, 3000);

    }

    /**
     * showServerMessage prints the message from the server, then flushes the output
     *
     * @param message from the server
     */
    public void showServerMessage(String message){
        stdout.println(message);
        stdout.flush();
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

    public void updateGameState(ClientGame clientGame){
        this.clientGame = clientGame;
        showServerMessage(clientGame.getBoardStringToView());
    }

    public void disconnected(){
        System.out.println("disconnecting");
        executor.shutdown();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
