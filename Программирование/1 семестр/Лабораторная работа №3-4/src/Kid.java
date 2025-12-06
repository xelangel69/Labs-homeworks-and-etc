/**
 * Класс, представляющий персонажа "Малыш".
 */
public final class Kid extends Persona {

    /**
     * Конструктор персонажа.
     */
    Kid(){
        super("Малыш", Mood.DEFAULT, Locations.LIVING_ROOM);
    }

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
}