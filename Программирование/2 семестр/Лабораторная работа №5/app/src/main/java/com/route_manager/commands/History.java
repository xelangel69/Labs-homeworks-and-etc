package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.exceptions.HistoryIsEmptyException;
import com.route_manager.manager.CommandManager;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду history
 * @author Ivan Kirillov
 */
public final class History extends Command {
    private final CommandManager commandManager;
    private final Console console;

    /**
     * Конструктор класса команды history
     */
    public History(CommandManager commandManager, Console console) {
        super("history", "Вывести последние 7 команд (без их аргументов)");
        this.commandManager = commandManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        try {
            var history = commandManager.getHistory();
            if (history.isEmpty()) throw new HistoryIsEmptyException("История команд пуста!");

            console.println("Последние команды:");
            history.forEach(System.out::println);

            return true;
        } catch (HistoryIsEmptyException e) {
            console.println(e.getMessage());
        }
        return false;
    }
}


