package com.common.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * Маршрут
 * @author Ivan Kirillov
 */
public final class Route implements Comparable<Route>, Serializable {
    private static long idCounter = 1;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private Float distance; //Поле может быть null, Значение поля должно быть больше 1

    /**
     * Конструктор класса
     */
    public Route(String name, Coordinates coordinates, Location from, Location to, Float distance) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException();
        if (coordinates == null) throw new IllegalArgumentException();
        if (to == null) throw new IllegalArgumentException();
        if (distance <= 1) throw new IllegalArgumentException();

        this.id = idCounter++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.ZonedDateTime.now();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    /**
     * Пустой конструктор класса (необходим для модуля Jackson)
     */
    public Route() {}

    public static void updateIdCounter(Collection<Route> routes) {
        if (routes == null || routes.isEmpty()) {
            idCounter = 1;
            return;
        }

        long maxId = routes.stream()
                .mapToLong(Route::getId)
                .max()
                .orElse(0);

        idCounter = maxId + 1;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Неправильное имя!");
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new IllegalArgumentException("Координаты не могут быть пустыми!");
        this.coordinates = coordinates;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setTo(Location to) {
        if (to == null) throw new IllegalArgumentException();
        this.to = to;
    }

    public void setDistance(Float distance) {
        if (distance <= 1) throw new IllegalArgumentException();
        this.distance = distance;
    }

    /**
     * @return ID маршрута
     */
    public long getId() {
        return id;
    }

    /**
     * @return название маршрута
     */
    public String getName() {
        return name;
    }

    /**
     * @return координаты маршрута
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return время создания маршрута
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return точка отправления маршрута
     */
    public Location getFrom() {
        return from;
    }

    /**
     * @return точка прибытия маршрута
     */
    public Location getTo() {
        return to;
    }

    /**
     * @return дистанция маршрута
     */
    public Float getDistance() {
        return distance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationDate(java.time.ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public static long generateId() {
        return idCounter++;
    }

    @Override
    public String toString() {
        return "ID маршрута - " + id
                + ";\nНазвание маршрута - " + name
                + ";\nКоординаты: " + coordinates
                + ";\nВремя создания - " + creationDate
                + ";\nТочка отправления: " + from
                + ";\nТочка прибытия: " + to
                + ";\nРасстояние - " + distance
                + ";\n";
    }

    @Override
    public int compareTo(Route other) {
        if (this.distance == null || other.distance == null) return 0;
        return this.distance.compareTo(other.distance);
    }
}