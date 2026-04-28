package com.client.asker;

import com.client.console.Console;
import com.common.exceptions.InvalidScriptInputException;
import com.common.model.Coordinates;
import com.client.util.InputProvider;

/**
 * Класс, запрашивающий у пользователя данные для создания нового маршрута
 * @author Ivan Kirillov
 */
public final class CoordinatesAsker extends Asker<Coordinates> {
    private final Console console;
    private final InputProvider inputProvider;

    /**
     * Конструктор класса
     */
    public CoordinatesAsker(Console console, InputProvider inputProvider) {
        this.console = console;
        this.inputProvider = inputProvider;
    }

    /**
     * Запрашивает у пользователя координату X
     * @return координата X
     */
    public Double askX() {
        var isInteractive = inputProvider.isInteractive();

        while (true) {
            try {

                var strX = inputProvider.readLine("Введите X: ");
                double x = Double.parseDouble(strX);

                if (!isInteractive) console.println(x);
                return x;
            } catch (Exception e) {
                if (!isInteractive) throw new InvalidScriptInputException("Ошибка в скрипте: " + e.getMessage());
                console.printErr("Ошибка ввода: " + e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя координату Y
     * @return координата Y
     */
    public Integer askY(){
        var isInteractive = inputProvider.isInteractive();

        while (true) {
            try {
                var strY = inputProvider.readLine("Введите Y (<= 71): ");
                int y = Integer.parseInt(strY);

                if (y > 71) throw new IllegalArgumentException("Y должен быть меньше 71");

                if (!isInteractive) console.println(y);
                return y;
            } catch (Exception e) {
                if (!isInteractive) throw new InvalidScriptInputException("Ошибка в скрипте: " + e.getMessage());
                console.printErr("Ошибка ввода: " + e.getMessage());
            }
        }
    }

    @Override
    public Coordinates builder() {
        console.println("Ввод координат");
        return new Coordinates(askX(), askY());
    }
}
