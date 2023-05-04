package by.belstu.Lab8.task3;

import java.net.*;

public class Receiver {
    private int port;
    private DatagramSocket socket;

    public Receiver(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            return new String(buffer, 0, packet.getLength());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 3000;

        Receiver receiver = new Receiver(port);
        String message = receiver.receiveMessage();
        System.out.println("Received: " + message);
        receiver.close();
    }
}
