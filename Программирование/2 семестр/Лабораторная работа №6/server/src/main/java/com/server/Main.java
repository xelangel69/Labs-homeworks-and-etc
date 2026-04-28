package com.server;

import com.server.commands.*;
import com.common.exceptions.FileIsEmptyException;
import com.common.exceptions.NoArgsException;
import com.server.manager.CollectionManager;
import com.server.manager.FileManager;
import com.server.manager.ServerCommandManager;
import com.common.model.Route;
import com.server.network.UDPServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            if (args.length == 0) throw new NoArgsException("Введите имя загружаемого файла как аргумент командной строки");

            String fileName = args[0];
            File file = new File(fileName);

            int port = 8080;
            if (args.length > 1) {
                try {
                    port = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    logger.warn("Передан некорректный порт. Используется порт по умолчанию: {}", port);
                }
            }

            if (file.length() == 0) throw new FileIsEmptyException("Файл пустой!");

            FileManager fileManager = new FileManager(fileName);
            CollectionManager collectionManager = new CollectionManager();
            ServerCommandManager serverCommandManager = new ServerCommandManager(new HashMap<>());

            Collection<Route> loadedCollection = fileManager.readCollection();
            collectionManager.setRoutes(loadedCollection);

            Route.updateIdCounter(loadedCollection);

            serverCommandManager.registerCommand("info", new Info(collectionManager));
            serverCommandManager.registerCommand("help", new Help(serverCommandManager));
            serverCommandManager.registerCommand("show", new Show(collectionManager));
            serverCommandManager.registerCommand("add", new AddElement(collectionManager));
            serverCommandManager.registerCommand("update", new UpdateByID(collectionManager));
            serverCommandManager.registerCommand("remove_by_id", new RemoveByID(collectionManager));
            serverCommandManager.registerCommand("clear", new Clear(collectionManager));
            serverCommandManager.registerCommand("add_if_max", new AddIfMax(collectionManager));
            serverCommandManager.registerCommand("remove_lower", new RemoveLower(collectionManager));
            serverCommandManager.registerCommand("history", new History(serverCommandManager));
            serverCommandManager.registerCommand("average_of_distance", new AverageOfDistance(collectionManager));
            serverCommandManager.registerCommand("filter_contains_name", new FilterContainsName(collectionManager));
            serverCommandManager.registerCommand("print_descending", new PrintDescending(collectionManager));

            Runnable saveTask = () -> {
                logger.info("Сохранение коллекции в файл...");
                try {
                    fileManager.saveCollection(collectionManager.getRoutes());
                    logger.info("Коллекция успешно сохранена!");
                } catch (IOException e) {
                    logger.error("Не удалось сохранить коллекцию: {}", e.getMessage());
                }
            };

            Runtime.getRuntime().addShutdownHook(new Thread(saveTask));

            Thread consoleThread = new Thread(() -> {
                java.util.Scanner scanner = new java.util.Scanner(System.in);
                while (scanner.hasNextLine()) {
                    if (scanner.nextLine().trim().equalsIgnoreCase("save")) {
                        saveTask.run();
                    } else {
                        logger.warn("Неизвестная серверная команда. Доступна только 'save'.");
                    }
                }
            });
            consoleThread.setDaemon(true);
            consoleThread.start();

            UDPServer udpServer = new UDPServer(8080);
            logger.info("Сервер запущен на порту {}", port);

            ServerApp serverApp = new ServerApp(serverCommandManager, udpServer);

            serverApp.run();
        } catch (FileIsEmptyException e) {
            logger.fatal("Критическая ошибка - файл пустой");
            System.exit(0);
        } catch (NoArgsException e) {
            logger.fatal("Критическая ошибка - аршументы программы не были введены");
            System.exit(1);
        } catch (IOException e) {
            logger.fatal("Критическая ошибка: {}", e.getMessage());
        }
    }
}