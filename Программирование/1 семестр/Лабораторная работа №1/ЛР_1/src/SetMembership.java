import java.util.HashSet;
import java.util.Set;

public class SetMembership {
    public static void main(String[] args) {
        // Создаем множество строк
        Set<String> fruits = new HashSet<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("orange");

        // Проверяем, принадлежит ли элемент множеству
        String elementToCheck = "banana";
        boolean isPresent = fruits.contains(elementToCheck);

        if (isPresent) {
            System.out.println("Элемент '" + elementToCheck + "' принадлежит множеству."); // Вывод: Элемент 'banana' принадлежит множеству.
        } else {
            System.out.println("Элемент '" + elementToCheck + "' не принадлежит множеству.");
        }

        elementToCheck = "grape";
        isPresent = fruits.contains(elementToCheck);

        if (isPresent) {
            System.out.println("Элемент '" + elementToCheck + "' принадлежит множеству.");
        } else {
            System.out.println("Элемент '" + elementToCheck + "' не принадлежит множеству."); // Вывод: Элемент 'grape' не принадлежит множеству.
        }
    }
}