package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;

/**
 * Класс, представляющий аттаку DragonBreath.
 * Наследуется от класса SpecialMove.
 */
public final class DragonBreath extends SpecialMove
{
    /**
     * Конструктор атаки DragonBreath.
     */
    public DragonBreath()
    {
        super(Type.DRAGON, 60, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {
        if (Math.random() <= 0.3)
        {
            Effect.paralyze(p);
        }
    }

    @Override
    protected String describe()
    {
        return "использует Dragon Breath!";
    }
}
