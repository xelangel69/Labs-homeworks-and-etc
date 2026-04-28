package Exceptions;

import Items.Buns;

/**
 * Исключение, указывающее на попытку поедания несвежей плюшки.
 */
public class BunsNotFreshException extends Exception {

  /** Объект плюшки, которая не прошла проверку свежести. */
  private final Buns unfreshBun;

  /**
   * Создает новый экземпляр исключения.
   *
   * @param message    сообщение, описывающее реакцию персонажа (например, "'Фу, эта плюшка несвежая!'").
   * @param unfreshBun объект {@link Buns}, который оказался несвежим.
   */
  public BunsNotFreshException(String message, Buns unfreshBun) {
    super(message);
    this.unfreshBun = unfreshBun;
  }

  /**
   * Возвращает расширенное сообщение об ошибке.
   *
   * @return строка вида: "Сообщение. Проблема с плюшкой: [информация о плюшке]".
   */
  @Override
  public String getMessage() {
    return super.getMessage() + " Проблема с плюшкой: " + unfreshBun;
  }
}