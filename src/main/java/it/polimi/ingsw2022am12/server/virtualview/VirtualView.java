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
public class VirtualView{
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

    /**
     * forwardMsg puts the gameState string as a message in the output stream of the socket, then flushes it
     * @param gameState the state of the game coded as a string
     *
     */
    public void forwardMsg(String gameState){

        out.println(gameState);
        out.flush();
    }

    /**
     * disconnected method ends the game and prints a message of disconnection
     */
    public void disconnected(){
        myController.removeView(this);
    }

    /**
     * close method closes both the socket and the in Scanner/ out PrintWriter
     */
    public void close(){
        parser.interrupt();
        System.out.println("parser thread is interrupted:"+ parser.isInterrupted());
        in.close();
        out.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
