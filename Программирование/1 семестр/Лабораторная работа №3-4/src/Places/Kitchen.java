package Places;

import Items.Buns;

import java.util.ArrayList;

/**
 * Класс, представляющий локацию кухни.
 */
public class Kitchen extends Location {

    /** Список плюшек, находящихся в данный момент на столе. */
    private final ArrayList<Buns> bunsOnTable;

    /**
     * Конструктор кухни.
     */
    public Kitchen() {
        super("Кухня", 3);
        this.bunsOnTable = new ArrayList<>();
    }

    /**
     * Размещает n плюшек на столе.
     *
     * @param buns список объектов {@link Buns}, которые нужно добавить на стол.
     */
    public void placeBuns(ArrayList<Buns> buns) {
        this.bunsOnTable.addAll(buns);
        System.out.println("На столе появились плюшки в количестве " + buns.size());
    }

    /**
     * Возвращает список плюшек, лежащих на столе.
     *
     * @return список объектов {@link Buns}.
     */
    public ArrayList<Buns> getBunsOnTable() {
        return bunsOnTable;
    }
}