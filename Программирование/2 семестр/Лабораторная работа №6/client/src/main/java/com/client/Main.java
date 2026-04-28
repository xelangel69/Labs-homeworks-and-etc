package com.client;

import com.client.console.Console;
import com.client.network.UDPClient;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8080;

        Terminal terminal = TerminalBuilder.builder().system(true).build();
        Console console = new Console(terminal);

        if (args.length == 2) {
            host = args[0];
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                console.printErr("Ошибка: Порт должен быть числом! Используется порт по умолчанию: " + port);
            }
        } else if (args.length == 1) {
            console.printErr("Укажите оба аргумента (хост и порт) или ни одного. Используются значения по умолчанию.");
        }

        try {
            console.printByProgram("Подключение к серверу " + host + ":" + port + "...");

            UDPClient udpClient = new UDPClient(host, port);
            RequestManager requestManager = new RequestManager(udpClient, console);

            ClientApp app = new ClientApp(console, terminal, requestManager);
            app.run();
        } catch (Exception e) {
            System.err.println("Ошибка инициализации: " + e.getMessage());
        }
    }
}