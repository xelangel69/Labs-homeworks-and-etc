package Characters;

import Exceptions.BunsNotFreshException;
import Exceptions.KidEatBunsException;
import Items.Buns;
import Places.*;
import Interfaces.Food;

import java.util.ArrayList;
import java.util.Random;

/**
 * Класс, представляющий персонажа "Малыш".
 */
public final class Kid extends Persona implements Food {

    /**
     * Конструктор персонажа.
     */
    public Kid() {
        super("Малыш", Mood.DEFAULT, Locations.LIVING_ROOM);
    }

    Random random = new Random();

    /**
     * Реализует реакцию Малыша на появление другого персонажа в той же локации.
     *
     * @param p персонаж, который вошел в локацию.
     */
    @Override
    public void reactToEnter(Persona p) {
        if (p instanceof Karlsson) {
            System.out.println("Малыш обрадовался приходу персонажа " + p.getName());
            setMood(Mood.HAPPY);
        } else {
            System.out.println("Малыш испугался прихода персонажа " + p.getName());
            setMood(Mood.SCARED);
        }
    }

    /**
     * Описывает перемещение Малыша в новую локацию.
     *
     * @param l целевая локация {@link Locations}.
     */
    @Override
    public void moveTo(Locations l){
        System.out.println("Малыш идет в локацию " + l.getName());
    }

    /**
     * Возвращает строковое представление состояния Малыша.
     *
     * @return строка, содержащая информацию о текущей локации и настроении.
     */
    @Override
    public String toString() {
        return "Малыш находится в локации: " + getLocation() + ". Настроение Малыша: " + getMood();
    }

    @Override
    public void makeCoffee() {
        System.out.println("Малыш не смог приготовить кофе");
    }

    @Override
    public void eatBuns(ArrayList<Buns> buns) throws KidEatBunsException {
        int chanceToSteal = random.nextInt(10);
        if (chanceToSteal >= 5) {
            System.out.println("Малыш съел плюшку");
        } else {
            throw new KidEatBunsException("Съесть плюшку не получилось.");
        }
    }
}