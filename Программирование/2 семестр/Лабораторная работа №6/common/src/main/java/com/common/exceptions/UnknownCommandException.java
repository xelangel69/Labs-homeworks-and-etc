package com.common.exceptions;

/**
 * Исключение, выбрасываемое при вводе неизвестной команды
 * @author Ivan Kirillov
 */
public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
