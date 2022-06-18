package it.polimi.ingsw2022am12.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw2022am12.ControlMessagesAdapter;
import it.polimi.ingsw2022am12.server.controller.ControlMessages;
import it.polimi.ingsw2022am12.server.controller.Controller;
import it.polimi.ingsw2022am12.server.virtualview.VirtualView;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ConnectionHandler class represents the component that handles the connection with the server's side, through a virtual view
 */
public class ConnectionHandler implements Runnable{


    private final Controller controller;
    private final ServerSocket serverSocket;

    /**
     * Constructor method of ConnectionHandler
     * @param controller my controller
     * @param serverSocket the socket of the Server
     */
    public ConnectionHandler(Controller controller, ServerSocket serverSocket){
        this.controller = controller;
        this.serverSocket = serverSocket;

    }

    /**
     * method run connects to the server socket and creates a virtualView, to interact with it through messages (it can
     * be considered as an interface for the server)
     */
    @Override
    public void run() {
        while(true){
            try{
                Socket socket = serverSocket.accept();
                VirtualView virtualView = new VirtualView(socket, controller);
                controller.addView(virtualView);
                ControlMessages msg = controller.getMatchStatusOfView(virtualView);
                ArrayList<ControlMessages> msgs = new ArrayList<>();
                msgs.add(msg);
                Gson g = new GsonBuilder().registerTypeAdapter(ArrayList.class, new ControlMessagesAdapter()).create();
                virtualView.forwardMsg(g.toJson(msgs));

            }catch(IOException e ){
                break;
            }
        }
    }
}
