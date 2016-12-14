package com.tiy.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by crci1 on 12/14/2016.
 */
public class MyClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // connect to the server on the target port
        try {
            Socket clientSocket = new Socket("localhost", 8004);

            // once we connect to the server, we also have an input and output stream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // send the server an arbitrary message
            while (true) {
                System.out.println("Enter message for server (Enter Exit to quit");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                // sending message to the server
                out.println(userInput);
                // read what the server returns
                String serverResponse = in.readLine();
                System.out.println("From server: " + serverResponse);


            }
            // close the connection
            clientSocket.close();


        } catch (IOException e){
            e.printStackTrace();
        }

    }
}

//    Enter a message for the server (enter "exit" to leave):
//        Hello from the client
//        From server: Hello from the server
//        Enter a message for the server (enter "exit" to leave):
//        How is the weather over on the server?
//        From server: Same as on the client - we're on the same machine, silly!
//        Enter a message for the server (enter "exit" to leave):
