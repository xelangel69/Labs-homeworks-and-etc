package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.manager.CollectionManager;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду clear
 * @author Ivan Kirillov
 */
public final class Clear extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды clear
     */
    public Clear(CollectionManager collectionManager, Console console) {
        super("clear", "Удалить все маршруты");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        collectionManager.clearCollection();

        console.printSuccess("Коллекция очищена!");
        return true;
    }
}
