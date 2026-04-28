package Places;

import Characters.Persona;
import Exceptions.LocationOvercrowdedException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Абстрактный базовый класс, представляющий локацию (место действия) в симуляции.
 */
public abstract class Location {

    /** Название локации. */
    private final String name;

    /** Максимально допустимое количество персонажей в локации. */
    private final int maxCapacity;

    /** Список персонажей, находящихся в данный момент в локации. */
    private final ArrayList<Persona> charactersIn;

    /**
     * Конструктор для создания новой локации.
     *
     * @param name        название локации.
     * @param maxCapacity максимальное количество персонажей, которые могут находиться здесь одновременно.
     */
    protected Location(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.charactersIn = new ArrayList<>();
    }

    /**
     * Возвращает название локации.
     *
     * @return строковое название.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает список персонажей, находящихся в локации.
     *
     * @return список объектов {@link Persona}.
     */
    public ArrayList<Persona> getCharactersIn() {
        return charactersIn;
    }

    /**
     * Удаляет персонажа из локации.
     *
     * @param person персонаж, который должен покинуть локацию.
     */
    public void removeCharacter(Persona person) {
        if (charactersIn.remove(person)) {
            System.out.println(person.getName() + " вышел из локации " + this.name);
        } else {
            System.out.println(person.getName() + " не найден в локации " + this.name);
        }
    }

    /**
     * Добавляет персонажа в локацию.
     *
     * @param person персонаж, который входит в локацию.
     * @throws LocationOvercrowdedException если количество персонажей достигло {@code maxCapacity}.
     */
    public void addCharacter(Persona person) throws LocationOvercrowdedException {
        if (charactersIn.size() >= maxCapacity) {
            throw new LocationOvercrowdedException("На локации слишком тесно!");
        }

        charactersIn.add(person);
        System.out.println(person.getName() + " вошел в локацию " + this.name);

        for (Persona resident : new ArrayList<>(charactersIn)) {
            if (!resident.equals(person)) {
                resident.reactToEnter(person);
            }
        }
    }

    /**
     * Сравнивает текущую локацию с другим объектом.
     *
     * @param o объект для сравнения.
     * @return {@code true}, если объекты равны, иначе {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o; // ИСПРАВЛЕНО: каст к places.Location
        return Objects.equals(name, location.name);
    }

    /**
     * Возвращает хеш-код локации.
     * Основан на названии локации.
     *
     * @return целочисленный хеш-код.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Возвращает строковое представление локации.
     * В данной реализации возвращает строковое представление списка персонажей внутри.
     *
     * @return строка со списком персонажей.
     */
    @Override
    public String toString() {
        return charactersIn.toString();
    }
}