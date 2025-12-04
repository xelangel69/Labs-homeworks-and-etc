import Pokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

/**
*Main
*/
public class Main
{
    /**
     *Пустой метод
     */
    public Main(){

    }

    /**
     * Точка входа в программу.
     * Создаёт битву и добавляет по три покемона в команды союзников и противников, затем запускает битву
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args)
    {
        Battle b = new Battle();

        Pokemon ally1 = new Xurkitree("Xurkitree", 45);
        Pokemon ally2 = new Nidoking("Nidoking", 45);
        Pokemon ally3 = new Steelix("Steelix", 45);

        Pokemon foe1 = new Nidorino("Nidorino", 45);
        Pokemon foe2 = new Onix("Onix", 45);
        Pokemon foe3 = new Nidoran("Nidoran", 45);

        b.addAlly(ally1);
        b.addAlly(ally2);
        b.addAlly(ally3);

        b.addFoe(foe1);
        b.addFoe(foe2);
        b.addFoe(foe3);

        b.go();
    }
}
