package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Stat;

/**
 * Класс, представляющий аттаку Leer.
 * Наследуется от класса StatusMove.
 */
public final class Leer extends StatusMove
{
    /**
    * Конструктор атаки Leer.
    */
    public Leer()
    {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {
        p.setMod(Stat.DEFENSE, -1);
    }

    @Override
    protected String describe()
    {
        return "использует Leer (снижает защиту)!";
    }
}
