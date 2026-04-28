package com.route_manager.manager;

import com.route_manager.commands.Command;
import com.route_manager.console.Console;
import com.route_manager.exceptions.UnknownCommandException;
import com.route_manager.util.InputProvider;

import java.util.ArrayDeque;
import java.util.Map;

/**
 * Мененджер команд - управляет всеми консольными командами
 * @author Ivan Kirillov
 */
public final class CommandManager {
    private final Map<String, Command> commands;
    ArrayDeque<String> commandList = new ArrayDeque<String>(7);
    private final Console console;

    /**
     * Конструктор мененджера команд
     */
    public CommandManager(Map<String, Command> commands, Console console) {
        this.commands = commands;
        this.console = console;
    }

    /**
     * Добавляет команду
     * @param command команда для добавления в список команд
     */
    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * @return список всех команд
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * @return последние выполненные команды без аргументов (7 команд)
     */
    public ArrayDeque<String> getHistory(){
        return commandList;
    }

    /**
     * Выполняет команду
     * @param userInput пользовательский ввод (команда (+аргумент))
     */
    public void executeCommand(String userInput, InputProvider inputProvider) {
        String[] tokens = userInput.trim().split("\\s", 2);
        String commandName = tokens[0];
        String argument = (tokens.length > 1) ? tokens[1] : "";

        Command command = commands.get(commandName);

        try {
            if (command == null) throw new UnknownCommandException("Неизвестная команда: " + commandName);

            command.execute(argument, inputProvider);
            commandList.addLast(commandName);

            if (commandList.size() > 7) {
                commandList.removeFirst();
            }
        } catch (UnknownCommandException e) {
            console.printErr(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return commands.toString();
    }
}
