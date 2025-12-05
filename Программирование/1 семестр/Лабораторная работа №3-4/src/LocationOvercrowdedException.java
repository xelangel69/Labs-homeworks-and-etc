/**
 * Исключение, указывающее на попытку превышения максимальной вместимости локации.
 */
public class LocationOvercrowdedException extends Exception {

    /**
     * Создает новый экземпляр исключения с заданным сообщением.
     *
     * @param message описание контекста ошибки.
     */
    public LocationOvercrowdedException(String message) {
        super(message);
    }

    /**
     * Возвращает детальное сообщение об ошибке.
     *
     * @return строка сообщения об ошибке.
     */
    @Override
    public String getMessage() {
        return super.getMessage() + " Локация переполнена!";
    }
}