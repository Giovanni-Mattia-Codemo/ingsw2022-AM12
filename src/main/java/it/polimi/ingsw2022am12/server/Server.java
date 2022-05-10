package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.virtualview.VirtualView;
import it.polimi.ingsw2022am12.server.controller.Controller;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Server class represents the component which receives requests from the client, and sends responses
 */
public class Server {

    private final int port;
    private ServerSocket serverSocket;
    private final Controller myController;
    private final ArrayList<VirtualView> virtualViews;

    /**
     * Constructor method of the Server class
     *
     * @param port the server's port
     */
    public Server(int port){
        this.port = port;
        this.myController = new Controller(this);
        virtualViews = new ArrayList<>();
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
     * The method that starts the server
     */
    public void startServer(){

        Scanner serverTerminalIn = new Scanner(System.in);
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server up and running \n");
        }catch(IOException e){
            e.printStackTrace();
        }
        ConnectionHandler connectionHandler = new ConnectionHandler(myController, serverSocket, this);
        new Thread(connectionHandler, "Connection Handler").start();
    }

    public void addView(VirtualView v){
        System.out.println("adding a view to server");
        virtualViews.add(v);
    }

    public void removeView(VirtualView v){
        System.out.println("removing a view from server");
        v.close();
        virtualViews.remove(v);
    }

    public void updateViewsOfStatus(){
        for(VirtualView v : virtualViews){
            v.forwardMsg(myController.getMatchStatusOfView(v).getMessage());
        }
    }

}