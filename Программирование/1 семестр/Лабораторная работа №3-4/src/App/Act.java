package App;

import Exceptions.*;
import Items.Buns;
import Characters.*;
import Places.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Класс, управляющий основным сценарием симуляции.
 */
public class Act {

    /**
     * Конструктор класса app.Act
     */
    public Act(){

    }

    /**
     * Создание экземпляров класса Kitchen и LivingRoom
     */
    private final Kitchen kitchen = new Kitchen();
    private final LivingRoom livingRoom = new LivingRoom();

    /**
     * Создание экземпляров класса Kid, FrekenBok и Karlsson
     */
    private final Kid kid = new Kid();
    private final FrekenBok frekenBok = new FrekenBok();
    private final Karlsson karlsson = new Karlsson();

    /**
     * Генератор случайных чисел для событий сцены (свежесть плюшек, появление Карлсона).
     */
    private final Random random = new Random();

    /**
     * Переменная (bool) проверяющая наличие Малыша на кухне
     */
    private boolean kidInKitchen = true;

    /**
     * Запускает выполнение сценария.
     *
     * @throws InterruptedException если во время расставления персонажей произошла ошибка.
     */
    public void start() throws InterruptedException, LocationOvercrowdedException, KidEatBunsException {
        initialPlacement();
        cookCoffee();
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
     * Определяет вероятность того, что Малыш пойдет на кухню
     */
    private void kidDecideToGo() {
        int chance = random.nextInt(10);

        if (chance >= 5) {
            System.out.println("\nМалыш решился пойти на кухню");

            try {
                livingRoom.removeCharacter(kid);
                kid.moveTo(Locations.KITCHEN);
                kitchen.addCharacter(kid);
                kid.setLocation(Locations.KITCHEN);
                kidInKitchen = true;
            } catch (LocationOvercrowdedException e) {
                System.out.println("Кухня переполнена!");
            }

        } else {
            System.out.println("\nМалыш не решился идти на кухню");
            kidInKitchen = false;
        }

    }

    /**
     * Определяет вероятность того, что Фрекен пойдет в комнату Малыша
     */
    private void frekenBokDecideToGo() {
        int chance = random.nextInt(10);

        if (chance >= 5) {
            System.out.println("\nФрекен Бок идет проверять Малыша в комнату.");

            try {
                kitchen.removeCharacter(frekenBok);
                frekenBok.moveTo(Locations.LIVING_ROOM);
                livingRoom.addCharacter(frekenBok);
                frekenBok.setLocation(Locations.LIVING_ROOM);
            } catch (LocationOvercrowdedException e) {
                System.out.println("Комната переполнена!");
            }

        } else {
            System.out.println("\nФрекен Бок решила не проверять Малыша.");
        }

    }

    /**
     * Симулирует процесс приготовления кофе.
     *
     * @throws InterruptedException если кофе убежало.
     */
    private void cookCoffee() throws InterruptedException, KidEatBunsException {

        boolean coffeeIsGood = true;

        try {
            frekenBok.makeCoffee();
        } catch (CoffeeRanAwayException e) {
            System.out.println(e.getMessage() + " " + frekenBok.getName() + " вскрикнула!");
            frekenBok.setMood(Mood.ANNOYED);
            coffeeIsGood = false;
        }

        if (!coffeeIsGood) {
            handleBadCoffee();
        } else {
            handleGoodCoffee();
        }
    }

    /**
     * Обрабатывает ветку сюжета с неудачным кофе.
     *
     * @throws InterruptedException если комната переполнена.
     */
    private void handleBadCoffee() throws InterruptedException {
        System.out.println("Настроение Фрекен Бок изменилось на " + frekenBok.getMood());
        System.out.println("\nФрекен Бок идет проверять Малыша в комнату.");

        try {
            kitchen.removeCharacter(frekenBok);
            livingRoom.addCharacter(frekenBok);
            frekenBok.setLocation(Locations.LIVING_ROOM);
        } catch (LocationOvercrowdedException e) {
            System.out.println("Комната переполнена!");
        }

        maybeKarlssonArrives();

        boolean karlssonThere = false;
        for (Persona character : livingRoom.getCharactersIn()) {
            if (character instanceof Karlsson) {
                karlssonThere = true;
                break;
            }
        }

        if (karlssonThere) {
            System.out.println("\nФрекен Бок видит Карлсона в комнате Малыша!");
            frekenBok.setMood(Mood.FURIOUS);
            System.out.println("Настроение Фрекен Бок изменилось на " + frekenBok.getMood());
            frekenBok.chaseKarlsson(karlsson);
            kid.setMood(Mood.JOYFUL);
            System.out.println("Настроение Малыша изменилось на " + kid.getMood());
            endOfScene();
        } else {
            frekenBok.setMood(Mood.DEFAULT);
            System.out.println("\nМалыш в комнате один, настроение Фрекен Бок изменилось на " + frekenBok.getMood());
            endOfScene();
        }
    }

    /**
     * Обрабатывает ветку сюжета с удачным кофе.
     *
     * @throws InterruptedException если плюшки несвежие.
     */
    private void handleGoodCoffee() throws InterruptedException, KidEatBunsException {
        kidDecideToGo();

        System.out.println("\nФрекен Бок идет есть плюшки.");

        int numberOfBuns = random.nextInt(3) + 2;
        ArrayList<Buns> buns = new ArrayList<>();
        for (int i = 0; i < numberOfBuns; i++) {
            buns.add(new Buns(random.nextInt(10)));
        }
        kitchen.placeBuns(buns);

        if (kidInKitchen) {
            kidEatBun();
        }

        try {
            frekenBok.eatBuns(kitchen.getBunsOnTable());
            if (kidInKitchen) {
                endOfScene();
            }
        } catch (BunsNotFreshException e) {
            System.out.println(frekenBok.getName() + " вскрикнула: " + e.getMessage());
            if (frekenBok.getMood() != Mood.ANGRY) {
                frekenBok.setMood(Mood.ANGRY);
            } else {
                frekenBok.setMood(Mood.FURIOUS);
            }
            System.out.println("Настроение Фрекен Бок изменилось на " + frekenBok.getMood());
            endOfScene();
        }

        if (!kidInKitchen) {
            frekenBokDecideToGo();
            endOfScene();
        }
    }

    /**
     * Определяет вероятность того, что Малыш попробует съесть плюшку
     */
    private void kidEatBun() {
        int chance = random.nextInt(10);

        if (chance >= 5) {
            System.out.println("\nМалыш пытается съесть плюшку.");
            try {
                kid.eatBuns(kitchen.getBunsOnTable());
            } catch (KidEatBunsException e) {
                System.out.println(e.getMessage());
                System.out.println("Фрекен Бок не понравились действия Малыша");
                frekenBok.setMood(Mood.ANGRY);
                System.out.println("Настроение Фрекен Бок изменилось на " + frekenBok.getMood());
            }
        } else {
            System.out.println("Малыш не решился съесть плюшку");
        }
    }

    /**
     * Определяет вероятность появления Карлсона в комнате.
     */
    private void maybeKarlssonArrives() {
        if (random.nextInt(100) < 30) {
            System.out.println("\nСлышно жужжание... Карлсон влетел в комнату Малыша!");
            try {
                livingRoom.addCharacter(karlsson);
            } catch (LocationOvercrowdedException e) {
                System.out.println("Комната переполнена, Карлсон улетел.");
            }
        }
    }

    /**
     * Задает конец сцены, выводит информацию о состоянии персонажей
     */
    public void endOfScene(){
        System.out.println("\nКонец сцены:");
        System.out.println("Состояние Малыша - " + kid);
        System.out.println("Состояние Фрекен Бок - " + frekenBok);
    }
}