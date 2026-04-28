package Characters;

import Places.Locations;

/**
 * Класс, представляющий персонажа "Карлсон".
 */
public final class Karlsson extends Persona {

    /**
     * Конструктор персонажа.
     */
    public Karlsson() {
        super("Карлсон", Mood.DEFAULT, Locations.LIVING_ROOM);
    }

    @Override
    public void moveTo(Locations location){

    }
}