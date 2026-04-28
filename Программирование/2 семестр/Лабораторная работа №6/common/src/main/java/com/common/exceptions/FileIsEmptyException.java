package com.common.exceptions;

/**
 * Исключение, выбрасываемое если файл с исходными данными пустой
 * @author Ivan Kirillov
 */
public class FileIsEmptyException extends RuntimeException {
    public FileIsEmptyException(String message) {
        super(message);
    }
}
