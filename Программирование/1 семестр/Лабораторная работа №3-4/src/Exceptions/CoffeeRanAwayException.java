package Exceptions;

/**
 * Исключение, сигнализирующее о неудаче в процессе приготовления кофе.
 */
public class CoffeeRanAwayException extends Exception {

    /**
     * Создает новый экземпляр исключения с указанным сообщением.
     *
     * @param message описание ошибки (например, "Кофе убежал!").
     */
    public CoffeeRanAwayException(String message) {
        super(message);
    }

    /**
     * Возвращает сообщение об ошибке.
     *
     * @return строка с описанием причины исключения.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}