package com.client.console;

import org.jline.terminal.Terminal;

/**
 * Класс реализующий консольный ввод/вывод
 * @author Ivan Kirillov
 */
public final class Console implements ConsoleFunctions {
    private final Terminal terminal;

    public Console(Terminal terminal) {
        this.terminal = terminal;
        terminal.writer().flush();
    }

    /**
     * Вывод в консоль
     * @param obj объект для вывода
     */
    @Override
    public void print(Object obj) {
        terminal.writer().print(obj);
        terminal.writer().flush();
    }

    /**
     * Вывод в консоль с новой строки
     * @param obj объект для вывода
     */
    @Override
    public void println(Object obj) {
        terminal.writer().println(obj);
        terminal.writer().flush();
    }

    /**
     * Вывод таблицы в консоль
     * @param obj1 первый объект (будет выведен слева)
     * @param obj2 второй объект (будет выведен справа)
     */
    @Override
    public void printTable(Object obj1, Object obj2) {
        terminal.writer().printf("%-45s%-1s%n", TextColors.YELLOW + "" + obj1 + TextColors.RESET, obj2);
        terminal.writer().flush();
    }

    /**
     * Вывод ошибки в консоль
     * @param obj объект для вывода
     */
    @Override
    public void printErr(Object obj) {
        terminal.writer().println(TextColors.RED + "" + obj + TextColors.RESET);
        terminal.writer().flush();
    }

    /**
     * Вывод в консоль в случае успешного выполнения команды
     * @param obj объект для вывода
     */
    @Override
    public void printSuccess(Object obj) {
        terminal.writer().println(TextColors.GREEN + "" + obj + TextColors.RESET);
        terminal.writer().flush();
    }

    /**
     * Вывод информационных сообщений в консоль
     * @param obj объект для вывода
     */
    @Override
    public void printByProgram(Object obj) {
        terminal.writer().println(TextColors.YELLOW + "" + obj + TextColors.RESET);
        terminal.writer().flush();
    }
}
