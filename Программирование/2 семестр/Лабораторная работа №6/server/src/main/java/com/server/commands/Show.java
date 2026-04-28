package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.model.Route;
import com.server.manager.CollectionManager;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, представляющий консольную команду show
 * @author Ivan Kirillov
 */
public final class Show extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды show
     */
    public Show(CollectionManager collectionManager) {
        super("show", "Вывести все маршруты");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        List<Route> sortedRoutes = collectionManager.getRoutes().stream()
                .sorted((r1, r2) -> {
                    if (r1.getFrom() == null && r2.getFrom() == null) return 0;
                    if (r1.getFrom() == null) return 1;
                    if (r2.getFrom() == null) return -1;
                    return Long.compare(r1.getFrom().getX(), r2.getFrom().getX());
                })
                .collect(Collectors.toList());

        return new Response(RequestStatus.SUCCESS, "Коллекция успешно получена.", sortedRoutes);
    }
}
