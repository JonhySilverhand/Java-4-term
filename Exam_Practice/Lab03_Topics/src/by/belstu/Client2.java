package by.belstu;

import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4000); // IP-адрес и порт сервера

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = consoleInput.readLine()) != null) {
                if (userInput.equals("GetTopics")) {
                    output.println(userInput); // Запрос списка топиков у сервера

                    String topicsResponse = input.readLine(); // Получение списка топиков от сервера
                    System.out.println("Доступные топики: " + topicsResponse);
                } else {
                    output.println(userInput); // Отправка выбранного топика серверу

                    String messagesResponse = input.readLine(); // Получение сообщений по топику от сервера
                    System.out.println("Сообщения по топику '" + userInput + "':");
                    System.out.println(messagesResponse);
                }
            }

            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}