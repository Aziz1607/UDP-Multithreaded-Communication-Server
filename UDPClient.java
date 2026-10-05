import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 3000;

        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Entrez un message à envoyer au serveur :");

            String message = scanner.nextLine();

            byte[] sendData = message.getBytes();
            
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverInetAddress, serverPort);
            
            clientSocket.send(sendPacket);

            System.out.println("Message envoyé  : " + message);
        } catch (Exception e) {
            System.err.println("Erreur du client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
