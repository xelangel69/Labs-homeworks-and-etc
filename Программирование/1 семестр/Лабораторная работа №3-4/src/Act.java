import java.util.ArrayList;
import java.util.Random;

/**
 * Класс, управляющий основным сценарием (актом) симуляции.
 */
public class Act {

    private final Kitchen kitchen = new Kitchen();
    private final LivingRoom livingRoom = new LivingRoom();

    private final Kid kid = new Kid();
    private final FrekenBok frekenBok = new FrekenBok();
    private final Karlsson karlsson = new Karlsson();

    /** Генератор случайных чисел для событий сцены (свежесть плюшек, появление Карлсона). */
    private final Random random = new Random();

    /**
     * Запускает выполнение сценария.
     *
     * @throws InterruptedException если выполнение потока было прервано во время паузы.
     */
    public void start() throws InterruptedException {

        initialPlacement();
        Thread.sleep(800);

        cookCoffee();
        Thread.sleep(800);
    }

    /**
     * Выполняет начальную расстановку персонажей.
     */
    private void initialPlacement() {
        System.out.println("Начало сцены:");

        try {
            livingRoom.addCharacter(kid);
        } catch (LocationOvercrowdedException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            kitchen.addCharacter(frekenBok);
        } catch (LocationOvercrowdedException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Симулирует процесс приготовления кофе.
     *
     * @throws InterruptedException если пауза между действиями прервана.
     */
    private void cookCoffee() throws InterruptedException {
        System.out.println("\nФрекен Бок варит кофе");

        boolean coffeeIsGood = true;

        try {
            frekenBok.makeCoffee();
        } catch (CoffeeRanAwayException e) {
            System.out.println(e.getMessage() + " " + frekenBok.getName() + " вскрикнула!");
            frekenBok.setMood(Mood.ANNOYED);
            coffeeIsGood = false;
        }

        if (!coffeeIsGood) {
            Thread.sleep(700);
            handleBadCoffee();
        } else {
            Thread.sleep(700);
            handleGoodCoffee();
        }
    }

    /**
     * Обрабатывает ветку сюжета с неудачным кофе.
     *
     * @throws InterruptedException если пауза прервана.
     */
    private void handleBadCoffee() throws InterruptedException {
        System.out.println("\nКофе не удался! Фрекен Бок идет проверять Малыша в комнату.");

        try {
            kitchen.removeCharacter(frekenBok);
            livingRoom.addCharacter(frekenBok);
        } catch (LocationOvercrowdedException e) {
            System.out.println("Комната переполнена!");
        }

        Thread.sleep(700);

        maybeKarlssonArrivesIntoLivingRoom();

        System.out.println("В комнате сейчас находятся: " + livingRoom.getCharactersIn());
        
        boolean karlssonThere = livingRoom.getCharactersIn().stream().anyMatch(p -> p instanceof Karlsson);

        if (karlssonThere) {
            System.out.println("\nФрекен Бок видит Карлсона в комнате Малыша!");
            frekenBok.setMood(Mood.FURIOUS);
            System.out.println("Настроение Фрекен Бок изменилось на " + frekenBok.getMood());
            frekenBok.chaseKarlsson();
        } else {
            frekenBok.setMood(Mood.DEFAULT);
            System.out.println("\nМалыш в комнате один, настроение Фрекен Бок изменилось на " + frekenBok.getMood());
        }
    }

    /**
     * Обрабатывает ветку сюжета с удачным кофе.
     *
     * @throws InterruptedException если пауза прервана.
     */
    private void handleGoodCoffee() throws InterruptedException {
        System.out.println("\nКофе удался! Фрекен Бок идет есть плюшки.");
        frekenBok.setMood(Mood.DEFAULT);

        int numberOfBuns = random.nextInt(3) + 2;
        ArrayList<Buns> buns = new ArrayList<>();
        for (int i = 0; i < numberOfBuns; i++) {
            buns.add(new Buns(random.nextInt(10)));
        }
        kitchen.placeBuns(buns);

        Thread.sleep(700);

        try {
            frekenBok.eatBuns(kitchen.getBunsOnTable());
        } catch (BunsNotFreshException e) {
            System.out.println(frekenBok.getName() + " вскрикнула: " + e.getMessage());
            frekenBok.setMood(Mood.ANGRY);
            System.out.println("Настроение Фрекен Бок изменилось на " + frekenBok.getMood());
        }
    }

    /**
     * Определяет вероятность появления Карлсона в комнате.
     */
    private void maybeKarlssonArrivesIntoLivingRoom() {
        if (random.nextInt(100) < 30) {
            System.out.println("\nСлышно жужжание... Карлсон влетел в комнату Малыша!");
            try {
                livingRoom.addCharacter(karlsson);
            } catch (LocationOvercrowdedException e) {
                System.out.println("Комната переполнена, Карлсон улетел.");
            }
        }
    }
}