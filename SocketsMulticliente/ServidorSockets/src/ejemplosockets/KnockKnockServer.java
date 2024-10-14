package ejemplosockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author abelc
 */
public class KnockKnockServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final int PORT = 4444;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escuchando en el puerto " + PORT);

            // Bucle para aceptar múltiples clientes
            while (true) {
                // Acepta una nueva conexión de cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");

                // Crea un nuevo hilo para manejar este cliente
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                Thread thread = new Thread(clientHandler);
                thread.start(); // Ejecuta el hilo
            }

        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor en el puerto " + PORT);
            e.printStackTrace();
        }
    }

}
