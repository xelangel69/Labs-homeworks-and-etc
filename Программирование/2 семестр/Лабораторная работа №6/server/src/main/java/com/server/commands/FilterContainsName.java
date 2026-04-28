package com.server.commands;

import com.common.network.RequestStatus;
import com.common.network.Response;
import com.common.exceptions.RoutesNotFindException;
import com.common.model.Route;
import com.server.manager.CollectionManager;

import java.util.Objects;

/**
 * Класс, представляющий консольную команду filter_contains_name {name}
 * @author Ivan Kirillov
 */
public final class FilterContainsName extends Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор класса команды filter_contains_name
     */
    public FilterContainsName(CollectionManager collectionManager) {
        super("filter_contains_name {подстрока}", "Вывести маршруты, названия которых содержат указанную подстроку");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String primitiveArg, Route routeArg) {
        try {
            if (primitiveArg.isEmpty()) throw new IllegalArgumentException("Нужно ввести подстроку!");

            var route = collectionManager.findByName(primitiveArg);
            if (Objects.equals(route, "")) throw new RoutesNotFindException("Нет маршрутов с такой подстрокой в названии!");

            return new Response(RequestStatus.SUCCESS, "Результаты поиска:\n" + route, null);
        } catch (IllegalArgumentException | RoutesNotFindException e) {
            return new Response(RequestStatus.ERROR, "Ошибка выполнения - " + e.getMessage(), null);
        }
    }
}
