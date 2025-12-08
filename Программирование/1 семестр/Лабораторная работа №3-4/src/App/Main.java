package App;

import Exceptions.LocationOvercrowdedException;

/**
 * Главный класс приложения, содержащий точку входа в программу.
 */
public class Main {

    /**
     * Конструктор класса app.Main
     */
    public Main(){
        
    }

    /**
     * Запускает приложение.
     *
     * @param args аргументы командной строки.
     * @throws InterruptedException для случаев, если произошла ошибка во время выполнения.
     */
    public static void main(String[] args) throws InterruptedException, LocationOvercrowdedException {
        new Act().start();
    }
}