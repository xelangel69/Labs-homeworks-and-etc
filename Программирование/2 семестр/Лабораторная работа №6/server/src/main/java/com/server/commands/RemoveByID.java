package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.exceptions.RoutesNotFindException;
import com.common.model.Route;
import com.server.manager.CollectionManager;

/**
 * Класс, представляющий консольную команду remove_by_id {id}
 * @author Ivan Kirillov
 */
public final class RemoveByID extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды remove_by_id
     */
    public RemoveByID(CollectionManager collectionManager) {
        super("remove_by_id {ID}", "Удалить маршрут из коллекции по его ID");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        try {
            if (primitiveArg.isEmpty()) throw new IllegalArgumentException("Введите ID!");

            long id = Long.parseLong(primitiveArg);

            var route = collectionManager.findById(id);
            if (route == null) throw new RoutesNotFindException("Нет объекта с таким ID!");

            collectionManager.removeElement(route);

            return new Response(RequestStatus.SUCCESS, "Маршрут с ID " + id + " удалён!", null);

        } catch (NumberFormatException e) {
            return new Response(RequestStatus.ERROR, "ID должен быть числом!", null);
        } catch (IllegalArgumentException | RoutesNotFindException e) {
            return new Response(RequestStatus.ERROR, e.getMessage(), null);
        }
    }
}
