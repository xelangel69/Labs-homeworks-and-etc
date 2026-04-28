package com.common.model;

import java.io.Serializable;

/**
 * Класс представляющий координаты
 * @author Ivan Kirillov
 */
public final class Coordinates implements Serializable {
    private double x;
    private int y; //Максимальное значение поля: 71

    /**
     * Конструктор класса
     */
    public Coordinates(double x, int y) {
        if (y > 71) throw new IllegalArgumentException("Y > 71");

        this.x = x;
        this.y = y;
    }

    /**
     * Пустой конструктор класса (необходим для модуля Jackson)
     */
    public Coordinates() {}

    public void setX(double x) {
        this.x = x;
    }

    public void setY(int y) {
        if (y > 71) throw new IllegalArgumentException("Y > 71");
        this.y = y;
    }

    /**
     * @return координаты по X
     */
    public double getX() {
        return x;
    }

    /**
     * @return координаты по Y
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "\n   X - " + x
                + ";\n   Y - " + y;
    }
}