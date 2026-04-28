package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.model.Route;
import com.server.manager.CollectionManager;

/**
 * Класс, представляющий консольную команду info
 * @author Ivan Kirillov
 */
public final class Info extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды info
     * @param collectionManager мененджер коллекции
     */
    public Info(CollectionManager collectionManager) {
        super("info", "Вывести информацию о коллекции маршрутов (тип, дата инициализации, количество элементов)");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        return new Response(RequestStatus.SUCCESS, null, collectionManager.getInfo());
    }
}
