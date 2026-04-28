package com.server.commands;

import com.common.network.Response;
import com.common.model.Route;

/**
 * Абстрактный класс, представляющий класс предок для всех консольных команд
 * @author Ivan Kirillov
 */
public abstract class Command {
    private final String name;
    private final String description;

    /**
     * Конструктор класса команд
     * @param name название команды
     * @param description описание команды
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract Response execute(String primitiveArg, Route routeArg);

    /**
     * @return сигнатура команды
     */
    public String getName() {
        return name;
    }

    /**
     * @return описание команды
     */
    public String getDescription() {
        return description;
    }

    /**
     * Выполняет команду
     * @return успешность выполнения команды
     */
    @Override
    public String toString() {
        return "Command{name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}