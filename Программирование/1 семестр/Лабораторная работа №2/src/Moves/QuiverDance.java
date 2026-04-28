package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Stat;

/**
 * Класс, представляющий аттаку QuiverDance.
 * Наследуется от класса StatusMove.
 */
public final class QuiverDance extends StatusMove
{
    /**
     * Конструктор атаки QuiverDance.
     */
    public QuiverDance()
    {
        super(Type.BUG, 0, 0);
    }

    @Override
        protected void applySelfEffects(Pokemon p)
    {
        p.setMod(Stat.SPECIAL_ATTACK, 1);
        p.setMod(Stat.SPECIAL_DEFENSE, 1);
        p.setMod(Stat.SPEED, 1);
    }

    @Override
    protected String describe()
    {
        return "использует Quiver Dance!";
    }
}
