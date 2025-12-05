/**
 * Неизменяемая запись (Record), представляющая объект "Плюшка".
 *
 * @param freshness числовой показатель свежести плюшки.
 *                  Используется для сравнения с пороговым значением {@link #FRESHNESS_THRESHOLD}.
 */
public record Buns(int freshness) {

    /**
     * Пороговое значение свежести (константа = 5).
     */
    public static final int FRESHNESS_THRESHOLD = 5;

    /**
     * Возвращает строковое представление плюшки.
     *
     * @return строка вида "Свежесть плюшки: [значение]".
     */
    @Override
    public String toString() {
        return "Свежесть плюшки: " + freshness;
    }
}