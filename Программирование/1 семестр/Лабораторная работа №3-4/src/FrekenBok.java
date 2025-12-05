import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Класс, представляющий персонажа "Фрекен Бок".
 */
public final class FrekenBok extends Persona implements Food {

    /** Генератор случайных чисел для определения исхода событий (варка кофе, решение проверить Малыша). */
    private final Random random = new Random();

    /**
     * Конструктор персонажа.
     */
    public FrekenBok() {
        super("Фрекен Бок", Mood.DEFAULT, Locations.KITCHEN);
    }

    /**
     * Реакция Фрекен Бок на вход другого персонажа.
     *
     * @param p персонаж, вошедший в локацию.
     */
    @Override
    public void reactToEnter(Persona p){
        System.out.println("Фрекен Бок не обрадовалась появлению персонажа " + p.getName());
        setMood(Mood.ANNOYED);
        System.out.println("Настроение Фрекен Бок изменилось на " + getMood());
    }

    /**
     * Принимает решение о проверке Малыша.
     *
     * @return {@code true} с вероятностью 50%, если Фрекен Бок решает пойти проверить Малыша,
     *         иначе {@code false}.
     */
    public boolean decideToCheckKid() {
        Random r = new Random();
        return r.nextBoolean();
    }

    /**
     * Процесс приготовления кофе.
     *
     * @throws CoffeeRanAwayException если кофе "убежал" (неудачная попытка приготовления).
     */
    @Override
    public void makeCoffee() throws CoffeeRanAwayException{
        System.out.println("Фрекен Бок готовит кофе");
        if (random.nextInt(8) + 2 > 5){
            System.out.println("Кофе получился что надо!");
        }
        else{
            throw new CoffeeRanAwayException("Кофе убежал!");
        }
    }

    /**
     * Процесс поедания плюшек.
     *
     * @param buns список плюшек, находящихся на столе.
     * @throws BunsNotFreshException если попадается плюшка со свежестью ниже порога {@link Buns#FRESHNESS_THRESHOLD}. В этом случае поедание прекращается.
     */
    @Override
    public void eatBuns(ArrayList<Buns> buns) throws BunsNotFreshException {
        System.out.println("Фрекен Бок собиралась провести в тишине несколько приятных минут, заедая кофе свежими плюшками.");

        Iterator<Buns> iterator = buns.iterator();
        while (iterator.hasNext()) {
            Buns bun = iterator.next();
            if (bun.freshness() > Buns.FRESHNESS_THRESHOLD) {
                System.out.println("Фрекен Бок съела вкусную " + bun + ".");
                iterator.remove(); // Удаляем съеденную плюшку со стола
            } else {
                throw new BunsNotFreshException("'Фу, эта плюшка несвежая!'", bun);
            }
        }

        if (buns.isEmpty()) {
            System.out.println("Все плюшки были свежими и вкусными!");
            setMood(Mood.HAPPY);
        }
    }

    /**
     * Действие погони за Карлсоном.
     */
    public void chaseKarlsson() {
        System.out.println("Фрекен Бок начинает гоняться за Карлсоном!");
        setMood(Mood.FURIOUS);
    }

    /**
     * Возвращает строковое представление состояния Фрекен Бок.
     *
     * @return строка с текущей локацией и настроением.
     */
    @Override
    public String toString() {
        return "Фрекен Бок находится в локации: " + getLocations() + ". Настроение Фрекен Бок: " + getMood();
    }
}