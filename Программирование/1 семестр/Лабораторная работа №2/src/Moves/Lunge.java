package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Stat;

/**
 * Класс, представляющий аттаку Lunge.
 * Наследуется от класса PhysicalMove.
 */
public final class Lunge extends PhysicalMove
{
    /**
     * Конструктор атаки Lunge.
     */
    public Lunge()
    {
        super(Type.BUG, 80, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {
        p.setMod(Stat.ATTACK, -1);
    }

    @Override
    protected String describe()
    {
        return "атакует Lunge (понижает атаку противника)!";
    }
}
