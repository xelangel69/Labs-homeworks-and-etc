package com.client.asker;

import com.common.exceptions.InvalidScriptInputException;
import com.common.exceptions.FileIsEmptyException;
import com.common.model.Coordinates;
import com.common.model.Location;
import com.common.model.Route;
import com.client.console.Console;
import com.client.util.InputProvider;

/**
 * Класс, запрашивающий у пользователя данные для создания нового маршрута
 * @author Ivan Kirillov
 */
public final class RouteAsker extends Asker<Route> {
    private final Console console;
    private final InputProvider inputProvider;

    /**
     * Конструктор класса
     */
    public RouteAsker(Console console, InputProvider inputProvider) {
        this.console = console;
        this.inputProvider = inputProvider;
    }

    @Override
    public Route builder(){
        return new Route(askName(),
                askCoordinates(),
                askFrom(),
                askTo(),
                askDistance());
    }

    /**
     * Запрашивает у пользователя название маршрута
     * @return название маршрута
     */
    public String askName(){
        var isInteractive = inputProvider.isInteractive();

        while(true){
            try {
                var name = inputProvider.readLine("Введите название маршрута: ");

                if (name.isEmpty()) throw new FileIsEmptyException("Имя не может быть пустым!");

                if (!isInteractive) console.println(name);
                return name;
            } catch (Exception e) {
                if (!isInteractive) throw new InvalidScriptInputException("Ошибка в скрипте: " + e.getMessage());
                console.printErr("Ошибка ввода: " + e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя координаты
     * @return координаты (X, Y)
     */
    public Coordinates askCoordinates() {
        var coordinatesAsker = new CoordinatesAsker(console, inputProvider);
        return coordinatesAsker.builder();
    }

    /**
     * Запрашивает у пользователя место отправления
     * @return координаты (X, Y, Z)
     */
    public Location askFrom() {
        var locationAsker = new LocationFromAsker(console, inputProvider);
        return locationAsker.builder();
    }

    /**
     * Запрашивает у пользователя место прибытия
     * @return координаты (X, Y, Z)
     */
    public Location askTo() {
        var locationAsker = new LocationToAsker(console, inputProvider);
        return locationAsker.builder();
    }

    /**
     * Запрашивает у пользователя длину маршрута
     * @return дистанция маршрута
     */
    public Float askDistance() {
        var isInteractive = inputProvider.isInteractive();

        while(true){
            try {
                var strDistance = inputProvider.readLine("Введите дистанцию (> 1): ");;

                if (strDistance.isEmpty()) {
                    return null;
                } else {
                    float distance = Float.parseFloat(strDistance);

                    if (distance <= 1) throw new IllegalArgumentException("Число должно быть больше 1!");

                    if (!isInteractive) console.println(distance);
                    return distance;
                }
            } catch (Exception e) {
                if (!isInteractive) throw new InvalidScriptInputException("Ошибка в скрипте: " + e.getMessage());
                console.printErr("Ошибка ввода: " + e.getMessage());
            }
        }
    }
}
