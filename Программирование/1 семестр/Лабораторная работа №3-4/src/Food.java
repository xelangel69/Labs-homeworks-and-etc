import java.util.ArrayList;

/**
 * Интерфейс, определяющий взаимодействие персонажа с плюшками и кофе.
 */
public interface Food {

    /**
     * Выполняет действие по приготовлению кофе.
     *
     * @throws CoffeeRanAwayException если в процессе приготовления произошла ошибка.
     */
    void makeCoffee() throws CoffeeRanAwayException;

    /**
     * Выполняет действие по поеданию плюшек из предложенного списка.
     *
     * @param buns список объектов {@link Buns}, которые персонаж собирается съесть.
     * @throws BunsNotFreshException если среди плюшек попадается несвежая.
     */
    void eatBuns(ArrayList<Buns> buns) throws BunsNotFreshException;
}