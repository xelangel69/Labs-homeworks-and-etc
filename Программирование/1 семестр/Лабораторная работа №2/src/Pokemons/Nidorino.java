package Pokemons;

import Moves.*;

/**
 * Класс, представляющий покемона Nidorino.
 * Наследуется от класса Nidoran.
 */
public class Nidorino extends Nidoran
{
    /**
     * Конструктор покемона Nidorino.
     *
     * @param name  имя покемона
     * @param level уровень покемона
     */
    public Nidorino(String name, int level)
    {
        super(name, level);
        addMove(new Leer());
    }
}
