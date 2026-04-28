package Pokemons;

import Moves.*;
import ru.ifmo.se.pokemon.Pokemon;

/**
 * Класс, представляющий покемона Onix.
 * Наследуется от класса Pokemon.
 */
public class Onix extends Pokemon
{
    /**
     * Конструктор покемона Onix.
     *
     * @param name  имя покемона
     * @param level уровень покемона
     */
    public Onix(String name, int level)
    {
        super(name, level);
        setStats(35, 45, 160, 30, 45, 70);
        setMove(new DoubleTeam(), new RockThrow(), new DragonBreath(), new IceFang());
    }
}
