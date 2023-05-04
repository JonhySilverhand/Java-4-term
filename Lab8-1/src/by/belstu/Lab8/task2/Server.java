package by.belstu.Lab8.task2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        List<Socket> clientSockets = new ArrayList<>();
        int number = new Random().nextInt(100);
        System.out.println("Ожидаем подключение игроков...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Игрок присоединился: " + clientSocket.getInetAddress().getHostAddress());
            clientSockets.add(clientSocket);
            new Thread(() -> handleGuesses(clientSocket, clientSockets, number)).start();
        }
    }
    private static void handleGuesses(Socket clientSocket, List<Socket> clientSockets, int number) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String guess;

            while(true) {
                guess = in.readLine();

                if (guess == null) {
                    break;
                }

                int guessNumber = Integer.parseInt(guess);

                if (guessNumber == number) {
                    out.println("Ты угадал, я действительно загадал число " + number);
                    break;
                } else {
                    String message = guessNumber > number ? "Твое число слишком большое!" : "Маловато!";
                    out.println(message);
                }
            }

            clientSocket.close();
            clientSockets.remove(clientSocket);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
