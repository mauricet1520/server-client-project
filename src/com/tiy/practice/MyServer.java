package com.tiy.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by crci1 on 12/14/2016.
 */
public class MyServer {
    final static int PORT_NUMBER = 8004;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Listening to server");
            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Connection Establish");
            System.out.println("Listening on port "+PORT_NUMBER);
            Socket clientSocket = serverSocket.accept();
            System.out.println("connection complete");
            System.out.println("host address: "+ clientSocket.getInetAddress().getHostAddress());


            // display information about who just connected to our server
            System.out.println("Incoming connection from " + clientSocket.getInetAddress().getHostAddress());

            // this is how we read from the client  just like System in
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // this is how we write back to the client System out
            PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = inputFromClient.readLine()) != null) {
                System.out.println("Incoming message: " + inputLine + " from " + clientSocket.toString());
                String clientName = inputLine.split(" ")[0];
                System.out.println("Enter a response:");
                String response = scanner.nextLine();
                outputToClient.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

//    About to listen to connection
//        Listening on port 8005
//        Connection established on the server!
//        Host address: 127.0.0.1
//        Incoming connection from 127.0.0.1
//        Received message: Hello from the client from Socket[addr=/127.0.0.1,port=59924,localport=8005]
//        Enter your response:
//        Hello from the server
//        Received message: How is the weather over on the server?  from Socket[addr=/127.0.0.1,port=59924,localport=8005]
//        Enter your response:
//        Same as on the client - we're on the same machine, silly!
