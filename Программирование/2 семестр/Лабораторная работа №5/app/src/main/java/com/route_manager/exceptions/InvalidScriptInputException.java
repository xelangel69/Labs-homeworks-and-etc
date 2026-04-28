package com.route_manager.exceptions;

/**
 * Исключение, выбрасываемое если в скрипте ошибка
 * @author Ivan Kirillov
 */
public class InvalidScriptInputException extends RuntimeException {
    public InvalidScriptInputException(String message) {
        super(message);
    }
}
