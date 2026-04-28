package Moves;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Stat;

/**
 * Класс, представляющий аттаку Swagger.
 * Наследуется от класса StatusMove.
 */
public final class Swagger extends StatusMove
{
    /**
     * Конструктор атаки Swagger.
     */
    public Swagger()
    {
        super(Type.NORMAL, 0, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon p)
    {
        p.setMod(Stat.ATTACK, 2);
        Effect.confuse(p);
    }

    @Override
    protected String describe()
    {
        return "использует Swagger (повышает атаку, но вызывает путаницу)!";
    }
}
