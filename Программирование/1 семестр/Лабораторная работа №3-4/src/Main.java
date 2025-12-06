/**
 * Главный класс приложения, содержащий точку входа в программу.
 */
public class Main {

    /**
     * Конструктор класса Main
     */
    public Main(){
        
    }

    /**
     * Запускает приложение.
     *
     * @param args аргументы командной строки.
     * @throws InterruptedException для случаев, если выполнение основного потока было прервано.
     */
    public static void main(String[] args) throws InterruptedException {
        new Act().start();
    }
}