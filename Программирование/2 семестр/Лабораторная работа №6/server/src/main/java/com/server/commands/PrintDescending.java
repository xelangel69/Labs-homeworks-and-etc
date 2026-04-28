package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.model.Route;
import com.server.manager.CollectionManager;

/**
 * Класс, представляющий консольную команду print_descending
 * @author Ivan Kirillov
 */
public final class PrintDescending extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды print_descending
     */
    public PrintDescending(CollectionManager collectionManager) {
        super("print_descending", "Вывести маршруты в порядке убывания");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        return new Response(RequestStatus.SUCCESS, null, collectionManager.descendSort());
    }
}
