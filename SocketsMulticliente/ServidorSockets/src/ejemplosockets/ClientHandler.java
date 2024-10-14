/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author abelc
 */
public class ClientHandler implements Runnable{
     private Socket clientSocket;
    private KnockKnockProtocol protocol;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.protocol = new KnockKnockProtocol(); // Cada cliente tiene su propio protocolo
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;
            outputLine = protocol.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = protocol.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye.")) {
                    break;
                }
            }

            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
