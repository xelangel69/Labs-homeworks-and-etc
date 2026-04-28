package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.model.Route;
import com.server.manager.CollectionManager;

/**
 * Класс, представляющий консольную команду clear
 * @author Ivan Kirillov
 */
public final class Clear extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды clear
     */
    public Clear(CollectionManager collectionManager) {
        super("clear", "Удалить все маршруты");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        collectionManager.clearCollection();
        return new Response(RequestStatus.SUCCESS, "Коллекция очищена!", null);
    }
}
