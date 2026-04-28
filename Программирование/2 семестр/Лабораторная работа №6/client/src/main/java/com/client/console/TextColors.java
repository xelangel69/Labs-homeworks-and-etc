package com.client.console;

/**
 * Перечисление цветов для консольного вывода
 * @author Ivan Kirillov
 */
public enum TextColors {
    RESET ("\u001B[0m"),
    RED ("\u001B[31m"),
    GREEN ("\u001B[32m"),
    YELLOW ("\u001B[33m"),
    WHITE ("\u001B[37m");

    private final String code;

    TextColors(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}

