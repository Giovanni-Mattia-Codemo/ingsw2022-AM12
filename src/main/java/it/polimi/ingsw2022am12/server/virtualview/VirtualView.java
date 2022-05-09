package it.polimi.ingsw2022am12.server.virtualview;

import it.polimi.ingsw2022am12.server.controller.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

/**
 * VirtualView class represents the layer that transforms information that come from the client and sends them to the
 * controller inside the server
 */
public class VirtualView implements Runnable{
    private final Socket socket;
    private final Controller myController;
    private PrintWriter out = null;
    private Scanner in;
    private Thread parser;

    /**
     * constructor method of VirtualView class
     * @param socket the socket associated to my VirtualView
     * @param newController the controller associated to my virtualView
     */
    public VirtualView(Socket socket, Controller newController){
        this.socket = socket;
        this.myController = newController;
    }

    /**
     * forwardMsg puts the gameState string as a message in the output stream of the socket, then flushes it
     * @param gameState the state of the game coded as a string
     *
     */
    public void forwardMsg(String gameState){
        out.println(gameState);
        out.flush();
    }
    public void disconnected(){
        myController.endGame();
    }

    /**
     * void run is the method executed by the various threads; it receives information from the client, and deserializes it,
     * sending it to the Controller
     */
    public void run(){
        try{
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            VirtualViewMessagesParser virtualViewMessagesParser = new VirtualViewMessagesParser(this, in, myController);
            parser = new Thread(virtualViewMessagesParser);
            parser.start();
            System.out.println("Im alive");
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
