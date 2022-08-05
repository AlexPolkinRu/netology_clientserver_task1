import server.ServerSuperComputer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Aleksandr Polochkin
 * 04.08.2022
 */

public class Client {
    public static void main(String[] args) {

        // Включаем суперкомпьютер
        ServerSuperComputer superComputer = new ServerSuperComputer();
        superComputer.start();

        // Ожидание запуска суперкомпьютера
        while (!superComputer.isServerStarted()) ;

        try (

                // Выбран способ Blocking так как клиенту не требуется
                // выполнять какие либо действия до получения ответа с сервера.
                // А серверу нечего делать до очередного запроса клиента.

                // Открываем сокет
                Socket socket = new Socket("127.0.0.1", 12345);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()
                        ), true
                );

                Scanner scanner = new Scanner(System.in)
        ) {


            String msg;

            while (true) {

                System.out.println("Calculation of the Fibonacci number on a remote computer.");
                System.out.print("Input an integer (0 - 2147483647) or exit: ");

                msg = scanner.nextLine();

                out.println(msg);

                if ("exit".equals(msg)) {
                    break;
                }

                System.out.println("SERVER: " + in.readLine());
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
