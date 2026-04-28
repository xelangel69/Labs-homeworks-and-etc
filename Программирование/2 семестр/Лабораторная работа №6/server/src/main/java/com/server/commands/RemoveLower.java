package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.server.manager.CollectionManager;
import com.common.model.Route;

/**
 * Класс, представляющий консольную команду remove_lower {element}
 * @author Ivan Kirillov
 */
public final class RemoveLower extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды remove_lower
     */
    public RemoveLower(CollectionManager collectionManager) {
        super("remove_lower", "Удалить из коллекции все маршруты, меньшие, чем определенный маршрут");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        collectionManager.removeLower(routeArg);

        return new Response(RequestStatus.SUCCESS, "Объекты, меньше заданного, удалены", null);
    }
}
