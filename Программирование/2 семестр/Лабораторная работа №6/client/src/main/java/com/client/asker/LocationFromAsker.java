package com.client.asker;

import com.client.console.Console;
import com.common.exceptions.InvalidScriptInputException;
import com.common.model.Location;
import com.client.util.InputProvider;

/**
 * Класс, запрашивающий у пользователя данные для создания точки отправления
 * @author Ivan Kirillov
 */
public final class LocationFromAsker extends Asker<Location> {
    private final Console console;
    private final InputProvider inputProvider;

    /**
     * Конструктор класса
     */
    public LocationFromAsker(Console console, InputProvider inputProvider) {
        this.console = console;
        this.inputProvider = inputProvider;
    }

    /**
     * Запрашивает у пользователя координату X
     * @return координата X
     */
    public Long askX(){
        var isInteractive = inputProvider.isInteractive();

        while (true) {
            try {
                var strX = inputProvider.readLine("Введите X: ");
                long x = Long.parseLong(strX);

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
                var strY = inputProvider.readLine("Введите Y: ");
                int y = Integer.parseInt(strY);

                if (!isInteractive) console.println(y);
                return y;
            } catch (Exception e) {
                if (!isInteractive) throw new InvalidScriptInputException("Ошибка в скрипте: " + e.getMessage());
                console.printErr("Ошибка ввода: " + e.getMessage());
            }
        }
    }

    /**
     * Запрашивает у пользователя координату Z
     * @return координата Z
     */
    public Float askZ(){
        var isInteractive = inputProvider.isInteractive();

        while (true) {
            try {
                var strZ = inputProvider.readLine("Введите Z: ");
                float z = Float.parseFloat(strZ);

                if (!isInteractive) console.println(z);
                return z;
            } catch (Exception e) {
                if (!isInteractive) throw new InvalidScriptInputException("Ошибка в скрипте: " + e.getMessage());
                console.printErr(e.getMessage());
            }
        }
    }

    @Override
    public Location builder() {
        console.println("Ввод координат точки отправления");
        return new Location(askX(), askY(), askZ());
    }
}
