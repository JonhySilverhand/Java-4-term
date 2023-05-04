package by.belstu.Lab8.task2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {
        final String Server_Address = "127.0.0.1";
        final int Server_Port = 3000;
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket(Server_Address, Server_Port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            System.out.println("Угадай число от 1 до 99");

            while(true){
                String guess = scanner.nextLine();
                out.println(guess);

                String message = in.readLine();
                System.out.println(message);
                if(message.contains("Congratulations!")){
                    break;
                }
            }
        }
    }
}
