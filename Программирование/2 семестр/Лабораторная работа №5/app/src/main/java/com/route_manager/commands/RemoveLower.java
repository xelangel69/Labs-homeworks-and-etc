package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.manager.CollectionManager;
import com.route_manager.model.Route;
import com.route_manager.model.asker.RouteAsker;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду remove_lower {element}
 * @author Ivan Kirillov
 */
public final class RemoveLower extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды remove_lower
     */
    public RemoveLower(CollectionManager collectionManager, Console console) {
        super("remove_lower", "Удалить из коллекции все маршруты, меньшие, чем определенный маршрут");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        console.printByProgram("СОЗДАНИЕ ОБЪЕКТА");

        Route route = new RouteAsker(console, inputProvider).builder();

        collectionManager.removeLower(route);
        console.printSuccess("Объекты, меньше заданного, удалены");

        return true;
    }
}
