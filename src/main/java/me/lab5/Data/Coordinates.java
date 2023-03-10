package me.lab5.Data;

public class Coordinates {
    private float x; //Значение поля должно быть больше -18
    private Long y; //Поле не может быть null


    public Coordinates(float x, Long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
