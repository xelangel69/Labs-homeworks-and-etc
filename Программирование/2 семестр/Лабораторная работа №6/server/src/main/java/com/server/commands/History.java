package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.exceptions.HistoryIsEmptyException;
import com.common.model.Route;
import com.server.manager.ServerCommandManager;

/**
 * Класс, представляющий консольную команду history
 * @author Ivan Kirillov
 */
public final class History extends Command {
    private final ServerCommandManager serverCommandManager;

    /**
     * Конструктор класса команды history
     */
    public History(ServerCommandManager serverCommandManager) {
        super("history", "Вывести последние 7 команд (без их аргументов)");
        this.serverCommandManager = serverCommandManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        try {
            var history = serverCommandManager.getHistory();
            if (history.isEmpty()) throw new HistoryIsEmptyException("История команд пуста!");

            return new Response(RequestStatus.SUCCESS, "Последние команды:", history);
        } catch (HistoryIsEmptyException e) {
            return new Response(RequestStatus.ERROR, e.getMessage(), null);
        }
    }
}


