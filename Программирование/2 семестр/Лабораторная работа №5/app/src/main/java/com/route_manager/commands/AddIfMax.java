package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.manager.CollectionManager;
import com.route_manager.model.Route;
import com.route_manager.model.asker.RouteAsker;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду add_if_max {element}
 * @author Ivan Kirillov
 */
public final class AddIfMax extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды add_if_max
     */
    public AddIfMax(CollectionManager collectionManager, Console console) {
        super("add_if_max", "Создать новый маршрут и добавить в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        console.printByProgram("СОЗДАНИЕ ОБЪЕКТА");

        Route route = new RouteAsker(console, inputProvider).builder();

        if (route.compareTo(collectionManager.maxElement()) > 0){
            collectionManager.inputElement(route);

            console.printSuccess("Маршрут добавлен в коллекцию");
            return true;
        } else {
            console.println("Маршрут не был добавлен, т.к. объект меньше максимального");
            return false;
        }
    }
}
