package com.route_manager.commands;

import com.route_manager.util.InputProvider;

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

    public abstract boolean execute(String argument, InputProvider inputProvider);

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