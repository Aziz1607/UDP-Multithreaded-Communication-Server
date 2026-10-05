import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        int port = 3000;

        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            System.out.println("Le serveur UDP est en écoute sur le port " + port);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                serverSocket.receive(receivePacket);
                
                new Thread(new ClientHandler(receivePacket)).start();
            }
        } catch (Exception e) {
            System.err.println("Erreur du serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final DatagramPacket packet;

    public ClientHandler(DatagramPacket packet) {
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            String message = new String(packet.getData(), 0, packet.getLength());
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();

            System.out.println("Message reçu du client " + clientAddress + ":" + clientPort + " - " + message);
        } catch (Exception e) {
            System.err.println("Erreur dans ClientHandler : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
