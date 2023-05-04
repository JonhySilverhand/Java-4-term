package by.belstu.Lab8.task3;

import java.net.*;

public class Sender {
    private int port;
    private DatagramSocket socket;

    public Sender(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message, InetAddress address) {
        try {
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 3000;

        Sender sender = new Sender(port);
        sender.sendMessage("Welcome to the Gulag!", address);
        sender.close();
    }
}
