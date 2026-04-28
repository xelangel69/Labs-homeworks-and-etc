package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.manager.CollectionManager;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду info
 * @author Ivan Kirillov
 */
public final class Info extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды info
     * @param collectionManager мененджер коллекции
     */
    public Info(CollectionManager collectionManager, Console console) {
        super("info", "Вывести информацию о коллекции маршрутов (тип, дата инициализации, количество элементов)");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        console.println(collectionManager.getInfo());
        return true;
    }
}
