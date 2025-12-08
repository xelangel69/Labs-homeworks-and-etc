package Places;

/**
 * Перечисление доступных локаций (мест действия) в симуляции.
 */
public enum Locations {

    /** Кухня. */
    KITCHEN("кухня"),

    /** Комната Малыша. */
    LIVING_ROOM ("комната Малыша");

    /** Строковое название локации для вывода в консоль. */
    private final String name;

    /**
     * Конструктор элемента локации.
     *
     * @param name название локации.
     */
    Locations(String name) {
        this.name = name;
    }

    /**
     * Возвращает строковое название локации.
     *
     * @return строка с названием локации.
     */
    public String getName() {
        return name;
    }
}