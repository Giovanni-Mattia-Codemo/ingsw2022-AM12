package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.controller.Controller;
import java.io.IOException;
import java.net.*;

/**
 * Server class represents the component which receives requests from the client, and sends responses
 */
public class Server {

    private final int port;
    private ServerSocket serverSocket;
    private final Controller myController;

    /**
     * Constructor method of the Server class
     *
     * @param port the server's port
     */
    public Server(int port, String directory){
        this.port = port;
        this.myController = new Controller(this);
    }

    /**
     * Main method for the Server
     *
     * @param args array of parameters written as strings
     */
    public static void main(String[] args) {
        Server server = new Server(1344);
        server.startServer();
    }

    /**
     * The method that starts the server's thread
     */
    public void startServer(){

        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server up and running \n");
        }catch(IOException e){
            e.printStackTrace();
        }
        ConnectionHandler connectionHandler = new ConnectionHandler(myController, serverSocket);
        new Thread(connectionHandler, "Connection Handler").start();
    }

}