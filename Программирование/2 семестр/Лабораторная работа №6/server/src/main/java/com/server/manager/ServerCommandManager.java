package com.server.manager;

import com.common.network.Request;
import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.model.Route;
import com.server.commands.Command;
import com.common.exceptions.UnknownCommandException;

import java.util.ArrayDeque;
import java.util.Map;

/**
 * Мененджер команд - управляет всеми консольными командами
 * @author Ivan Kirillov
 */
public final class ServerCommandManager {
    private final Map<String, Command> commands;
    ArrayDeque<String> commandList = new ArrayDeque<String>(7);

    /**
     * Конструктор мененджера команд
     */
    public ServerCommandManager(Map<String, Command> commands) {
        this.commands = commands;
    }

    public Response handleRequest(Request request) {
        String commandName = request.getCommandName();
        String argument = request.getPrimitiveArg();
        Route route = request.getRouteArg();

        Command command = commands.get(commandName);

        try {
            if (command == null) return new Response(RequestStatus.ERROR, "Неизвестная команда " + commandName, null);

            Response response = command.execute(argument, route);
            commandList.addLast(commandName);

            if (commandList.size() > 7) {
                commandList.removeFirst();
            }

            return response;
        } catch (UnknownCommandException e) {
            return new Response(RequestStatus.ERROR, "Ошибка выполнения: " + e.getMessage(), null);
        }
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

    @Override
    public String toString() {
        return commands.toString();
    }
}
