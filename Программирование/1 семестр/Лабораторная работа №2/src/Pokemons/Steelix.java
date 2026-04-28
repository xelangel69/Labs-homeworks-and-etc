package Pokemons;

import Moves.*;

/**
 * Класс, представляющий покемона Steelix.
 * Наследуется от класса Onix.
 */
public final class Steelix extends Onix
{
    /**
     * Конструктор покемона Steelix.
     *
     * @param name  имя покемона
     * @param level уровень покемона
     */
    public Steelix(String name, int level)
    {
        super(name, level);
        addMove(new IceFang());
    }
}
