package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;

/**
 * Класс, представляющий атаку SludgeBomb.
 * Наследуется от класса SpecialMove.
 */
public final class SludgeBomb extends SpecialMove
{
    /**
     * Конструктор атаки SludgeBomb.
     */
    public SludgeBomb()
    {
        super(Type.POISON, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {
        if (Math.random() <= 0.3)
        {
            Effect.poison(p);
        }
    }

    @Override
    protected String describe()
    {
        return "использует Sludge Bomb!";
    }
}
