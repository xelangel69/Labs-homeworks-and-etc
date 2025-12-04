package Pokemons;

import Moves.*;

/**
 * Класс, представляющий покемона Nidoking.
 * Наследуется от класса Nidorino.
 */
public final class Nidoking extends Nidorino
{
    /**
     * Конструктор покемона Nidoking.
     *
     * @param name  имя покемона
     * @param level уровень покемона
     */
    public Nidoking(String name, int level)
    {
        super(name, level);
        addMove(new Swagger());
    }
}
