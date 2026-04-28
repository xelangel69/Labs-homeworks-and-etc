package com.common.exceptions;

/**
 * Исключение, выбрасываемое при ошибке выполнения команды
 * @author Ivan Kirillov
 */
public class FailedCommandExecutionException extends RuntimeException {
    public FailedCommandExecutionException(String message) {
        super(message);
    }
}
