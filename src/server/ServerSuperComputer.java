package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Aleksandr Polochkin
 * 04.08.2022
 */

public class ServerSuperComputer extends Thread {

    private volatile boolean serverStarted;

    public ServerSuperComputer() {
        serverStarted = false;
    }

    public boolean isServerStarted() {
        return serverStarted;
    }

    public void run() {

        try (ServerSocket servSocket = new ServerSocket(12345)) {

            serverStarted = true;

            try (
                    // Ждем подключения клиента
                    Socket socket = servSocket.accept();

                    PrintWriter out = new PrintWriter(
                            socket.getOutputStream(), true
                    );

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()
                            )
                    )
            ) {

                while (true) {

                    String line;
                    int numberFibonacci;
                    BigDecimal fibN;

                    while ((line = in.readLine()) != null) {

                        // Выход если от клиента получили exit
                        if (line.equals("exit")) {
                            return;
                        }

                        // Пишем ответ
                        try {
                            numberFibonacci = Integer.parseInt(line);
                        } catch (NumberFormatException e) {
                            out.println("You entered an invalid request!");
                            continue;
                        }

                        fibN = Fib.get(numberFibonacci);

                        if (fibN == null) {
                            out.println("You entered an invalid request!");
                            continue;
                        }

                        out.println("Fib(" + numberFibonacci + ") = " + fibN);

                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
