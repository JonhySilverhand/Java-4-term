package by.belstu;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static Map<String, String> topicMessages = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4000); // Порт сервера
            ExecutorService executorService = Executors.newFixedThreadPool(10); // Пул потоков для параллельной обработки клиентов

            System.out.println("Сервер запущен. Ожидание подключения клиентов...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Ожидание подключения клиента
                System.out.println("Клиент подключился.");

                executorService.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader input;
        private PrintWriter output;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            try {
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                output = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String inputLine;
                while ((inputLine = input.readLine()) != null) {
                    System.out.println("Клиент: " + inputLine);

                    if (inputLine.equals("/topics")) {
                        output.println(String.join(",", topicMessages.keySet())); // Отправка списка топиков клиенту
                    } else if (topicMessages.containsKey(inputLine)) {
                        output.println(topicMessages.get(inputLine)); // Отправка сообщений по указанному топику клиенту
                    } else {
                        output.println("Топик не существует.");
                    }
                }
                input.close();
                output.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        topicMessages.put("topic1", "Сообщение 1 по топику 1");
        topicMessages.put("topic2", "Сообщение 1 по топику 2");
        topicMessages.put("topic3", "Сообщение 1 по топику 3");
    }
}