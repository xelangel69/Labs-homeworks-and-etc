package Pokemons;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import Moves.*;

/**
 * Класс, представляющий покемона Xurkitree.
 * Наследуется от класса Pokemon.
 */
public final class Xurkitree extends Pokemon
{
    /**
     * Конструктор покемона Xurkitree.
     *
     * @param name  имя покемона
     * @param level уровень покемона
     */
    public Xurkitree(String name, int level)
    {
        super(name, level);
        setStats(83, 89, 71, 173, 71, 83);
        setType(Type.ELECTRIC);
        setMove(new QuiverDance(), new Leer(), new IceBeam(), new Lunge());
    }
}
