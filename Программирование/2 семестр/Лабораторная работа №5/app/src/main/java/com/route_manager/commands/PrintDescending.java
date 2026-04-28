package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.manager.CollectionManager;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду print_descending
 * @author Ivan Kirillov
 */
public final class PrintDescending extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды print_descending
     */
    public PrintDescending(CollectionManager collectionManager, Console console) {
        super("print_descending", "Вывести маршруты в порядке убывания");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        console.print(collectionManager.descendSort());
        return true;
    }
}
