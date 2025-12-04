package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;

/**
 * Класс, представляющий аттаку IceBeam.
 * Наследуется от класса SpecialMove.
 */
public final class IceBeam extends SpecialMove
{
    /**
     * Конструктор атаки IceBeam.
     */
    public IceBeam()
    {
        super(Type.ICE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {
        if (Math.random() <= 0.1)
        {
            Effect.freeze(p);
        }
    }

    @Override
    protected String describe()
    {
        return "использует Ice Beam!";
    }
}
