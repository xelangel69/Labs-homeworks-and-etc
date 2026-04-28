package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.exceptions.RoutesNotFindException;
import com.server.manager.CollectionManager;
import com.common.model.Route;

/**
 * Класс, представляющий консольную команду update {id} {element}
 * @author Ivan Kirillov
 */
public final class UpdateByID extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды update
     */
    public UpdateByID(CollectionManager collectionManager) {
        super("update {ID}", "Обновить маршрут по ID");
        this.collectionManager = collectionManager;
    }

    private void updateRoute(Route oldRoute, Route newRoute) {
        oldRoute.setName(newRoute.getName());
        oldRoute.setCoordinates(newRoute.getCoordinates());
        oldRoute.setFrom(newRoute.getFrom());
        oldRoute.setTo(newRoute.getTo());
        oldRoute.setDistance(newRoute.getDistance());
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        try {
            if (primitiveArg.isEmpty()) return new Response(RequestStatus.ERROR, "ID не был введён", null);

            if (routeArg == null) return new Response(RequestStatus.ERROR, "Маршрут не передан", null);

            long id = Long.parseLong(primitiveArg);

            var route = collectionManager.findById(id);
            if (route == null) return new Response(RequestStatus.ERROR, "Нет объекта с таким ID", null);

            updateRoute(route, routeArg);

            return new Response(RequestStatus.SUCCESS, "Маршрут обновлен!", null);
        } catch (NumberFormatException e) {
            return new Response(RequestStatus.ERROR, "ID должен быть числом!", null);
        }
        catch (IllegalArgumentException | RoutesNotFindException e) {
            return new Response(RequestStatus.ERROR, e.getMessage(), null);
        }
    }
}