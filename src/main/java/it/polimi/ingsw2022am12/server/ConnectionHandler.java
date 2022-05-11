package it.polimi.ingsw2022am12.server;

import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.server.controller.Controller;
import it.polimi.ingsw2022am12.server.virtualview.VirtualView;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler implements Runnable{


    private final Controller controller;
    private final ServerSocket serverSocket;

    public ConnectionHandler(Controller controller, ServerSocket serverSocket){
        this.controller = controller;
        this.serverSocket = serverSocket;

    }

    @Override
    public void run() {
        while(true){
            try{
                Socket socket = serverSocket.accept();

                VirtualView virtualView = new VirtualView(socket, controller);

                controller.addView(virtualView);
                System.out.println("created virtualview");
                ControlMessages msg = controller.getMatchStatusOfView(virtualView);
                virtualView.forwardMsg(msg.getMessage());
                System.out.println("Sent it a message");

            }catch(IOException e ){
                break;
            }

        }
    }
}
