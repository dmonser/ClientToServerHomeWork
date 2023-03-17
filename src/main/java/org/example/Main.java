package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        int port = 8089;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                     ) {
                    System.out.println("New connection accepted");
                    final String massage = in.readLine();

                    System.out.printf("Received a message from the client: \"%s\"%n", massage);

                    out.println(String.format("Hi! Your port is %d", clientSocket.getPort()));
                }
            }
        }
    }
}