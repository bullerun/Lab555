package me.lab5.Data;

import me.lab5.Exception.RangeException;

public class Coordinates {
    private final float MINIMAL_X_COORDINATES = -18;
    private float x; //Значение поля должно быть больше -18
    private Long y; //Поле не может быть null

//    public Coordinates(float x, Long y) {
//        this.x = x;
//        this.y = y;
//    }
    public Coordinates(){

    }
    public void setX(float x) throws RangeException, IllegalArgumentException{
        if (x <= MINIMAL_X_COORDINATES) throw new RangeException();
        if (x == Float.POSITIVE_INFINITY) throw new IllegalArgumentException();
        this.x = x;
    }

    public void setY(Long y) throws IllegalArgumentException{
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
