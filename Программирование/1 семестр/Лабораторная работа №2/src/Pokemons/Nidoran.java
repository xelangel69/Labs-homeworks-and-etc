package Pokemons;

import Moves.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

/**
 * Класс, представляющий покемона Nidoran.
 * Наследуется от класса Pokemon.
 */
public class Nidoran extends Pokemon
{
    /**
     * Конструктор покемона Nidoran.
     *
     * @param name  имя покемона
     * @param level уровень покемона
     */
    public Nidoran(String name, int level)
    {
        super(name, level);
        setStats(46, 57, 40, 40, 40, 50);
        setType(Type.POISON);
        setMove(new SludgeBomb(), new IceBeam());
    }
}
