import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(8080);
            System.out.println("UDP Server listening on port 8080...");

            // Receive data
            // byte array (receiveData) to store the received data.
            byte[] receiveData = new byte[1024];

            // receivePacket that will be used to receive data.
            // information about the packet (source address, port, etc.) is stored in the receivePacket.
            // receiveData byte array, which will store the received data
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            
            // socket.receive(receivePacket) method blocks until a UDP packet is received, and the data is stored in the receiveData array.
            socket.receive(receivePacket);

            // receivePacket.getData(): This method retrieves the byte array from the DatagramPacket
            // buffer may be larger than the actual data received
            // so getLength() is used to determine the length of the valid data.
            // receivePacket.getLength(): This method returns the length of the data received

            //  constructor of the String class creates a new String object.
            // parameters are
            // byte array (receivePacket.getData())
            // starting index (0)
            // length of the valid data (receivePacket.getLength())
            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from client: " + clientMessage);

            // Send response
            // Creates a byte array (sendData) containing the response message.
            byte[] sendData = "Hello from UDP server!".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            // sends the response packet to the client.
            socket.send(sendPacket);

            // Close socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
