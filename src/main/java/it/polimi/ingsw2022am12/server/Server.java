package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.controller.Controller;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private int port;
    private ServerSocket serverSocket;
    private final Controller myController;


    public Server(int port){
        this.port = port;
        this.myController = new Controller();
    }

    public static void main(String[] args) {
        Server server = new Server(1344);
        server.startServer();
    }


    public void startServer(){

        ExecutorService executor = Executors.newCachedThreadPool();

        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server up and running \n");
        }catch(IOException e){
            e.printStackTrace();
        }

        while(true){
            try{
                Socket socket = serverSocket.accept();
                VirtualView virtualView = new VirtualView(socket, myController);
                executor.submit(virtualView);
            }catch(IOException e ){
                break;
            }
        }
        executor.shutdown();
    }

}
