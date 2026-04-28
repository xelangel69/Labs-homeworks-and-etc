package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.model.Route;
import com.server.manager.CollectionManager;

/**
 * Класс, представляющий консольную команду average_of_distance
 * @author Ivan Kirillov
 */
public final class AverageOfDistance extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды average_of_distance
     */
    public AverageOfDistance(CollectionManager collectionManager) {
        super("average_of_distance", "Вывести среднее расстояние всех маршрутов");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        Double averageDistance = collectionManager.averageDistance();
        return new Response(RequestStatus.SUCCESS, "Среднее расстояние - " + averageDistance, null);
    }
}
