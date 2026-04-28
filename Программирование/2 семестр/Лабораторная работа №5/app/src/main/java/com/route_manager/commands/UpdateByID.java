package com.route_manager.commands;

import com.route_manager.console.Console;
import com.route_manager.exceptions.RoutesNotFindException;
import com.route_manager.manager.CollectionManager;
import com.route_manager.model.Route;
import com.route_manager.model.asker.RouteAsker;
import com.route_manager.util.InputProvider;

/**
 * Класс, представляющий консольную команду update {id} {element}
 * @author Ivan Kirillov
 */
public final class UpdateByID extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    /**
     * Конструктор класса команды update
     */
    public UpdateByID(CollectionManager collectionManager, Console console) {
        super("update {ID}", "Обновить маршрут по ID");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public boolean execute(String argument, InputProvider inputProvider) {
        try {
            if (argument.isEmpty()) throw new IllegalArgumentException("Введите ID маршрута!");

            long id = Long.parseLong(argument);

            var route = collectionManager.findById(id);
            if (route == null) throw new RoutesNotFindException("Нет объекта с таким ID!");

            console.println("Введите новые данные для маршрута:");
            var newRoute = new RouteAsker(console, inputProvider).builder();

            updateRoute(route, newRoute);

            console.printSuccess("Маршрут обновлен!");
            return true;
        } catch (NumberFormatException e) {
            console.printErr("ID должен быть числом!");
            return false;
        }
        catch (IllegalArgumentException e) {
            console.printErr(e.getMessage());
        } catch (RoutesNotFindException e) {
            console.printErr(e.getMessage());
            return false;
        }
        return false;
    }

    private void updateRoute(Route oldRoute, Route newRoute) {
        oldRoute.setName(newRoute.getName());
        oldRoute.setCoordinates(newRoute.getCoordinates());
        oldRoute.setFrom(newRoute.getFrom());
        oldRoute.setTo(newRoute.getTo());
        oldRoute.setDistance(newRoute.getDistance());
    }
}