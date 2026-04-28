package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.manager.CollectionManager;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду show
 * @author Ivan Kirillov
 */
public final class Show extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды show
     */
    public Show(CollectionManager collectionManager, Console console) {
        super("show", "Вывести все маршруты");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        console.print(collectionManager);
        return true;
    }
}
