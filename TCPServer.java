import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            System.out.println("TCP Server listening on port 8081...");


            // This line blocks until a client connects to the server.
            // Once a connection is established, it returns a Socket object (clientSocket) that represents the connection to the client.
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // Receive data from the client

            // clientSocket: This is a Socket object representing the communication channel between the server and the connected client.
            // getInputStream(): This method retrieves the input stream associated with the socket
            // used to read data sent by the client.
            // InputStreamReader: This class is used to bridge from byte streams to character streams
            // reads bytes and decodes them into characters
            // BufferedReader: This class provides buffering for the input stream
            // reads characters, arrays, or lines

            // entire line sets up a buffered reader to read character data from the input stream of the client socket
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // readLine() that simplify the process of reading lines of text.
            String clientMessage = in.readLine();
            System.out.println("Received from client: " + clientMessage);

            // Send response to the client
            //  PrintWriter to send output to the client using the getOutputStream() method of the Socket
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            // It then sends the string "Hello from TCP server!" to the client.
            // out is obj of printwriter
            out.println("Hello from TCP server!");

            // Close sockets
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
