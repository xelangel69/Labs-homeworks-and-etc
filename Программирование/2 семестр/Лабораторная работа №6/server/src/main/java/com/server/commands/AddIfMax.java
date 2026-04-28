package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.server.manager.CollectionManager;
import com.common.model.Route;

/**
 * Класс, представляющий консольную команду add_if_max {element}
 * @author Ivan Kirillov
 */
public final class AddIfMax extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды add_if_max
     */
    public AddIfMax(CollectionManager collectionManager) {
        super("add_if_max", "Создать новый маршрут и добавить в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        if (routeArg.compareTo(collectionManager.maxElement()) > 0){
            routeArg.setId(Route.generateId());
            routeArg.setCreationDate(java.time.ZonedDateTime.now());

            collectionManager.inputElement(routeArg);
            return new Response(RequestStatus.SUCCESS, "Маршрут добавлен в коллекцию", null);
        } else {
            return new Response(RequestStatus.ERROR, "Маршрут не добавлен, т.к. объект меньше максимального", null);
        }
    }
}
