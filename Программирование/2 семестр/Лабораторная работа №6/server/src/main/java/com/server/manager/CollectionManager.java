package com.server.manager;

import com.common.model.Route;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Мененджер коллекции - управляет коллекцией
 * @author Ivan Kirillov
 */
public final class CollectionManager {
    private final java.time.ZonedDateTime initialTime;
    private final Collection<Route> routes = new HashSet<>();

    /**
     * Конструктор класса мененджера коллекции
     */
    public CollectionManager() {
        initialTime = java.time.ZonedDateTime.now();
    }

    /**
     * Метод
     * @param id ID, по которому необходимо совершить поиск
     * @return возвращает маршрут с указанным ID
     */
    public Route findById(long id) {
        return routes.stream()
                .filter(route -> route.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * @param name подстрока, по которой необходимо найти маршрут
     * @return возвращает маршрут, поле name которого содержит указанную подстроку
     */
    public String findByName(String name) {
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        return routes.stream()
                .filter(route -> {
                    Matcher matcher = pattern.matcher(route.getName());
                    return matcher.find();
                })
                .map(Route::toString)
                .collect(java.util.stream.Collectors.joining("\n"));
    }

    /**
     *
     * @return возвращает среднюю дистанцию среди всех маршрутов
     */
    public Double averageDistance() {
        return routes.stream()
                .mapToDouble(Route::getDistance)
                .average()
                .orElse(0);
    }

    /**
     *
     * @return маршруты в порядке убывания
     */
    public String descendSort() {
        return routes.stream()
                .sorted(Comparator.reverseOrder())
                .map(Route::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     *
     * @return возвращает максимальный маршрут
     */
    public Route maxElement() {
        return routes.stream()
                .max(Route::compareTo)
                .orElse(null);
    }

    /**
     * Добавляет маршрут в коллекцию
     * @param r маршрут, который нужно добавить в коллекцию
     */
    public void inputElement(Route r) {
        routes.add(r);
    }

    /**
     * Удаляет маршрут из коллекции
     * @param r маршрут, который нужно удалить из коллекции
     */
    public void removeElement(Route r) {
        routes.remove(r);
    }

    /**
     * Удаляет маршруты, меньше заданного
     * @param refRoute "эталонный" маршрут
     */
    public void removeLower(Route refRoute) {
        routes.removeIf(r -> r.compareTo(refRoute) < 0);
    }

    /**
     * Предоставляет информацию о коллекции
     * @return информация о коллекции (тип, время инициализации, размер)
     */
    public String getInfo() {
        return "Тип коллекции: " + routes.getClass().getSimpleName() + "\nВремя инициализации: " + initialTime + "\nРазмер: " +  routes.size();
    }


    public void setRoutes(Collection<Route> routes) {
        this.routes.addAll(routes);
    }

    /**
     * @return элементы коллекции
     */
    public Collection<Route> getRoutes() {
        return routes;
    }

    /**
     * Удаляет все элементы из коллекции
     */
    public void clearCollection() {
        routes.clear();
    }

    @Override
    public String toString() {
        if (routes.isEmpty()) return "Коллекция пуста\n";

        return routes.stream()
                .sorted(Comparator.comparing(Route::getId))
                .map(Route::toString)
                .collect(java.util.stream.Collectors.joining("\n"));
    }
}
