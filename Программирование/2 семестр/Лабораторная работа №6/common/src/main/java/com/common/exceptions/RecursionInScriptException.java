package com.common.exceptions;

/**
 * Исключение, выбрасываемое при обнаружении рекурсии в скрипте
 * @author Ivan Kirillov
 */
public class RecursionInScriptException extends RuntimeException {
    public RecursionInScriptException(String message) {
        super(message);
    }
}
