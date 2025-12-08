package Interfaces;

import Places.Locations;

/**
 * Интерфейс, определяющий способность объекта перемещаться в пространстве.
 */
public interface MovingTo {

    /**
     * Выполняет перемещение объекта в заданную локацию.
     *
     * @param location целевая локация, куда должен переместиться объект (элемент перечисления {@link Locations}).
     */
    void moveTo(Locations location);
}