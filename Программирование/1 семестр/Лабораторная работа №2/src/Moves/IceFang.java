package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;

/**
 * Класс, представляющий аттаку IceFang.
 * Наследуется от класса PhysicalMove.
 */
public final class IceFang extends PhysicalMove
{
    /**
     * Конструктор атаки IceFang.
     */
    public IceFang()
    {
        super(Type.ICE, 65, 95);
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
        return "использует Ice Fang!";
    }
}
