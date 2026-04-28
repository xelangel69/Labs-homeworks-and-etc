package com.client.asker;

/**
 * Абстрактный класс, представляющий класс предок для всех "опрашивателей"
 * @author Ivan Kirillov
 */
public abstract class Asker<T> {
    /**
     * Сборщик готового объекта
     * @return готовый объект
     */
    public abstract T builder();
}
