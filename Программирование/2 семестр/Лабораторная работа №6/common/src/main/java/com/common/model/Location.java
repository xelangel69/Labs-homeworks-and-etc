package com.common.model;

import java.io.Serializable;

/**
 * Точка отправления/прибытия
 * @author Ivan Kirillov
 */
public final class Location implements Serializable {
    private long x;
    private Integer y;
    private Float z;

    /**
     * Конструктор класса
     */
    public Location(long x, Integer y, Float z) {
        if (y == null) throw new IllegalArgumentException("y null");
        if (z == null) throw new IllegalArgumentException("z null");

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Пустой конструктор класса (необходим для модуля Jackson)
     */
    public Location() {}

    public void setX(long x) {
        this.x = x;
    }

    public void setY(Integer y) {
        if (y == null) throw new IllegalArgumentException("y null");
        this.y = y;
    }

    public void setZ(Float z) {
        if (z == null) throw new IllegalArgumentException("z null");
        this.z = z;
    }

    /**
     * @return координаты по X
     */
    public long getX() {
        return x;
    }

    /**
     * @return координаты по Y
     */
    public Integer getY() {
        return y;
    }

    /**
     * @return координаты по Z
     */
    public Float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "\n   X - " + x
                + ";\n   Y - " + y
                + ";\n   Z - " + z;
    }
}

