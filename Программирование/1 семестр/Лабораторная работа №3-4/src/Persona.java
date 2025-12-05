import java.util.Random;

/**
 * Абстрактный класс {@code Persona}, представляющий базовую сущность персонажа в симуляции.
 * <p>
 * Класс объединяет общие характеристики всех персонажей: имя, настроение и текущее местоположение.
 * Реализует интерфейсы {@link MovingTo} для перемещения и {@link EmotionalState} для управления эмоциями.
 * </p>
 */
public abstract class Persona implements MovingTo, EmotionalState {

    /** Имя персонажа. */
    private String name;

    /** Текущее настроение персонажа. */
    private Mood mood;

    /** Текущее местоположение персонажа. */
    private Locations locations;

    /**
     * Конструктор для создания нового персонажа.
     *
     * @param name      имя персонажа (не должно быть null или пустым).
     * @param mood      начальное настроение персонажа.
     * @param locations начальное местоположение персонажа.
     */
    public Persona(String name, Mood mood, Locations locations) {
        this.name = name;
        this.mood = mood;
        this.locations = locations;
    }

    /**
     * Устанавливает новое имя персонажа.
     *
     * @param name новое имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает имя персонажа.
     *
     * @return строковое представление имени персонажа.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает настроение персонажа.
     * Метод реализует логику из интерфейса {@link EmotionalState}.
     *
     * @param mood новое настроение (объект перечисления {@link Mood}).
     */
    public void setMood(Mood mood) {
        this.mood = mood;
    }

    /**
     * Возвращает текущее настроение персонажа.
     *
     * @return текущий объект {@link Mood}.
     */
    public Mood getMood() {
        return mood;
    }

    /**
     * Устанавливает текущее местоположение персонажа.
     *
     * @param locations новая локация (объект перечисления {@link Locations}).
     */
    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    /**
     * Возвращает текущее местоположение персонажа.
     *
     * @return текущая локация {@link Locations}.
     */
    public Locations getLocations() {
        return locations;
    }

    /**
     * Перемещает персонажа в указанную локацию.
     * Реализация интерфейса {@link MovingTo}.
     *
     * @param location целевая локация для перемещения.
     */
    @Override
    public void moveTo(Locations location) {
        this.locations = location;
    }

    /**
     * Определяет реакцию на появление другого персонажа.
     * <p>
     * Базовая реализация устанавливает настроение в {@code Mood.DEFAULT}.
     * Может быть переопределен в классах-наследниках для специфического поведения.
     * </p>
     *
     * @param persona персонаж, который вошел в локацию.
     */
    @Override
    public void reactToEnter(Persona persona){
        System.out.println("Реакция на вход другого персонажа: ");
        setMood(Mood.DEFAULT);
    }

    /**
     * Возвращает строковое представление объекта персонажа.
     * Включает имя, настроение и местоположение.
     *
     * @return строка с информацией о персонаже.
     */
    @Override
    public String toString() {
        return "Имя персонажа: " + name + ". Настроение: : " + mood + ". Местонахождение: : " + locations;
    }
}