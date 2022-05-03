package it.polimi.ingsw2022am12.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class represents the component which sends requests to the server, waiting for a response
 */
public class Client {

    private int port;
    private String ip;
    private PrintWriter stdout;
    private PrintWriter out;

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

        Socket socket = new Socket(ip, port);
        System.out.println("Connected!");
        Scanner in = new Scanner(socket.getInputStream());
         out = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);

        stdout = new PrintWriter(System.out);
        System.out.println("Enter nick \n");
        stdout.println("Enter your nick for the game\n");
        stdout.flush();
        String fromServer ="";
        String input = "";
        String line = null;
        ClientInputHandler clientInputHandler = new ClientInputHandler(stdin, this);
        new Thread(clientInputHandler).start();
        ServerMessageHandler serverMessageHandler = new ServerMessageHandler(in, this);
        new Thread(serverMessageHandler).start();


        /*
        do{

            String nick = stdin.nextLine();
            System.out.println("User input: "+ nick);
            nick =clientInputHandler.convertInputToJsonString(nick);
            out.println(nick);
            out.flush();
            fromServer = in.nextLine();
            if(fromServer.equals(ControlMessages.GAMEISFULL.getMessage())){
                System.out.print("The server is full, try again later");
                stdin.close();
                socket.close();
                stdout.close();
            }
            System.out.println(fromServer);
        }while(!(fromServer.equals(ControlMessages.ACCEPTED.getMessage())||fromServer.equals(ControlMessages.INSERTMODE.getMessage())));
        while (true){
            String input = "";
            input = stdin.nextLine();
            System.out.println(" inserted: "+input);
            out.println(input);
            out.flush();
            fromServer= in.nextLine();
            System.out.println(fromServer);
        }

        */

        /*
        Nick nome
        GameSettings 2 true
        Mage 1

        {"tag":"InputMode","number":2,"mode":true}
         {"tag":"Nick","nick":"mynick3"}

         */
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
}
