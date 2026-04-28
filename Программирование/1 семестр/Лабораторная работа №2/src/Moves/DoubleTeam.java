package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Stat;

/**
 * Класс, представляющий аттаку DoubleTeam.
 * Наследуется от класса StatusMove.
 */
public final class DoubleTeam extends StatusMove
{
    /**
     * Конструктор атаки DoubleTeam.
     */
    public DoubleTeam()
    {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p)
    {
        p.setMod(Stat.EVASION, 1);
    }

    @Override
    protected String describe()
    {
        return "использует Double Team (увеличивает уклончивость)!";
    }
}