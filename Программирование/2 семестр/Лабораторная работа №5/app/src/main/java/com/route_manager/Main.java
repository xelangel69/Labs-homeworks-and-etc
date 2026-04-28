package com.route_manager;

import com.route_manager.commands.*;
import com.route_manager.console.Console;
import com.route_manager.exceptions.FileIsEmptyException;
import com.route_manager.exceptions.NoArgsException;
import com.route_manager.manager.*;
import com.route_manager.model.Route;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Console console = null;

        try {
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            console = new Console(terminal);

            if (args.length == 0) throw new  NoArgsException("Введите имя загружаемого файла как аргумент командной строки");

            String fileName = args[0];
            File file = new File(fileName);

            if (file.length() == 0) throw new FileIsEmptyException("Файл пустой!");

            console.println("");
            console.printSuccess("УПРАВЛЕНИЕ МАРШРУТАМИ");
            console.printByProgram("Добро пожаловать в программу для управления маршрутами!\nДля того, чтобы узнать список доступных команд введите 'help'.");


            FileManager fileManager = new FileManager(fileName, console);
            CollectionManager collectionManager = new CollectionManager();
            CommandManager commandManager = new CommandManager(new HashMap<>(), console);

            Collection<Route> loadedCollection = fileManager.readCollection();
            collectionManager.setRoutes(loadedCollection);

            Route.updateIdCounter(loadedCollection);

            commandManager.registerCommand("help", new Help(commandManager, console));
            commandManager.registerCommand("info", new Info(collectionManager, console));
            commandManager.registerCommand("show", new Show(collectionManager, console));
            commandManager.registerCommand("add", new AddElement(collectionManager, console));
            commandManager.registerCommand("update", new UpdateByID(collectionManager, console));
            commandManager.registerCommand("remove_by_id", new RemoveByID(collectionManager, console));
            commandManager.registerCommand("clear", new Clear(collectionManager, console));
            commandManager.registerCommand("save", new Save(collectionManager, fileManager, console));
            commandManager.registerCommand("execute_script", new ExecuteScript(commandManager, console));
            commandManager.registerCommand("exit", new Exit());
            commandManager.registerCommand("add_if_max", new AddIfMax(collectionManager, console));
            commandManager.registerCommand("remove_lower", new RemoveLower(collectionManager, console));
            commandManager.registerCommand("history", new History(commandManager, console));
            commandManager.registerCommand("average_of_distance", new AverageOfDistance(collectionManager, console));
            commandManager.registerCommand("filter_contains_name", new FilterContainsName(collectionManager, console));
            commandManager.registerCommand("print_descending", new PrintDescending(collectionManager, console));

            AppManager appManager = new AppManager(console, commandManager, terminal);
            appManager.run();
        } catch (FileIsEmptyException e) {
            if (console != null) {
                console.printErr(e.getMessage());
            } else {
                System.err.println("Критическая ошибка: " + e.getMessage());
            }
            System.exit(0);
        } catch (NoArgsException e) {
            if (console != null) {
                console.printErr(e.getMessage());
            } else {
                System.err.println("Критическая ошибка: " + e.getMessage());
            }
            System.exit(1);
        } catch (IOException e) {
            if (console != null) {
                console.printErr(e.getMessage());
            } else {
                System.err.println("Критическая ошибка: " + e.getMessage());
            }
        }
    }
}