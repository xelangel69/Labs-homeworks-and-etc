package Moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

/**
 * Класс, представляющий аттаку RockThrow.
 * Наследуется от класса PhysicalMove.
 */
public final class RockThrow extends PhysicalMove
{
    /**
     * Конструктор атаки RockThrow.
     */
    public RockThrow()
    {
        super(Type.ROCK, 50, 90);
    }

    @Override
    protected String describe()
    {
        return "использует Rock Throw";
    }
}
