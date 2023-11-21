import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class p9client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Change to server IP
        int serverPort = 9876; // Change to server port

        // sendFile(serverAddress, serverPort, "script.txt");
        sendFile(serverAddress, serverPort, "text.txt");
        // sendFile(serverAddress, serverPort, "audio.wav");
        // sendFile(serverAddress, serverPort, "video.mp4");
    }

    private static void sendFile(String serverAddress, int serverPort, String filePath) {
        try (DatagramSocket socket = new DatagramSocket()) {
            File file = new File(filePath);

            // Send file name
            byte[] fileNameBytes = file.getName().getBytes();
            DatagramPacket fileNamePacket = new DatagramPacket(fileNameBytes, fileNameBytes.length,InetAddress.getByName(serverAddress), serverPort);
            socket.send(fileNamePacket);

            // Send file size
            byte[] fileSizeBytes = Long.toString(file.length()).getBytes();
            DatagramPacket fileSizePacket = new DatagramPacket(fileSizeBytes, fileSizeBytes.length,InetAddress.getByName(serverAddress), serverPort);
            socket.send(fileSizePacket);

            // Send file data
            byte[] buffer = new byte[1024];
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    DatagramPacket dataPacket = new DatagramPacket(buffer, bytesRead,
                            InetAddress.getByName(serverAddress), serverPort);
                    socket.send(dataPacket);
                }
            }

            System.out.println("File sent: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
