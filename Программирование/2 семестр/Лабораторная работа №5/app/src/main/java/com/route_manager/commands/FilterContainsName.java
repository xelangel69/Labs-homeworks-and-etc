package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.exceptions.RoutesNotFindException;
import com.route_manager.manager.CollectionManager;
import com.route_manager.util.InputProvider;

import java.util.Objects;

/**
 * Класс, представляющий консольную команду filter_contains_name {name}
 * @author Ivan Kirillov
 */
public final class FilterContainsName extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды filter_contains_name
     */
    public FilterContainsName(CollectionManager collectionManager, Console console) {
        super("filter_contains_name {подстрока}", "Вывести маршруты, названия которых содержат указанную подстроку");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        try {
            if (argument.isEmpty()) throw new IllegalArgumentException("Нужно ввести подстроку!");

            var route = collectionManager.findByName(argument);
            if (Objects.equals(route, "")) {
                throw new RoutesNotFindException("Нет маршрутов с такой подстрокой в названии!");
            }

            console.print("Результаты поиска:\n" + route);
            return true;
        } catch (IllegalArgumentException e) {
            console.printErr(e.getMessage());
        } catch (RoutesNotFindException e) {
            console.printErr(e.getMessage());
            return false;
        }
        return false;
    }
}
