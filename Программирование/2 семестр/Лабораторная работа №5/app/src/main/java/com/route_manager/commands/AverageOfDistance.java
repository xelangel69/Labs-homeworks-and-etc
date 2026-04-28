package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.manager.CollectionManager;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду average_of_distance
 * @author Ivan Kirillov
 */
public final class AverageOfDistance extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды average_of_distance
     */
    public AverageOfDistance(CollectionManager collectionManager, Console console) {
        super("average_of_distance", "Вывести среднее расстояние всех маршрутов");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        Double averageDistance = collectionManager.averageDistance();
        console.println("Среднее расстояние - " + averageDistance);
        return true;
    }
}
