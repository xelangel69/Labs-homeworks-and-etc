package com.client.console;

/**
 * Интерфейс, описывающий основные функции консоли для ввода/вывода команд
 */
public interface ConsoleFunctions {
    void print(Object obj);
    void println(Object obj);
    void printTable(Object obj1, Object obj2);
    void printErr(Object obj);
    void printSuccess(Object obj);
    void printByProgram(Object obj);
}
